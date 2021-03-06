package com.example.flashcardlearneng.process

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardlearneng.R
import com.example.flashcardlearneng.model.Category
import kotlinx.android.synthetic.main.item_category.view.*
import java.util.*


class CategoryAdapter(private var dataset: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_category, parent, false)
        Log.d("recyclerView","oncreateView")
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(dataset[position])
        Log.d("recyclerView","onBindView")
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data:Category){
            itemView.categoryView.data = data
        }
    }

}