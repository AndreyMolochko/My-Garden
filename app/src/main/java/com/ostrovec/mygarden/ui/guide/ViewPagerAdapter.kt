package com.ostrovec.mygarden.ui.guide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private var pages: List<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return pages.get(position)
    }

    override fun getCount(): Int {
        return pages.size
    }

    fun setPages(pages: List<Fragment>) {
        this.pages = pages
        notifyDataSetChanged()
    }

}