package com.nadia.totoro.presenter

import android.content.Context
import com.nadia.totoro.view.IView

/**
 *
 * author: Created by 闹闹 on 2018-09-12
 * version: 1.0.0
 */
open class BasePresenter<T : IView> : IPresenter<T> {

    private var mView: T? = null
    lateinit var mContext: Context

    override fun attachView(view: T) {
        mView = view
        mContext = mView!!.getContextView()
    }

    override fun detachView() {
        if (mView != null) {
            mView = null
        }
    }

    private fun isViewAttached() = mView != null

    fun getMvpView(): T? = mView

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException :
        RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}
