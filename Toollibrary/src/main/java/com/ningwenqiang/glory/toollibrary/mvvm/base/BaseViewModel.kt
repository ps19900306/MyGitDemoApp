package com.ningwenqiang.glory.toollibrary.mvvm.base

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
create by: 86136
create time: 2020/9/30 9:09
Function description:
 */

class BaseViewModel<M : BaseModel>( application: Application) :
    AndroidViewModel(application),
    IBaseViewModel {

    var model: M? = null

    constructor( application: Application, model: M) : this(application) {
        this.model = model
    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {

    }

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun registerRxBus() {

    }

    override fun removeRxBus() {

    }


}
