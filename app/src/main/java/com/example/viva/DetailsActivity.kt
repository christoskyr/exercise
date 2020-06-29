package com.example.viva

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.ProductDomain

/**
 * The Details Activity
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailsActivity : AppCompatActivity() {

    companion object {
        const val MODEL = "MODEL"
    }

    /**
     * Views
     */
    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView

    private lateinit var mModel: ProductDomain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        setUpToolbar()
        productName = findViewById<TextView>(R.id.productName)
        productPrice = findViewById<TextView>(R.id.productPrice)
        productDescription = findViewById<TextView>(R.id.productDescription)

        val args: Bundle = intent.extras
        mModel = if (savedInstanceState != null) {
            savedInstanceState.getParcelable(MODEL)
        } else {
            args.getParcelable(MODEL)
        }

        initUI()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpToolbar() {
        val actionbar = supportActionBar
        actionbar!!.title = baseContext.getString(R.string.details_activity_title)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun initUI() {
        productName.text = mModel.name
        productPrice.text = mModel.price
        productDescription.text = mModel.description
    }

}
