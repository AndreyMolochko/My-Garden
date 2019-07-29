package com.ostrovec.mygarden.ui.guide

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityGuideBinding
import com.ostrovec.mygarden.ui.base.BaseActivity
import com.ostrovec.mygarden.ui.welcome.WelcomeActivity
import com.ostrovec.mygarden.utils.SharedPrefsWoker

class GuideActivity : BaseActivity() {

    val onClickOK: GuideHandler = object : GuideHandler{
        override fun onClickOK(context: Context) {
                WelcomeActivity.open(context)
                SharedPrefsWoker(context).setShownGuideActivity(false)
        }

    }

    private lateinit var binding: ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!SharedPrefsWoker(this).shownGuide()) {
            WelcomeActivity.open(this@GuideActivity)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_guide)
        initViews()
    }

    private fun initViews() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        binding.guideViewPager.adapter = viewPagerAdapter
        viewPagerAdapter.setPages(getPagesFragments())
        binding.guideTabLayout.setupWithViewPager(binding.guideViewPager)
        binding.handler = onClickOK
    }

    private fun getPagesFragments(): List<Fragment> {
        val guideSignUpFragment = PageFragment.getNewInstance(R.string
                .guide_text_sign_up,
                R.drawable.ic_log_in)
        val guideAddPlantsFragment = PageFragment.getNewInstance(R.string
                .guide_text_add_plants,
                R.drawable.ic_flower_pot)
        val guideNotificationFragment = PageFragment.getNewInstance(R.string
                .guide_text_turn_notification,
                R.drawable.ic_message)
        return arrayListOf(guideSignUpFragment, guideAddPlantsFragment, guideNotificationFragment)
    }
}
