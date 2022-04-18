package net.glinsey.model


import com.google.gson.annotations.SerializedName

data class RentalDuration(
    @SerializedName("count")
    val count: Int,
    @SerializedName("unit")
    val unit: String
)