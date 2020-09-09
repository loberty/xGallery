package cn.loberty.xgallery.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Create by WangChen on 2020/9/8
 *
 */
data class Pixabay(
    val totalHits:Int,
    val hits:Array<PhotoItem>,
    val total:Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (!hits.contentEquals(other.hits)) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + hits.contentHashCode()
        result = 31 * result + total
        return result
    }
}

@Parcelize
data class PhotoItem(
    @SerializedName("id") val photoId:Int,
    @SerializedName("webformatURL") val previewUrl:String,
    @SerializedName("largeImageURL") val fullUrl:String
        /*id": 3063284,
    "previewURL": "https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg",
    "previewWidth": 150,
    "previewHeight": 99,
    "webformatURL": "https://pixabay.com/get/55e0d340485aa814f1dc846096293f7c173fdde6514c704c7c267ed19149c45b_640.jpg",
    "webformatWidth": 640,
    "webformatHeight": 426,
    "largeImageURL": "https://pixabay.com/get/55e0d340485aa814f6da8c7dda793676173ddee650536c48702672d1924bc159bb_1280.jpg",
    "imageWidth": 6000,
    "imageHeight": 4000,
    "imageSize": 3574625,
    "views": 778318,
    "downloads": 491662,
    "favorites": 1029,
    "likes": 1182,
    "comments": 267,
    "user_id": 1564471,
    "user": "anncapictures",
    "userImageURL": "https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg"*/
): Parcelable