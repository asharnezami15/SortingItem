package com.example.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_items_product_list.view.*


class ProductListAdapter(private val mContext: Context, private val productList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_items_product_list, parent, false))

    }

    override fun getItemCount(): Int {
       return productList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val totalList = productList[position]
        holder.textName?.text =  "Name: "+totalList.productName
        holder.textPrice?.text = "Price: "+ totalList.price
        holder.textCategory?.text  = "Category: " +totalList.category



    }

    class ViewHolder(view:View?): RecyclerView.ViewHolder(view!!) {
        val textName = view?.textName
        val textPrice = view?.textPrice
        val textCategory = view?.textCategory
    }
}



