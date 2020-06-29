package com.example.viva.event

import domain.ProductDomain

/**
 * Product Clicked Event
 */
class ProductClickedEvent (
    val product: ProductDomain
)