package net.glinsey.model


import com.google.gson.annotations.SerializedName

data class VolumesResponse(
    @SerializedName("items")
    val items: List<Volume>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int
)