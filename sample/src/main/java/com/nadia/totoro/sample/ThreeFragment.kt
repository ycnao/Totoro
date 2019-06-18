package com.nadia.totoro.sample

import android.os.Bundle
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
class ThreeFragment : BaseFragment<MainActivity, ThreeFragment>(), TestView {

    private val presenter = TestPresenter()
    private val mListData = ArrayList<String>()
    private lateinit var mAdapter: MainListAdapter

    fun newInstance(index: Int): ThreeFragment {
        val fragment = ThreeFragment()
        val bundle = Bundle()
        bundle.putInt(INDEX, index)
        fragment.arguments = bundle
        return fragment
    }

    override fun initLayout() = R.layout.fragment_work

    override fun afterInjectView(savedInstanceState: Bundle?) {
        presenter.attachView(this)

        val footerView = View.inflate(act, R.layout.item_footer, null)
        listView.addFooterView(footerView)

        mAdapter = MainListAdapter(act, mListData, R.layout.item_main)
        listView.adapter = mAdapter
    }

    override fun lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return
        }
        presenter.data()
        mHasLoadedOnce = true
    }

    override fun loadData(data: List<String>) {
        mListData.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    override fun loadView(data: List<BannerData>) {}

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}