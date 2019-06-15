package com.nadia.totoro.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public abstract class NAbstractAdapter extends BaseAdapter {

    protected List<Object> mData;

    protected Context mContext;

    public NAbstractAdapter(Context context, List<Object> data) {
        mData = data;
        mContext = context;
    }

    /**
     * 清空数据源
     */
    public void clean() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取其对应的fragment。记得转型回来。
     *
     * @param <T>
     * @param t
     * @return
     */
    @SuppressWarnings("RestrictedApi")
    public <T> Fragment getItFragmentInstance(Class<T> t) {
        FragmentActivity act = (FragmentActivity) mContext;
        List<Fragment> fragments = act.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment.getClass() == t) {
                return fragment;
            }
        }
        return null;
    }

    /**
     * 执行ListView的getView方法。
     *
     * @param position
     * @param convertView
     * @param parent
     */
    public abstract View getListItemView(int position, View convertView, ViewGroup parent);

    /*
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /*
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int arg0) {
        return mData.get(arg0);
    }

    /*
     * (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getListItemView(position, convertView, parent);
    }
}
