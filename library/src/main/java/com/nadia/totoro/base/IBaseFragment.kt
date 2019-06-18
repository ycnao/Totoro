package com.nadia.totoro.base

import android.os.Bundle
import android.view.View
import android.app.Activity
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.app.Fragment


/**
 * 碎片顶层抽象
 *
 * @param <A> 持有该fragment的activity类型
 * @param <F> 其具体子类类型
 *
 * author: Created by 闹闹 on 2018-09-12
 * version: 1.0.0
 */
abstract class IBaseFragment<A : IBaseActivity<A>, F : Fragment> : Fragment() {

	val INDEX = "index"

	var mIndex: Int = -1

	lateinit var act: A

	lateinit var instance: F

	/**
	 * 获取当前碎片索引
	 *
	 * @return mIndex
	 */
	fun getIndex() = mIndex


	/**
	 * Fragment当前状态是否可见
	 */
	private var isVisible: Boolean? = false

	/**
	 * 可见时的回调方法
	 */
	protected abstract fun onVisible()

	/**
	 * 不可见时的回调方法
	 */
	protected abstract fun onInvisible()

	protected abstract fun initLayout(): Int

	protected abstract fun afterInjectView(savedInstanceState: Bundle?)

	override fun onCreate(savedInstanceState: Bundle?) {
		/**
		 * 得到初始化参数包中的数据
		 */
		if (arguments != null) mIndex = arguments!!.getInt(INDEX)
		super.onCreate(savedInstanceState)
		instance = this as F
	}

	override fun onAttach(activity: Activity) {
		super.onAttach(activity)
		act = activity as A
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		afterInjectView(savedInstanceState)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		// Create, or inflate the Fragment's UI, and return it.
		// If this Fragment has no UI then return null.
		return inflater.inflate(initLayout(), container, false)
	}

	override fun setUserVisibleHint(isVisibleToUser: Boolean) {
		super.setUserVisibleHint(isVisibleToUser)
		if (userVisibleHint) {
			isVisible = true
			onVisible()
		} else {
			isVisible = false
			onInvisible()
		}
	}

	protected fun <Q> getAct(q: Class<Q>): Q = activity as Q

	/**
	 * intent
	 */
	protected fun <K> startActivity(class1: Class<K>) = startActivity(act.intent.setClass(act, class1))

	/**
	 * 跳到另外一个activity，注意intent的必须是act.intent
	 *
	 * @param class1
	 */
	protected fun <T> startActivityForResult(class1: Class<T>, requestCode: Int) {
		act.intent.setClass(act, class1)
		startActivityForResult(act.intent, requestCode)
	}

	override fun onPause() {
		super.onPause()
	}

	override fun onStart() {
		super.onStart()
	}

	override fun onResume() {
		super.onResume()
	}

	override fun onStop() {
		super.onStop()
	}

	override fun onDestroy() {
		super.onDestroy()
	}
}
