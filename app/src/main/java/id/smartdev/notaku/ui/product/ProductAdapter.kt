package id.smartdev.notaku.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.scopes.FragmentScoped
import id.smartdev.notaku.data.model.Product
import id.smartdev.notaku.databinding.ItemListProductBinding
import javax.inject.Inject

@FragmentScoped
class ProductAdapter @Inject constructor(
    private val listProduct: List<Product>,
    private val listener: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listProduct[position], listener)
    }

    override fun getItemCount(): Int = listProduct.size

    class ViewHolder(private val holder: ItemListProductBinding) :
        RecyclerView.ViewHolder(holder.root) {
        fun bind(product: Product, listener: (Product) -> Unit) {

            holder.tvProductName.text = product.name
            holder.tvPrice.text = product.price.toString()

            Glide.with(itemView.context)
                .load("https://ui-avatars.com/api/?name=${product.name}&&background=random")
                .into(holder.ivProduct)

            itemView.setOnClickListener {
                listener(product)
            }
        }
    }
}