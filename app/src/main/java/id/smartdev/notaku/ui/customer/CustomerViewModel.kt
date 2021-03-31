package id.smartdev.notaku.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.smartdev.notaku.data.model.Customer
import id.smartdev.notaku.data.repo.CustomerRepository
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val repo: CustomerRepository
) : ViewModel() {
    private var triggerFetchData = MutableLiveData<Boolean>()
    private var customers: LiveData<List<Customer>> = Transformations.switchMap(triggerFetchData) {
        repo.getAll()
    }

    fun getCustomers(): LiveData<List<Customer>> {
        return customers
    }

    fun insertCustomer(customer: Customer) {
        repo.insert(customer)
    }

    fun updateCustomer(customer: Customer) {
        repo.update(customer)
    }

    fun loadCustomer() {
        triggerFetchData.value = true
    }

    fun deleteCustomer(customer: Customer) {
        repo.delete(customer)
    }
}