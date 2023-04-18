package com.example.appintro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.appintro.adapter.myPagerAdapter
import com.example.appintro.databinding.ActivityMainBinding
import com.example.appintro.fragment.Page_1
import com.example.appintro.fragment.Page_2
import com.example.appintro.fragment.Page_3

class MainActivity : AppCompatActivity() {
    val fragment1 = Page_1()
    val fragment2 = Page_2()
    val fragment3 = Page_3()
    lateinit var adapter: myPagerAdapter
    val pref_show_intro = "Intro"
    lateinit var preference : SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        preference = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)


        if (!preference.getBoolean(pref_show_intro,true))
        {
//            startActivity(Intent(this@MainActivity,WelcomeActivity::class.java))
//            finish()
        }

        adapter = myPagerAdapter(this)

        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)

        binding.viewPager.adapter = adapter
        binding.btnNext.setOnClickListener {
            binding.viewPager.currentItem++
        }

        binding.btnSkip.setOnClickListener {
            //GoToWelcomeScree()
        }

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)

        val onPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // This method will be invoked when the current page is scrolled
            }

            override fun onPageSelected(position: Int) {
                if (position == adapter.list.size-1)
                {
                    //last Page
                    binding.btnNext.text = "DONE"
                    binding.btnNext.setOnClickListener {
                        // GoToWelcomeScree()
                    }
                }
                else
                {
                    //has next
                    binding.btnNext.text = "NEXT"
                    binding.btnNext.setOnClickListener {
                        binding.viewPager.currentItem++
                    }
                }

                when( binding.viewPager.currentItem)
                {
                    0->
                    {
                        binding.indicator1.setTextColor(Color.BLACK)
                        binding.indicator2.setTextColor(Color.GRAY)
                        binding.indicator3.setTextColor(Color.GRAY)
                    }
                    1->
                    {
                        binding.indicator1.setTextColor(Color.GRAY)
                        binding.indicator2.setTextColor(Color.BLACK)
                        binding.indicator3.setTextColor(Color.GRAY)
                    }
                    2->
                    {
                        binding.indicator1.setTextColor(Color.GRAY)
                        binding.indicator2.setTextColor(Color.GRAY)
                        binding.indicator3.setTextColor(Color.BLACK)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // This method will be invoked when the scroll state changes
            }
        }

        viewPager.registerOnPageChangeCallback(onPageChangeListener)


    }
}