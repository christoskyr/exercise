package network

import ext.response.ProductDTO
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface for retrofit to retrieve products list
 */
interface RestBackEndService {
    @GET("products")
    fun getProductList(): Call<List<ProductDTO>>
}