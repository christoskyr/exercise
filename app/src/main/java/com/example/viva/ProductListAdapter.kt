package com.example.viva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.viva.event.ProductClickedEvent
import com.squareup.picasso.Picasso
import domain.ProductDomain
import org.greenrobot.eventbus.EventBus

/**
 * The Adapter
 */
class ProductListAdapter (
    private val data: List<ProductDomain>
) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder =
        ProductListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {

        Picasso.get()
            .load(data[position].thumbnail)
            .fit()
            .into(holder.image)

        holder.title.text = data[position].name
        holder.price.text = data[position].price

        holder.cardView.setOnClickListener {
            EventBus.getDefault().post(ProductClickedEvent(data[position]))
        }
    }

    /**
     * ProductListAdapter ViewHolder
     */
    inner class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val title: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}