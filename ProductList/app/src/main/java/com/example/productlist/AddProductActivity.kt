package com.example.productlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_product.*
import java.util.ArrayList

class AddProductActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val mCategory = arrayOf("Select Category", "Clothes", "Electronics", "Beauty")
    private var product: String? = null
    private var mProductList = ArrayList<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        spinner.onItemSelectedListener = this
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mCategory)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        initListener()

    }

    private fun initListener() {
        button_save.setOnClickListener {
            if (checkValidation()) {
                val productName = et_productName.text.toString().trim()
                val productPrice = et_Price.text.toString()
                val productModel = ProductModel(productName, productPrice.toInt(), product!!)
                mProductList.add(productModel)
                val intent = Intent(this, ProductsListActivity::class.java)
                intent.putParcelableArrayListExtra(AppConstants.KEY_PRODUCT_LIST, mProductList)
                startActivity(intent)
                Toast.makeText(this, getString(R.string.sucessfully_added), Toast.LENGTH_LONG).show()
                clearData()

            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position > 0) {
            product = mCategory[position]
        }
    }

    private fun checkValidation(): Boolean {
        if (et_productName.text.trim().toString().trim().isEmpty()) {
            et_productName.error = getString(R.string.please_enter_product_name)
            et_productName.requestFocus()
            return false
        }
        if (et_Price.text.trim().toString().isEmpty()) {
            et_Price.error = getString(R.string.please_enter_price)
            et_Price.requestFocus()
            return false
        }

        if (product.equals(null)) {
            Toast.makeText(this, getString(R.string.please_enter_category), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun clearData() {
        et_productName.text.clear()
        et_Price.text.clear()
        product = null
        spinner.setSelection(0)
    }
}
