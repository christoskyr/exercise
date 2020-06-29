package ext.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Product DTO
 */
data class ProductDTO (
    @field:SerializedName("Id")
    val Id: Int?,

    @field:SerializedName("Name")
    val Name: String?,

    @field:SerializedName("Price")
    val Price: String?,

    @field:SerializedName("Thumbnail")
    val Thumbnail: String?,

    @field:SerializedName("Image")
    val Image: String?,

    @field:SerializedName("Description")
    val Description: String?
) : Serializable