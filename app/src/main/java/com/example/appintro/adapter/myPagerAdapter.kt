package com.example.appintro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author: Kamran Khan
 * @Date: 18,April,2023
 * @Accounts
 *      -> https://stackoverflow.com/users/17921670/kamran-khan
 */

class myPagerAdapter (activity: FragmentActivity?) : FragmentStateAdapter(activity!!)
{

    val list : MutableList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

}