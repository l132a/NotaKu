package id.smartdev.notaku.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.smartdev.notaku.data.model.Customer
import id.smartdev.notaku.data.repo.CustomerRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repoCustomer: CustomerRepository
): ViewModel() {
    private var triggerFetchData = MutableLiveData<Boolean>()
    private var customers: LiveData<List<Customer>> = Transformations.switchMap(triggerFetchData) {
        repoCustomer.getAll()
    }

    fun getCustomers(): LiveData<List<Customer>> {
        return customers
    }

    fun loadData() {
        triggerFetchData.value = true
    }
}