package com.binaracademy.myaccountant.data.presenter

import java.lang.ref.WeakReference

abstract class BasePresenter<V> {

    private var view: WeakReference<V>? = null

    fun setView(view: V) {
        this.view = WeakReference(view)
    }

    protected fun getView(): V? = view?.get()
}