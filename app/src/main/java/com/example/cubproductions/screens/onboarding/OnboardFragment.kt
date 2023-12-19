package com.example.cubproductions.screens.onboarding

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.cubproductions.R
import com.example.cubproductions.adapter.ViewPagerAdapter
import com.example.cubproductions.data.OnboardingData
import com.example.cubproductions.databinding.FragmentOnboardBinding
import com.google.android.material.tabs.TabLayout


class OnboardFragment : Fragment() {

    private lateinit var onboardingViewPagerAdapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var onboardingViewPager: ViewPager
    private var position: Int = 0
    private lateinit var binding: FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardBinding.inflate(inflater, container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tabLayout = binding.tabIndicator

        val onBoardingData: MutableList<OnboardingData> = ArrayList()

        onBoardingData.add(
            OnboardingData(
                R.drawable.pick,
                getString(R.string.pick),
                getString(R.string.onedesc)
            )
        )

        onBoardingData.add(
            OnboardingData(
                R.drawable.greatethings,
                getString(R.string.onboardtwo),
                getString(R.string.twodesc)
            )
        )

        onBoardingData.add(
            OnboardingData(
                R.drawable.getproduct,
                getString(R.string.onboardthree),
                getString(R.string.threedesc)
            )
        )


        setOnboardingViewPager(onBoardingData)

        position = onboardingViewPager.currentItem

        val next = binding.button

        next.setOnClickListener {
            if (position < onBoardingData.size){
                position++
                onboardingViewPager.currentItem = position
            }
            if(position == 3){
                onBoardingFinished()
                findNavController().navigate(
                    R.id.action_onboardFragment_to_loginFragment)
            }
            Log.d("position","$position")
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                position = tab.position
                if (tab.position == 2) {
                    next.text = "Get Started"
                } else {
                    next.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding",
            Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    private fun setOnboardingViewPager(onboardingData: List<OnboardingData>){

        onboardingViewPager = binding.screenPager
        onboardingViewPagerAdapter = ViewPagerAdapter(requireContext(), onboardingData)
        onboardingViewPager.adapter = onboardingViewPagerAdapter
        tabLayout.setupWithViewPager(onboardingViewPager)
    }

}