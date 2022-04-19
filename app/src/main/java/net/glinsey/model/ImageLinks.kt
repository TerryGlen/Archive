package net.glinsey.model


import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String
){
    /** By default Google returns thumbnails with http
     *  Since Android 9.0 plaintext traffic is disabled by default
     *  This is a quick fix, that can be made with custom deserialization
     */
    fun smallThumbnailSecure() : String{
        return smallThumbnail.replace("http","https")
    }
    fun thumbnailSecure() : String{
        return thumbnail.replace("http","https")

    }
}