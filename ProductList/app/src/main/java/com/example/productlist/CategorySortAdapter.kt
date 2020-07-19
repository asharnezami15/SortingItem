package com.example.productlist

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_items_sort_order.view.*

class CategorySortAdapter(private val mContext: Context, private val mCategoryItemList: ArrayList<ProductModel>):
    RecyclerView.Adapter<CategorySortAdapter.SortViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortViewHolder {
      return SortViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_items_sort_order, parent, false))
    }

    override fun getItemCount(): Int {
       return mCategoryItemList.size

    }

    override fun onBindViewHolder(holder: SortViewHolder, position: Int) {
        val listCategory = mCategoryItemList[position]
        holder.productName.text = "Name: "+listCategory.productName
        holder.price.text = "Price: "+listCategory.price
    }

    class SortViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productName: TextView = view.text_name
        val price: TextView = view.text_Price
    }
}