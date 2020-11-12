package com.ningwenqiang.glory.toollibrary.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import com.ningwenqiang.glory.toollibrary.activity.ActivityStackManager
import com.ningwenqiang.glory.toollibrary.log.L
import com.ningwenqiang.glory.toollibrary.observer.ObserverNwq
import com.nwq.code.liferecord.R
import okio.BufferedSource
import okio.buffer
import okio.sink
import okio.source
import java.io.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*

/**
create by: 86136
create time: 2020/11/10 10:21
Function description:
 */
object FileUtils {

    /**
     * 1MB
     */
    private const val MD5_FILE_BUFFER_LENGTH = 1 * 1024 * 1024
    private const val JCAMERA = "JCAMERA"

    /**
     * 根节点
     */
    private fun getFileRootDirectory(childName: String): File? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityStackManager.getContext().getExternalFilesDir(childName)?.absoluteFile
        } else {
            Environment.getExternalStoragePublicDirectory(childName).absoluteFile
        }
    }

    /**
     * JCamera
     * 根节点
     */
    fun getJCameraRootDirector(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityStackManager.getContext().getExternalFilesDir(JCAMERA)?.absoluteFile?.path
        } else {
            Environment.getExternalStoragePublicDirectory(JCAMERA).absoluteFile?.path
        }
    }


    fun saveBitmap(bitmap: Bitmap): String? {
        val bao = ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        val bytes = bao.toByteArray()
        return saveToFile(bytes, ".jpg")
    }


    /**
     * 判断文件是否存在
     * @param path 文件路径
     * @return 是否存在
     */
    fun isExist(path: String): Boolean {
        val file = File(path)
        return file.exists()
    }

    /**
     * 删除文件或者目录
     *
     * @param path 指定路径的文件或目录
     * @return 返回操作结果
     */
    fun deleteFile(path: String): Boolean {
        if (TextUtils.isEmpty(path)) {
            return true
        }
        val file = File(path)
        if (!file.exists()) {
            return true
        }
        if (file.isDirectory) {
            val subPaths = file.list()
            for (p in subPaths) {
                if (!deleteFile(path + File.separator + p)) {
                    return false
                }
            }
        }
        return file.delete()
    }

    /**
     * 猎取临时目录
     *
     * @param context
     * @return
     */
    fun getCachePath(context: Context): File {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                context.cacheDir ?: context.cacheDir
            } else {
                context.externalCacheDir ?: context.cacheDir
            }
        } else {
            context.cacheDir
        }
    }

    /**
     * 保存byte数组到一个文件
     *
     * @param context
     * @param data
     * @return 返回生成MD5摘要的文件名称
     */
    fun saveToFile(
        data: ByteArray,
        suffixStr: String = "",
        context: Context = ActivityStackManager.getContext()
    ): String {
        val tmpFile: String = if (suffixStr.isEmpty()) {
            getCachePath(context).absolutePath + File.separator + getMD5ofByte(data)
        } else {
            getCachePath(context).absolutePath + File.separator + getMD5ofByte(data) + ".$suffixStr"
        }
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(tmpFile)
            fos.write(data)
            fos.flush()
            return tmpFile
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return ""
    }

    //字符的MD5摘要
    fun getMD5ofStr(origString: String): String? {
        return getMD5ofByte(origString.toByteArray(charset("UTF-8")))
    }

    //Byte数组的MD5摘要
    fun getMD5ofByte(origByteArray: ByteArray): String? {
        try {
            val md5: MessageDigest = MessageDigest.getInstance("MD5")
            md5.reset()
            md5.update(origByteArray)
            val result: ByteArray = md5.digest()
            val origMD5 = byteArray2HexStr(result)
            return origMD5.toLowerCase()
        } catch (e: java.lang.Exception) {
            L.e(e.toString(), "getMD5ofByte", "FileUtils", "nwq", "2020/11/10");
        }
        return null
    }

    /**
     * 以下摘要md5函数拷贝自腾讯ugckit sdk的FileUtils
     */
    @Throws(IOException::class)
    private fun getMD5FromFile(filePath: String?): String? {
        if (filePath == null) {
            return null
        }
        var digestString: String? = null
        val file = File(filePath)
        val buffer = ByteArray(1024)
        var inputStream: InputStream? = null
        var digest: MessageDigest? = null
        try {
            digest = MessageDigest.getInstance("MD5")
        } catch (e1: NoSuchAlgorithmException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
            L.e("NoSuchAlgorithmException: ", "getMD5FromFile", "FileUtils", "nwq", "2020/11/10");
        }
        val fileSize = file.length()
        if (fileSize > 3 * MD5_FILE_BUFFER_LENGTH) {
            var raf: RandomAccessFile? = null
            L.i("fileSize is greater than 3MB", "getMD5FromFile", "FileUtils", "nwq", "2020/11/10");
            // 大于3MB时，分段，分头、中、尾，各1MB
            try {
                raf = RandomAccessFile(file, "r")
                raf.seek(0)
                var bytesRead = 0
                var totalRead = 0
                val bytesToRead: Int =
                    3 * MD5_FILE_BUFFER_LENGTH

                /*
                 * 计算中、尾部起始位置，头部起始为0，不用计算 ------------------------------------------------- |Head 1MB| 间隔大小 | Mid 1MB| 间隔大小
                 * | Tail 1MB| ------------------------------------------------- 其中间隔大小计算方式为： (fileSize - 3 *
                 * MD5_FILE_BUFFER_LENGHT) / 2 所以中部起始位置为：Head + 一个间隔的大小 尾部起始点为：(fileSize - MD5_FILE_BUFFER_LENGHT)
                 */
                val midStartPosition: Int =
                    MD5_FILE_BUFFER_LENGTH + (fileSize - 3 * MD5_FILE_BUFFER_LENGTH) as Int / 2
                val tailStartPosition =
                    (fileSize - MD5_FILE_BUFFER_LENGTH) as Int
                L.i(
                    String.format(
                        "midStartPosition = %d, tailStartPosition = %d",
                        midStartPosition,
                        tailStartPosition
                    ), "getMD5FromFile", "FileUtils", "nwq", "2020/11/10"
                );
                val data = ByteArray(128 * 1024)
                while (totalRead < bytesToRead) {
                    // Log.d(TAG, "count = " + (++count));
                    bytesRead = raf.read(data)
                    totalRead += bytesRead
                    if (totalRead == MD5_FILE_BUFFER_LENGTH) {
                        // 读完头部，开始读取中部
                        L.d(
                            "totalRead == MD5_FILE_BUFFER_LENGHT",
                            "getMD5FromFile",
                            "FileUtils",
                            "nwq",
                            "2020/11/10"
                        );
                        raf.seek(midStartPosition.toLong())
                    } else if (totalRead == 2 * MD5_FILE_BUFFER_LENGTH) {
                        // 读完中部，开始读取尾部
                        L.d(
                            "totalRead == 2 * MD5_FILE_BUFFER_LENGHT",
                            "getMD5FromFile",
                            "FileUtils",
                            "nwq",
                            "2020/11/10"
                        );
                        raf.seek(tailStartPosition.toLong())
                    }
                    digest!!.update(data)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            } finally {
                if (raf != null) {
                    try {
                        raf.close()
                    } catch (e2: java.lang.Exception) {
                        e2.printStackTrace()
                    }
                }
            }
        } else {
            L.d("fileSize is smaller than 3MB", "getMD5FromFile", "FileUtils", "nwq", "2020/11/10");
            try {
                inputStream = FileInputStream(file)
                var readSize = 0
                while (readSize != -1) {
                    readSize = inputStream.read(buffer, 0, buffer.size)
                    if (readSize > 0) {
                        digest!!.update(buffer, 0, readSize)
                        // Log.d(TAG, "update: " + BytesUtil.byte2hexWithoutSpace(digest.digest()));
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e2: java.lang.Exception) {
                        e2.printStackTrace()
                    }
                }
            }
        }
        if (digest != null) {
            digestString = byteArray2HexStr(digest.digest())
        }
        return digestString
    }


    private fun byteArray2HexStr(bs: ByteArray): String {
        val md5StrBuff = StringBuilder()
        for (b in bs) {
            if (Integer.toHexString(0xFF and b.toInt()).length == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF and b.toInt()))
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF and b.toInt()))
            }
        }
        return md5StrBuff.toString()
    }


    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Throws(java.lang.Exception::class)
    fun getFileSize(file: File): Long {
        var size: Long = 0
        if (file.exists()) {
            var fis: FileInputStream? = null
            fis = FileInputStream(file)
            size = fis.available().toLong()
        } else {
            file.createNewFile()
            L.d("", "getFileSize", "FileUtils", "nwq", "2020/9/5")
        }
        return size
    }


    //保存图片到相册
    fun saveImageToGallery(
        context: Context,
        path: String,
        observerNwq: ObserverNwq<Boolean>
    ) {
        try {
            val pictureFolder = getFileRootDirectory(Environment.DIRECTORY_PICTURES)
            val appDir = File(
                pictureFolder,
                ActivityStackManager.getContext().getString(R.string.app_name)
            )//可能在公用的所以包一层
            if (!appDir.exists()) {
                val mkdirs = appDir.mkdirs()
                if (!mkdirs) {
                    observerNwq.observation(false)
                    return
                }
            }
            val file = File(appDir, File(path).name)
            val bitmap = BitmapFactory.decodeFile(path)
            val contentValues = ContentValues()
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
            contentValues.put(MediaStore.Images.Media.TITLE, file.name)
            contentValues.put(MediaStore.Images.Media.DATA, file.path)
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            val uri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            if (uri != null) {
                val outputStream =
                    context.contentResolver.openOutputStream(uri)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                    outputStream.close()
                }
            }
            observerNwq.observation(true)
        } catch (e: IOException) {
            observerNwq.observation(false)
        }
    }

    //保存视频到相册
    fun saveVideoToGallery(
        context: Context,
        videoUri: Uri,
        observerNwq: ObserverNwq<Boolean>
    ) {
        try {

            val pictureFolder = getFileRootDirectory(Environment.DIRECTORY_PICTURES)
            val appDir = File(
                pictureFolder,
                ActivityStackManager.getContext().getString(R.string.app_name)
            )//可能在公用的所以包一层
            if (!appDir.exists()) {
                val mkdirs = appDir.mkdirs()
                if (!mkdirs) {
                    observerNwq.observation(false)
                    return
                }
            }

            val retriever =
                MediaMetadataRetriever()
            retriever.setDataSource(videoUri.path)
            val nVideoWidth =
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
                    ?.toInt()
            val nVideoHeight =
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
                    ?.toInt()
            val duration =
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                    ?.toInt()
            val file =
                File(appDir, File(videoUri.path).name)
            val contentValues = ContentValues()
            contentValues.put(MediaStore.Video.Media.DISPLAY_NAME, file.name)
            contentValues.put(MediaStore.Video.Media.TITLE, file.name)
            contentValues.put(MediaStore.Video.Media.DATA, file.path)
            contentValues.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            contentValues.put(
                MediaStore.Video.Media.DATE_TAKEN,
                System.currentTimeMillis()
            )
            contentValues.put(
                MediaStore.MediaColumns.DATE_MODIFIED,
                System.currentTimeMillis() / 1000
            )
            contentValues.put(MediaStore.Video.Media.DURATION, duration)
            contentValues.put(MediaStore.Video.Media.WIDTH, nVideoWidth)
            contentValues.put(MediaStore.Video.Media.HEIGHT, nVideoHeight)
            contentValues.put(
                MediaStore.Video.Media.RESOLUTION,
                nVideoWidth.toString() + "x" + nVideoHeight
            )
            contentValues.put(
                MediaStore.Video.Media.SIZE,
                File(videoUri.path).length()
            )
            val uri = context.contentResolver
                .insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                val outputStream =
                    context.contentResolver.openOutputStream(uri)
                if (outputStream != null) {
                    val sink: okio.Sink = outputStream.sink()
                    val buffer: BufferedSource = File(videoUri.path).source().buffer()
                    buffer.readAll(sink)
                    observerNwq.observation(true)
                } else {
                    observerNwq.observation(false)
                }
            } else {
                observerNwq.observation(false)
            }
        } catch (e: java.lang.Exception) {
            observerNwq.observation(false)
        }
    }


}