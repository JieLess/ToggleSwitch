package com.example.toggleswitch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf("Auto", "On", "Off")
        val list2 = listOf("High", "Medium", "Low")

        tsNightVisionMode.setTitles(list)
        tsNightVisionMode.setOnToggleSwitchChangeListener { position: Int ->
            Log.e("log", "cur position $position")
        }
        tsNightVisionMode.setCheckedTogglePosition(2)

        tsLevel.setTitles(list2)
        tsLevel.setCheckedTogglePosition(0)
    }
}
