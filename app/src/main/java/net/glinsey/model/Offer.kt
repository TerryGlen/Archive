package net.glinsey.model


import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("finskyOfferType")
    val finskyOfferType: Int,
    @SerializedName("giftable")
    val giftable: Boolean,
    @SerializedName("listPrice")
    val listPrice: ListPrice,
    @SerializedName("rentalDuration")
    val rentalDuration: RentalDuration,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice
)