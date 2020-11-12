package com.ningwenqiang.glory.toollibrary.utils

import java.text.SimpleDateFormat
import java.util.*

/**
create by: 86136
create time: 2020/11/10 10:43
Function description:
 */
object DataTimeUtils {

    //----------------------------------------无参数，以此时此刻为准
    /**
     * 一获取当前日期相关
     * 下面这些都是无参数提交的
     */
    const val DATE_FORMAT_Y1 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_Y2 = "yyyy-MM-dd HH:mm"
    const val DATE_FORMAT_Y3 = "yyyy-MM-dd HH"
    const val DATE_FORMAT_Y4 = "yyyy-MM-dd"
    const val DATE_FORMAT_Y5 = "yyyy-MM"
    const val DATE_FORMAT_Y6 = "yyyy"
    const val DATE_FORMAT_M1 = "MM-dd HH:mm:ss"
    const val DATE_FORMAT_M2 = "MM-dd HH:mm"
    const val DATE_FORMAT_M3 = "MM-dd HH"
    const val DATE_FORMAT_M4 = "MM-dd"
    const val DATE_FORMAT_M5 = "MM"
    const val DATE_FORMAT_D1 = "dd HH:mm:ss"
    const val DATE_FORMAT_D2 = "dd HH:mm"
    const val DATE_FORMAT_D3 = "dd HH"
    const val DATE_FORMAT_D4 = "dd"
    const val DATE_FORMAT_H1 = "HH:mm:ss"
    const val DATE_FORMAT_H2 = "HH:mm"
    const val DATE_FORMAT_H3 = "HH"
    const val DATE_FORMAT_Mi1 = "mm:ss"
    const val DATE_FORMAT_Mi2 = "mm"
    const val DATE_FORMAT_S1 = "ss"


    /**
     * 获得当前日期 yyyy-MM-dd HH:mm:ss
     *
     * @return 2019-08-27 14:12:40
     */
    fun getCurrentTime(dateFormat: String): String {
        // 小写的hh取得12小时，大写的HH取的是24小时
        val df = SimpleDateFormat(dateFormat)
        val date = Date()
        return df.format(date)
    }

    /**
     * 获取当前日期是一个星期的第几天
     * @return 2
     */
    fun getDayOfWeek(): Int {
        val cal = Calendar.getInstance()
        cal.time = Date()
        return cal[Calendar.DAY_OF_WEEK] - 1
    }


    /**
     * 获得当天0点时间 的时间戳
     */
    fun getTimesmorning(): Long {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.timeInMillis
    }

    fun isItMorning(): Boolean {
        return isItMorning(System.currentTimeMillis())
    }


    //-----------------------------单一时间参数，以参数时间为准
    /**
     *现在是上午吗
     * @return 2019-08-27 14:12:40
     */
    fun isItMorning(time: Long): Boolean {
        val mCalendar = Calendar.getInstance()
        mCalendar.timeInMillis = time * 1000
        mCalendar[Calendar.HOUR]
        val apm = mCalendar[Calendar.AM_PM]
        return apm == 0
    }

    //-----------------------------相对复杂一点的逻辑

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime     当前时间
     * @param dateSection 时间区间
     * @param dateFormat  传入的时间格式 默认是 2018-01-08,2019-09-09
     * @return
     * @author jqlin
     */
    fun isEffectiveDate(
        nowTime: Date,
        dateSection: String,
        dateFormat: String = DATE_FORMAT_Y4
    ): Boolean {
        return try {
            val times = dateSection.split(",").toTypedArray()
            val startTime = SimpleDateFormat(dateFormat).parse(times[0])
            val endTime = SimpleDateFormat(dateFormat).parse(times[1])
            if (nowTime.time === startTime.time
                || nowTime.time === endTime.time
            ) {
                return true
            }
            val date = Calendar.getInstance()
            date.time = nowTime
            val begin = Calendar.getInstance()
            begin.time = startTime
            val end = Calendar.getInstance()
            end.time = endTime
            if (isSameDay(date, begin) || isSameDay(date, end)) {
                return true
            }
            date.after(begin) && date.before(end)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
        return if (cal1 != null && cal2 != null) {
            cal1[0] === cal2[0] && cal1[1] === cal2[1] && cal1[6] === cal2[6]
        } else {
            throw IllegalArgumentException("The date must not be null")
        }
    }



}