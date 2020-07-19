package com.example.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_category.view.*
import java.util.*
import kotlin.collections.ArrayList

class CategoryAdapter(private val mContext: Context, private var sortedMap: SortedMap<String, ArrayList<ProductModel>>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var categoryList: List<String> = ArrayList<String>(sortedMap.keys)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.list_item_category, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.name.text = categoryList.get(position)
        val categoryItemList: ArrayList<ProductModel>? = sortedMap[categoryList[position]]
        setCategoryAdapter(mContext, holder.mCategoryItemRecycler, categoryItemList)
    }

    private fun setCategoryAdapter(context: Context, recyclerView_product: RecyclerView, categoryItemList: ArrayList<ProductModel>?) {
        val categorySortListAdapter = categoryItemList?.let { CategorySortAdapter(context, it) }
        recyclerView_product.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_product.setHasFixedSize(true)
        recyclerView_product.itemAnimator = DefaultItemAnimator()
        recyclerView_product.adapter = categorySortListAdapter
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.textView_category_label
        val mCategoryItemRecycler: RecyclerView = view.categoryItemList
    }
}