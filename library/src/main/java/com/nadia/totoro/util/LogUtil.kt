package com.nadia.totoro.util

import android.util.Log


/**
 * Base64 加密
 * author: Created by 闹闹 on 2018-08-14
 * version: 1.0.0
 */
class LogUtil {

    /**
     * 截断输出日志
     *
     * @param message String
     */
    fun e(tag: String?, message: String?) {
        var msg = message
        if (tag == null || tag.isEmpty() || msg == null || msg.isEmpty()) return

        val segmentSize = 3 * 1024
        val length = msg.length.toLong()
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg)
        } else {
            while (msg!!.length > segmentSize) {// 循环分段打印日志
                val logContent = msg.substring(0, segmentSize)
                msg = msg.replace(logContent, "")
                Log.e(tag, logContent)
            }
            Log.e(tag, msg)// 打印剩余日志
        }
    }
}

