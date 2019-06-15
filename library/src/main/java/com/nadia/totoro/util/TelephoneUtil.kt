package com.nadia.totoro.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager


/**
 * 电话相关操作类
 * author: Created by 闹闹 on 2018-08-14
 * version: 1.0.0
 */
class TelephoneUtil(val context: Context) {

    /**
     * 直接拨打电话
     */
    fun callPhone(phoneNum: String?) {
        if (phoneNum != null && phoneNum.trim { it <= ' ' }.isNotEmpty()) {
            val intent = Intent()
            intent.action = "android.intent.action.CALL"
            intent.data = Uri.parse("tel:$phoneNum")
            context.startActivity(intent)
        }
    }

    /**
     * 跳转到拨号界面
     */
    fun callSysDial(phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val uri = Uri.parse("tel:$phoneNum")
        intent.data = uri
        context.startActivity(intent)
    }

    /**
     * 发送邮件
     *
     * @param mailAddress 邮件地址
     */
    fun sendMail(mailAddress: String) {
        val uri = Uri.parse("mailto:$mailAddress")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        //intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
        // intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
        // intent.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"))
    }

    /**
     * 跳转到微信
     */
    fun jumpWeChat() {
        val intent = Intent()
        val component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.component = component
        context.startActivity(intent)
    }

    /**
     * 跳转到系统的短信编辑界面
     */
    fun sendMessage(phoneNum: String, content: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        val uri = Uri.parse("smsto:$phoneNum")
        intent.data = uri
        intent.putExtra("sms_body", content)
        context.startActivity(intent)
    }

    /**
     * 直接发送短信，无界面
     */
    fun sendHideMessage(phoneNum: String?, content: String) {
        if (phoneNum != null && phoneNum.trim { it <= ' ' }.isNotEmpty()) {
            val manager = SmsManager.getDefault()
            // 消息内容大于70就对消息进行拆分
            if (content.length > 70) {
                val arrayList = manager.divideMessage(content)
                for (message in arrayList) {
                    manager.sendTextMessage(phoneNum, null, message, null, null)
                }
            } else {
                manager.sendTextMessage(phoneNum, null, content, null, null)
            }
        }
    }
}
