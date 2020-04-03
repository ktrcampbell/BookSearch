package com.bigbang.booksearch.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView
import com.bigbang.booksearch.R
import com.bigbang.booksearch.R.drawable.launch_screen

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(launch_screen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
