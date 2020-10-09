package com.ningwenqiang.glory.toollibrary.mvvm.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.InvocationTargetException

/**
create by: 86136
create time: 2020/9/30 9:44
Function description:
 */
class ViewModelFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            BaseViewModel<BaseModel>(mApplication) as T
        } else try {
            val className = modelClass.canonicalName
            val classViewModel = Class.forName(className!!)
            val cons =
                classViewModel.getConstructor(Application::class.java)
            val viewModel: ViewModel = cons.newInstance(mApplication) as ViewModel
            viewModel as T
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } catch (e: InstantiationException) {
            e.printStackTrace()
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
        //反射动态实例化ViewModel
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private lateinit var INSTANCE: ViewModelFactory

        fun getInstance(application: Application): ViewModelFactory {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = ViewModelFactory(application)
            }
            return INSTANCE
        }
    }

}