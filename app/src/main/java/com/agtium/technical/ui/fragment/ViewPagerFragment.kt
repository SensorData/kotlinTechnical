package com.agtium.technical.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.agtium.technical.databinding.FragmentViewpagerBinding
import java.lang.Exception

class ViewPagerFragment: Fragment() {

    private lateinit var binding: FragmentViewpagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewpagerBinding.inflate(inflater, container, false)
        binding.viewpager.adapter = MyPagerAdapter(requireActivity(), ViewPagerFragmentEnum.values().toList())
        binding.viewpager.setPageTransformer(ZoomOutPageTransformer())
        return binding.root
    }


    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.75f
        private val MIN_ALPHA = 0.5f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                when {
                    position < -1 -> alpha = 0f
                    position <= 1 -> {
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                        alpha = (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> alpha = 0f
                }
            }
        }
    }

    inner class MyPagerAdapter(fa: FragmentActivity, private val items: List<ViewPagerFragmentEnum>) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return items.size
        }
        override fun createFragment(position: Int): Fragment {
            return items[position].value()
        }

    }
}

enum class ViewPagerFragmentEnum(val value: ()->Fragment) {
    ONE({OneFragment()}),
    TWO({TwoFragment()}),
    THREE({ThreeFragment()})
}