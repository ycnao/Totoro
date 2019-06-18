package com.nadia.totoro.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import com.nadia.totoro.sample.adapter.MainListAdapter
import com.nadia.totoro.sample.model.BannerData
import com.nadia.totoro.sample.presenter.TestPresenter
import com.nadia.totoro.sample.view.TestView
import kotlinx.android.synthetic.main.fragment_work.*
import java.util.ArrayList

/**
 * 核心企业看板
 */
class CoreWorkFragment : BaseFragment<MainActivity, CoreWorkFragment>(), TestView {

    private val presenter = TestPresenter()
    private val mListData = ArrayList<String>()
    private lateinit var mAdapter: MainListAdapter

    fun newInstance(index: Int): CoreWorkFragment {
        val fragment = CoreWorkFragment()
        val bundle = Bundle()
        bundle.putInt(INDEX, index)
        fragment.arguments = bundle
        return fragment
    }

    override fun initLayout() = R.layout.fragment_work

    override fun afterInjectView(savedInstanceState: Bundle?) {
        presenter.attachView(this)

        Log.d("dddd","1")
        val footerView = View.inflate(act, R.layout.item_footer, null)
        listView.addFooterView(footerView)

        mAdapter = MainListAdapter(act, mListData, R.layout.item_main)
        listView.adapter = mAdapter

        presenter.data()
        mHasLoadedOnce = true
    }

    override fun lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return
        }
        Log.d("dddd","2")

//        presenter.data()
    }

    override fun loadData(data: List<String>) {
        Log.d("dddd","3")

        mListData.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    override fun loadView(data: List<BannerData>) {

    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}