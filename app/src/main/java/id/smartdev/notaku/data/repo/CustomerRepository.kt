package id.smartdev.notaku.data.repo

import androidx.lifecycle.LiveData
import id.smartdev.notaku.data.db.CustomerDao
import id.smartdev.notaku.data.model.Customer
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    customerDao: CustomerDao,
    compositeDisposable: CompositeDisposable
) {
    private val cus: CustomerDao = customerDao
    private val com: CompositeDisposable = compositeDisposable

    fun getAll(): LiveData<List<Customer>> {
        return cus.getAll()
    }

    fun getById(id: Int): LiveData<Customer> {
        return cus.getById(id)
    }

    fun insert(customer: Customer) {
        com.add(Observable.fromCallable { cus.insert(customer) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
        )
    }

    fun update(customer: Customer) {
        com.add(Observable.fromCallable { cus.update(customer) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
        )
    }

    fun delete(customer: Customer) {
        com.add(Observable.fromCallable { cus.delete(customer) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
        )
    }
}