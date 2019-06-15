package com.nadia.totoro.util

import android.content.Context
import android.widget.ImageView
import com.nadia.totoro.R
import com.nadia.totoro.widget.CircleImageView
import com.nadia.totoro.widget.RoundImageView
import com.squareup.picasso.Picasso

/**
 * 处理图片
 * author: Created by 闹闹 on 2018-10-14
 * version: 1.0.0
 */
class ImageUtil(val context: Context) {

    private val icon = R.mipmap.ic_header_normal

    /**
     * 加载普通头像
     * @param url 地址
     * @param resId 资源 可以为0
     * @param view 控件
     */
    fun loadHeadView(url: String, resId: Int, view: ImageView) {
        Picasso.with(context).load(url).error(if (resId != 0) resId else icon).into(view)
    }

    /**
     * 加载原形头像
     * @param url 地址
     * @param resId 资源 可以为0
     * @param view 控件
     */
    fun loadHeadView(url: String, resId: Int, view: CircleImageView) {
        Picasso.with(context).load(url).error(if (resId != 0) resId else icon).into(view)
    }

    /**
     * 加载网络图片
     * @param url 地址
     * @param placeholderId 占位图
     * @param errorId 错误资源
     * @param imageView 控件
     */
    fun loadView(url: String?, placeholderId: Int, errorId: Int, imageView: ImageView) {
        Picasso.with(context).load(url).placeholder(placeholderId).error(errorId).into(imageView)
    }

    /**
     * 加载网络图片
     * @param url 地址
     * @param placeholderId 占位图
     * @param errorId 错误资源
     * @param imageView 控件
     */
    fun loadView(url: String?, placeholderId: Int, errorId: Int, imageView: RoundImageView) {
        Picasso.with(context).load(url).placeholder(placeholderId).error(errorId).into(imageView)
    }

    /**
     * 加载本地图片
     * @param resId 本地资源
     * @param placeholderId 占位图
     * @param errorId 错误资源
     * @param imageView 控件
     */
    fun loadView(resId: Int, placeholderId: Int, errorId: Int, imageView: ImageView) {
        Picasso.with(context).load(resId).placeholder(placeholderId).error(errorId).into(imageView)
    }

    /**
     * 加载本地图片
     * @param resId 本地资源
     * @param placeholderId 占位图
     * @param errorId 错误资源
     * @param imageView 控件
     */
    fun loadView(resId: Int, placeholderId: Int, errorId: Int, imageView: RoundImageView) {
        Picasso.with(context).load(resId).placeholder(placeholderId).error(errorId).into(imageView)
    }
}
