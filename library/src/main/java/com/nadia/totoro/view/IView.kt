package com.nadia.totoro.view

import android.app.Activity

/**
 *
 * author: Created by 闹闹 on 2018-09-12
 * version: 1.0.0
 */
interface IView {

    fun showLoading(msg: String?)

    fun hideLoading()

    fun showEmpty()

    fun toastShowShort(rId: Int)

    fun toastShowShort(msg: String)

    fun getContextView(): Activity

    fun showError(imageId: Int, text: String, status: Int)

}
