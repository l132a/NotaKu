package id.smartdev.notaku.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.smartdev.notaku.data.model.Product
import id.smartdev.notaku.data.repo.ProductRepository
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {
    private var triggerFetchData = MutableLiveData<Boolean>()
    private var product: LiveData<List<Product>> = Transformations.switchMap(triggerFetchData) {
        repo.getAll()
    }

    fun getProducts(): LiveData<List<Product>> {
        return product
    }

    fun insertCustomer(product: Product) {
        repo.insert(product)
    }

    fun updateCustomer(product: Product) {
        repo.update(product)
    }

    fun loadCustomer() {
        triggerFetchData.value = true
    }

    fun deleteCustomer(product: Product) {
        repo.delete(product)
    }
}