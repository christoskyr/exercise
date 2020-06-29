package domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Product List Domain
 */
class ProductListDomainResponse (
    val productList: List<ProductDomain>
)

/**
 * Product tDomain
 */
class ProductDomain() : Parcelable {
    var id: Int? = null
    var name: String? = null
    var price: String? = null
    var thumbnail: String? = null
    var image: String? = null
    var description: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        price = parcel.readString()
        thumbnail = parcel.readString()
        image = parcel.readString()
        description = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(thumbnail)
        parcel.writeString(image)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDomain> {
        override fun createFromParcel(parcel: Parcel): ProductDomain {
            return ProductDomain(parcel)
        }

        override fun newArray(size: Int): Array<ProductDomain?> {
            return arrayOfNulls(size)
        }
    }
}

