package com.example.productlist

import android.os.Parcel
import android.os.Parcelable

data class ProductModel(
    var productName: String,
    var price: Int?,
    var category: String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productName)
        parcel.writeInt(price!!)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }

}