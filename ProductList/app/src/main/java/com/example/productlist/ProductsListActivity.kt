package com.example.productlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_products_list.*
import java.util.*
import kotlin.collections.ArrayList

class ProductsListActivity : AppCompatActivity() {

    private var mProductList = ArrayList<ProductModel>()
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        getBundleData()
        initRecyclerView()
    }

    private fun getBundleData() {
        val bundle = intent?.extras
        mProductList = bundle?.getParcelableArrayList(AppConstants.KEY_PRODUCT_LIST)!!
    }

    private fun initRecyclerView() {
        productListAdapter = ProductListAdapter(this, mProductList)
        recyclerView_product.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView_product.setHasFixedSize(true)
        recyclerView_product.itemAnimator = DefaultItemAnimator()
        recyclerView_product.adapter = productListAdapter
    }

    private fun sortByAscendingOrder() {
        mProductList.sortBy { it.productName.toLowerCase() }
        productListAdapter.notifyDataSetChanged()
        initRecyclerView()
    }

    private fun sortByPrice() {
        mProductList.sortBy {it.price }
        productListAdapter.notifyDataSetChanged()
        initRecyclerView()
    }

    private fun sortByCategory() {
        mProductList.let {
            if (it.isNotEmpty()) {
                val sortedMap: SortedMap<String, ArrayList<ProductModel>> = sortedMapOf()
                for (i in mProductList) {
                    val category = i.category
                    if (sortedMap.containsKey(category)) {
                        val itemList = sortedMap[category]
                        itemList?.add((itemList.size - 1), i)
                        sortedMap[category] = itemList
                    } else {
                        val itemList: ArrayList<ProductModel> = arrayListOf()
                        itemList.add(i)
                        sortedMap[category] = itemList
                    }
                }
                setCategoryAdapter(sortedMap)

            }
        }

    }

    private fun setCategoryAdapter(sortedMap: SortedMap<String, ArrayList<ProductModel>>) {
        val categoryListAdapter = CategoryAdapter(this, sortedMap)
        recyclerView_product.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView_product.setHasFixedSize(true)
        recyclerView_product.itemAnimator = DefaultItemAnimator()
        recyclerView_product.adapter = categoryListAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_filter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.a_to_z -> {
                sortByAscendingOrder()
            }

            R.id.low_to_high -> {
                sortByPrice()
            }

            R.id.by_category -> {
                sortByCategory()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
