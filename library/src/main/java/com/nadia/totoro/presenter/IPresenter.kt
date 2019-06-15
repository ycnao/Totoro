package com.nadia.totoro.presenter

import com.nadia.totoro.view.IView

/**
 *
 * author: Created by 闹闹 on 2018-09-12
 * version: 1.0.0
 */
interface IPresenter<in V : IView> {

    fun attachView(view: V)

    fun detachView()
}
