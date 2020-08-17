package com.example.flashcardlearneng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_screen.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewTest.label = "Job"
        viewTest.percentLearned = 0
        object : CountDownTimer(100000, 100) {
            override fun onFinish() {
            }

            override fun onTick(p0: Long) {
                ++viewTest.percentLearned
                viewTest.invalidate()
            }

        }.start()

    }
}