package com.ostrovec.mygarden.ui.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.FragmentPageBinding

class PageFragment : Fragment() {

    companion object {
        private val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        private val IMAGE_KEY = "IMAGE_KEY"

        fun getNewInstance(description: Int, image: Int): PageFragment {
            val pageFragment = PageFragment()
            val bundle = Bundle()
            bundle.putInt(DESCRIPTION_KEY, description)
            bundle.putInt(IMAGE_KEY, image)
            pageFragment.arguments = bundle

            return pageFragment
        }
    }

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }


    private fun initViews() {
        binding.fragmentPageDescriptionTextView.text = getString(arguments!!.getInt
        (DESCRIPTION_KEY))
        binding.fragmentPageImageView.setImageResource(arguments!!.getInt(IMAGE_KEY))
    }

}