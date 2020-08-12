package com.example.flashcardlearneng

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcardlearneng.model.Category

class HomeScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            Category("123",3,4).wordTotal = 123
    }

}