package com.nadia.totoro.widget.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 图片浏览器
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public class BannerAdapter extends PagerAdapter {
    
    private int size;
    private List<View> viewList;
    private final int cacheCount = 3;
    
    public BannerAdapter(List<View> viewList) {
        this.viewList = viewList;
        size = viewList.size();
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewList.size() > cacheCount){
            container.removeView(viewList.get(position%size));
        }
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup parent = (ViewGroup) viewList.get(position%size).getParent();
        if (parent != null) {
            parent.removeView(viewList.get(position%size));
        }
        container.addView(viewList.get(position%size));
        return viewList.get(position%size);
    }
    
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}