package com.nadia.totoro.sample

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.v4.app.*
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.nadia.totoro.base.IBaseActivity
import com.nadia.totoro.sample.common.app.Constants
import com.nadia.totoro.view.IView
import com.nadia.totoro.widget.spotsdialog.SpotsDialog


/**
 *
 * author: Created by 闹闹 on 2018-09-11
 * version: 1.0.0
 */
abstract class BaseActivity<T : Activity> : IBaseActivity<T>(), IView {

    lateinit var mApplication: MyApplication
    private lateinit var spotsDialog: SpotsDialog
    lateinit var inputMethodManager: InputMethodManager

    abstract fun initParameter(bundle: Bundle?)

    abstract fun initLayout(): Int

    abstract fun afterInjectView()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        //设置状态栏字体颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //4.4以上
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            if (instance is MainActivity) {
//                isActionBarBlackColor(true)
//            } else {
//                isActionBarBlackColor(false)
//            }
//        }

        //
        mApplication = applicationContext as MyApplication

        initParameter(intent.extras)
        setContentView(initLayout())
        afterInjectView()
        initTopView()

//		if (abActionIBtn != null) abBackIBtn.setOnClickListener { finish() }
    }

    /**
     * 动态设置状态栏高度
     */
    fun initTopView() {
//		if (topView != null) {
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//				topView.visibility = View.VISIBLE
//				//获取当前控件的布局对象
//				val params = topView.layoutParams as LinearLayout.LayoutParams
//				//设置当前控件布局的高度
//				params.height = StatusBarUtils.getStatusBarHeight(instance)
//				//将设置好的布局参数应用到控件中
//				topView.layoutParams = params
//			} else {
//				topView.visibility = View.GONE
//			}
//		}
    }

    /**
     * 设置状态栏 0 为白色
     */
    fun isActionBarBlackColor(isBlack: Boolean) {
        window.decorView.systemUiVisibility = if (isBlack) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
    }

    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            val mPermissionList = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_LOGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.GET_ACCOUNTS
            )
            ActivityCompat.requestPermissions(this, mPermissionList, 123)
        }
    }

    override fun showLoading(msg: String?) {
        spotsDialog = SpotsDialog(this, if (msg.isNullOrEmpty()) getString(R.string.please_wait) else msg)
        spotsDialog.setCancelable(true)
        spotsDialog.setCanceledOnTouchOutside(true)
        spotsDialog.show()
    }

    override fun hideLoading() = spotsDialog.dismiss()

    override fun toastShowShort(rId: Int) = toastShow(rId)

    override fun toastShowShort(msg: String) = toastShow(msg)

    override fun showEmpty() {
//        recycleView.visibility = View.VISIBLE
//        layout_list_status.visibility = View.GONE
    }

    override fun showError(imageId: Int, text: String, status: Int) {
//        recycleView.visibility = View.GONE
//        layout_list_status.visibility = View.VISIBLE
//        iv_list_status.setImageResource(imageId)
//        tv_list_status.text = text
    }

    override fun getContextView(): Activity = this as T

    /**
     * 用户相关
     */
    fun userId(): String = mApplication.userId

    fun userDetailsId(): String = mApplication.userDetailsId


    /**
     * 跳进浏览器。
     *
     * @param title 标题
     * @param url   url路径
     */
    fun startWebBrowser(title: String, url: String) {
        intent.putExtra(Constants.web.WEB_URL, url)
        intent.putExtra(Constants.web.WEB_TITLE, title)
//		intent.setClass(this, WebBrowserActivity::class.java)
        startActivity(intent)
    }

    /**
     * 跳进pdf浏览器。
     *
     * @param title 标题
     * @param url   url路径
     */
    fun startPdfBrowser(title: String, url: String) {
        intent.putExtra(Constants.pdf.PDF_URL, url)
        intent.putExtra(Constants.pdf.PDF_TITLE, title)
//		intent.setClass(this, PdfBrowserActivity::class.java)
        startActivity(intent)
    }


    /**
     * 浏览图片
     */
    fun startImageView(url: String) {
//		intent = Intent(this, ImageViewActivity::class.java)
        intent.putExtra(Constants.image.IMG_URL, url)
        startActivity(intent)
    }

    /**
     * 浏览图片（网络）
     *
     * @param url 地址
     */
    fun showImage(url: String) {
        val urls = arrayOf(if (TextUtils.isEmpty(url)) "http://www" else url)
        intent.putExtra(Constants.image.IMG_URLS, urls)
//		startActivity(ImgBrowserActivity::class.java)
    }

    /**
     * 浏览图片。
     *
     * @param urls 多个url
     */
    fun showImages(vararg urls: String) {
        intent.putExtra(Constants.image.IMG_URLS, urls)
//		startActivity(ImgBrowserActivity::class.java)
    }

    fun showImages(position: Int, vararg urls: String) {
        intent.putExtra(Constants.image.IMG_URLS, urls)
        intent.putExtra(Constants.image.IMG_POSITION, position)
//		startActivity(ImgBrowserActivity::class.java)
    }

    /**
     * 显示图片
     *
     * @param urls
     * @param currentPosition
     */
    fun showImages(urls: List<String>?, currentPosition: Int) {
        if (urls == null || urls.isEmpty()) {
            return
        }
        val s = arrayOfNulls<String>(urls.size)
        for (i in urls.indices) {
            s[i] = urls[i]
        }
        intent.putExtra(Constants.image.IMG_URLS, s)
        intent.putExtra(Constants.image.IMG_POSITION, currentPosition)
//		startActivity(ImgBrowserActivity::class.java)
    }

    /**
     * 预览本地图片
     *
     * @param urls
     * @param currentPosition
     */
    fun showImagesLocal(urls: List<String>?, currentPosition: Int) {
        if (urls == null || urls.isEmpty()) {
            return
        }
        val s = arrayOfNulls<String>(urls.size)
        for (i in urls.indices) {
            s[i] = urls[i]
        }
        intent.putExtra(Constants.image.IMG_URLS, s)
        intent.putExtra(Constants.image.IMG_FILE_PATHS, currentPosition)
//		startActivity(ImgBrowserActivity::class.java)
    }

//    fun requestPerm() {
//        AndPermission.with(this).permission(
//            Permission.Group.MICROPHONE,
//            Permission.Group.STORAGE,
//            Permission.Group.CAMERA,
//            Permission.Group.LOCATION
//        ).onGranted { }.onDenied { toastShowShort("拒绝麦克风和存储权限无法录像和录音，截屏"); }.start()
//    }

    /**
     * 关闭键盘
     */
    fun hintKeyBoard() {
        //拿到InputMethodManager
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //如果window上view获取焦点 && view不为空
        if (imm.isActive && currentFocus != null) {
            //拿到view的token 不为空
            if (currentFocus.windowToken != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }
}
