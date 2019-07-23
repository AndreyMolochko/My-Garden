package com.ostrovec.mygarden.ui.guide

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        binding.guideViewPager.adapter = viewPagerAdapter
        viewPagerAdapter.setPages(getPagesFragments())
        binding.guideTabLayout.setupWithViewPager(binding.guideViewPager)
    }

    private fun getPagesFragments(): List<Fragment> {
        val pages: List<Fragment> = arrayListOf()
        val firstFragment: PageFragment = PageFragment.getNewInstance(R.string.guide_text_sign_up,
                R.drawable.ic_log_in)
        val secondFragment: PageFragment = PageFragment.getNewInstance(R.string.guide_text_add_plants,
                R.drawable.ic_flower_pot)
        val thirdFragment: PageFragment = PageFragment.getNewInstance(R.string.guide_text_turn_notification,
                R.drawable.ic_message)
        (pages as ArrayList).add(firstFragment)
        pages.add(secondFragment)
        pages.add(thirdFragment)

        return pages
    }
}
