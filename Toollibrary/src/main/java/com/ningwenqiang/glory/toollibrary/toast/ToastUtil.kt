package com.ningwenqiang.glory.toollibrary.toast

import android.widget.Toast
import com.ningwenqiang.glory.toollibrary.BasicApp
import com.ningwenqiang.glory.toollibrary.BasicApp.Companion.getContextActivity
import java.lang.ref.WeakReference

/**
create by: 86136
create time: 2020/10/26 10:46
Function description:
 */
class ToastUtil {

    companion object {
        private var mToast: WeakReference<Toast>? = null

        fun showToastLong(res: CharSequence) {
            BasicApp.mHandler.post {
                mToast?.get()?.cancel()
                val toast = Toast.makeText(getContextActivity(), res, Toast.LENGTH_LONG)
                mToast = WeakReference(toast)
                toast.show()
            }
        }

        fun showToastLong(res: Int) {
            BasicApp.mHandler.post {
                mToast?.get()?.cancel()
                val toast = Toast.makeText(getContextActivity(), res, Toast.LENGTH_LONG)
                mToast = WeakReference(toast)
                toast.show()
            }
        }

        fun showToastShort(res: CharSequence) {
            BasicApp.mHandler.post {
                mToast?.get()?.cancel()
                val toast = Toast.makeText(getContextActivity(), res, Toast.LENGTH_SHORT)
                mToast = WeakReference(toast)
                toast.show()
            }
        }

        fun showToastShort(res: Int) {
            BasicApp.mHandler.post {
                mToast?.get()?.cancel()
                val toast = Toast.makeText(getContextActivity(), res, Toast.LENGTH_SHORT)
                mToast = WeakReference(toast)
                toast.show()
            }
        }

    }
}