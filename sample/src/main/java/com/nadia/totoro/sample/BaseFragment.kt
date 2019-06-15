package com.nadia.totoro.sample

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.nadia.totoro.base.IBaseActivity
import com.nadia.totoro.base.IBaseFragment
import com.nadia.totoro.view.IView
import com.nadia.totoro.widget.spotsdialog.SpotsDialog

/**
 *
 * author: Created by 闹闹 on 2018-09-12
 * version: 1.0.0
 */
abstract class BaseFragment<A : IBaseActivity<A>, F : Fragment> : IBaseFragment<A, F>(), IView {

    private lateinit var spotsDialog: SpotsDialog

    lateinit var mApplication: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mApplication = act.application as MyApplication
    }

    /**
     * 	动态设置状态栏高度
     */
    fun initTopView() {
//		if (topView != null) {
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//				topView.visibility = View.VISIBLE
//				//获取当前控件的布局对象
//				val params = topView.layoutParams as LinearLayout.LayoutParams
//				//设置当前控件布局的高度
//				params.height = StatusBarUtils.getStatusBarHeight(act)
//				//将设置好的布局参数应用到控件中
//				topView.layoutParams = params
//			} else {
//				topView.visibility = View.GONE
//			}
//		}
    }

    override fun showLoading(msg: String?) {
        spotsDialog = SpotsDialog(activity, if (msg.isNullOrEmpty()) getString(R.string.please_wait) else msg)
        spotsDialog.setCancelable(true)
        spotsDialog.setCanceledOnTouchOutside(true)
        spotsDialog.show()
    }

    override fun hideLoading() = spotsDialog.dismiss()

    override fun toastShowShort(msg: String) = act.toastShow(msg)

    override fun toastShowShort(rId: Int) = act.toastShow(rId)

    override fun showError(imageId: Int, text: String, status: Int) {
//		recycleView.visibility = View.GONE
//		layout_list_status.visibility = View.VISIBLE
//		iv_list_status.setImageResource(imageId)
//		tv_list_status.text = text
    }

    override fun showEmpty() {
//		recycleView.visibility = View.VISIBLE
//		layout_list_status.visibility = View.GONE
    }

    override fun getContextView(): Activity = activity!!


    /**
     * 用户相关
     */
    fun userId(): String = mApplication.userId

    fun userDetailsId(): String = mApplication.userDetailsId
}
