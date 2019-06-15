package com.nadia.totoro.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单数组适配器。 数据源：ArrayList<String>
 *
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
public abstract class NArrayAdapter extends NAbstractAdapter {

    /**
     * 构造函数
     *
     * @param context
     * @param data
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public NArrayAdapter(Context context, ArrayList<String> data) {
        super(context, (List) data);
    }
}
