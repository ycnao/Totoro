package com.nadia.totoro.base

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.nadia.totoro.R
import com.nadia.totoro.app.NAppManager
import org.greenrobot.eventbus.EventBus
import java.io.Serializable

/**
 * 顶层抽象父类，与具体项目无关
 *
 * @param <T> 子类类型
 * author: Created by 闹闹 on 2018-09-11
 * version: 1.0.0
 */
abstract class IBaseActivity<T : Activity> : AppCompatActivity() {

	lateinit var instance: T

	lateinit var mActivityManager: NAppManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		instance = this as T
		mActivityManager = NAppManager.getAppManager()
		mActivityManager.addActivity(this)
	}

	/**
	 * activity过度类型枚举
	 */
	enum class ActivityTransitionMode {
		LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
	}

	/*** Fragment 相关操作 ***/
	fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction { add(frameId, fragment) }

	fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction { replace(frameId, fragment) }

	private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
		val fragmentTransaction = beginTransaction()
		fragmentTransaction.func()
		fragmentTransaction.commit()
	}

	/**
	 * 事件
	 */
	fun registerEventBus(subscriber: Any) {
		if (!EventBus.getDefault().isRegistered(subscriber)) EventBus.getDefault().register(subscriber)
	}

	fun unregisterEventBus(subscriber: Any) {
		if (EventBus.getDefault().isRegistered(subscriber)) EventBus.getDefault().unregister(subscriber)
	}

	/**
	 * 短提示
	 * @param msg 消息内容
	 */
	fun toastShow(msg: String) = Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

	/**
	 * 短提示
	 * @param resId 资源内容
	 */
	fun toastShow(resId: Int) = Toast.makeText(applicationContext, resId, Toast.LENGTH_SHORT).show()

	/**
	 * 长提示
	 *
	 * @param msg 消息内容
	 */
	fun toastShowLong(msg: String) = Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()

	/**
	 * 转换
	 */
	fun textToString(text: TextView): String = text.text.toString()

	fun editToString(editText: EditText): String = editText.text.toString().trim()

	/*** Intent 相关操作 ***/
	fun getStringExtra(name: String) = intent.getStringExtra(name) ?: ""

	fun getIntExtra(name: String): Int = intent.getIntExtra(name, 0)

	fun getLongExtra(name: String): Long = intent.getLongExtra(name, 0L)

	fun getStringArrayExtra(name: String): Array<String> = intent.getStringArrayExtra(name)

	fun getBooleanExtra(name: String): Boolean = intent.getBooleanExtra(name, false)

	fun <T : Parcelable> getParcelableExtra(name: String): T = intent.getParcelableExtra(name)

	fun <V : Serializable> getSerializableExtra(name: String): V = intent.getSerializableExtra(name) as V

	protected fun startActivity(clazz: Class<*>) {
		startActivity(intent.setClass(instance, clazz))
		overridePendingTransition(R.anim.transition_scale_in, R.anim.transition_scale_out)
	}

	protected fun startActivityForResult(clazz: Class<*>, requestCode: Int) {
		intent.setClass(instance, clazz)
		startActivityForResult(intent, requestCode)
	}
}
