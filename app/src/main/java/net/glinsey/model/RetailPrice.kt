package net.glinsey.model


import com.google.gson.annotations.SerializedName

data class RetailPrice(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("currencyCode")
    val currencyCode: String
)