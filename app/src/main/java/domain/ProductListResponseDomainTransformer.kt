package domain

import ext.response.ProductDTO

/**
 * ProductList Response Domain Transformer
 */
object ProductListResponseDomainTransformer {

    @JvmStatic
    fun transform (source : List<ProductDTO>?) : ProductListDomainResponse =
        ProductListDomainResponse(transformProductList(source)!!)


    private fun transformProductList(productList: List<ProductDTO>?): List<ProductDomain>? =
        productList?.map {
            ProductDomain().apply {
                id = it.Id
                name = it.Name
                price = it.Price
                thumbnail = it.Thumbnail
                image = it.Image
                description = it.Description
            }
        }
}