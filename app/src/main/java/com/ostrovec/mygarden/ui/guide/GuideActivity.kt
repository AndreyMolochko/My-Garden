package com.ostrovec.mygarden.ui.guide

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityGuideBinding
import com.ostrovec.mygarden.ui.base.BaseActivity

class GuideActivity : BaseActivity() {

    private lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_guide)
        initViews()
    }

    private fun initViews() {
        val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        binding.guideViewPager.adapter = viewPagerAdapter
        viewPagerAdapter.setPages(getPagesFragments())
    }

    private fun getPagesFragments(): List<Fragment> {
        val pages : List<Fragment> = arrayListOf()
        val firstFragment: PageFragment = PageFragment.getNewInstance("Sign up and Sign in",
        R.drawable.ic_launcher_background)
        val secondFragment: PageFragment = PageFragment.getNewInstance("Add plants",
                R.drawable.ic_launcher_background)
        val thirdFragment: PageFragment = PageFragment.getNewInstance("Get notification",
                R.drawable.ic_launcher_background)
        (pages as ArrayList).add(firstFragment)
        pages.add(secondFragment)
        pages.add(thirdFragment)

        return pages
    }
}
