package com.example.flashcardlearneng

import android.os.Bundle
import android.util.LayoutDirection
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcardlearneng.model.Category
import com.example.flashcardlearneng.process.CategoryAdapter
import com.example.flashcardlearneng.process.TestClass
import kotlinx.android.synthetic.main.home_screen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        var listData:ArrayList<Category> = ArrayList()

        listData.add(Category("Person",30,30))
        listData.add(Category("Country",19,30))
        listData.add(Category("Job",10,30))
        listData.add(Category("School",20,70))
        listData.add(Category("Person",30,30))
        listData.add(Category("Country",19,30))
        listData.add(Category("Job",10,30))
        listData.add(Category("School",20,70))

        listData.add(Category("Person",30,30))
        listData.add(Category("Country",19,30))
        listData.add(Category("Job",10,30))
        listData.add(Category("School",20,70))
        listData.add(Category("Person",30,30))
        listData.add(Category("Country",19,30))
        listData.add(Category("Job",10,30))
        listData.add(Category("School",20,70))


        recyclerListCategory.adapter = CategoryAdapter(listData)
        recyclerListCategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false )



/*        var listData:ArrayList<String> = ArrayList()

        listData.add("ahii1")
        listData.add("ahii2")
        listData.add("ahii3")
        listData.add("ahii4")
        listData.add("ahii5")
        listData.add("ahii6")
        listData.add("ahii7")
        listData.add("ahii8")
        listData.add("ahii9")
        listData.add("ahii10")
        listData.add("ahii11")



        recyclerListCategory.adapter =TestClass(listData)
        recyclerListCategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false )*/


    }

}