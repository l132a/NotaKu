package id.smartdev.notaku.ui.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.scopes.FragmentScoped
import id.smartdev.notaku.data.model.Customer
import id.smartdev.notaku.databinding.ItemCustomerBinding
import javax.inject.Inject

@FragmentScoped
class CustomerAdapter @Inject constructor(
    private val listCustomer: List<Customer>,
    private val listener: (Customer) -> Unit
) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCustomerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCustomer[position], listener)
    }

    override fun getItemCount(): Int = listCustomer.size

    class ViewHolder(private val holder: ItemCustomerBinding) :
        RecyclerView.ViewHolder(holder.root) {
        fun bind(customer: Customer, listener: (Customer) -> Unit) {
            holder.tvName.text = customer.name
            holder.tvPhone.text = customer.phone

            Glide.with(itemView.context)
                .load("https://ui-avatars.com/api/?name=${customer.name}&&background=random")
                .into(holder.ivCustomer)

            itemView.setOnClickListener {
                listener(customer)
            }
        }
    }
}