package com.example.flashcardlearneng.process

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardlearneng.model.Category
import java.util.*

class CategoryAdapter(private var dataset: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}