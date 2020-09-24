package com.example.parayo.inquiry.myinquiry

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyInquiryPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT

) {

    private val fragments = InquiryPage.values()
        .map { InquiryListFragment.newInstance(it) }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) =
        fragments[position].inquiryPage?.title

}