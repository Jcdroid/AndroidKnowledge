package com.jcdroid.kotlin_app.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jcdroid.kotlin_app.R

/**
 * Created by Jcdroid on 2018/11/15.
 */
class ListAdapter(layoutResId: Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_name, item)
    }
}