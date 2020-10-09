package com.ningwenqiang.glory.toollibrary.mvvm.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
create by: 86136
create time: 2020/9/30 10:19
Function description:
 */
public abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<BaseModel>> :
    AppCompatActivity(), IBaseView {

    protected var binding: V? = null
    protected var viewModel: VM? = null
    private var viewModelId = 0
    // private val dialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //页面接受的参数方法
        initParam()
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState)
        //私有的ViewModel与View的契约事件回调逻辑
        //  registorUIChangeLiveDataCallBack()
        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        viewModel!!.registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        //解除Messenger注册
        viewModel?.removeRxBus()
        binding?.unbind()
    }


    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(savedInstanceState: Bundle?): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int


    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    open fun initViewModel(): VM? {
        return null
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
        viewModelId = initVariableId()
        viewModel = initViewModel()
        if (viewModel == null) {
            val modelClass: Class<*>
            var type: Type? = javaClass::class.java.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[1] as Class<*>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseViewModel::class.java
            }
            viewModel = createViewModel(this, modelClass as Class<VM>)
        }

        //关联ViewModel
        binding?.setVariable(viewModelId, viewModel)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding?.lifecycleOwner = this
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel!!)
        //注入RxLifecycle生命周期
        //viewModel.injectLifecycleProvider(this)
    }


    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    open fun <T : ViewModel> createViewModel(activity: AppCompatActivity, cls: Class<T>): T {
        return ViewModelFactory.getInstance(application).create(cls)
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    open fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    open fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    open fun startContainerActivity(canonicalName: String?) {
        startContainerActivity(canonicalName, null)
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    open fun startContainerActivity(canonicalName: String?, bundle: Bundle?) {
        val intent =
            Intent(this, ContainerActivity::class.java)
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName)
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle)
        }
        startActivity(intent)
    }
}
