package id.smartdev.notaku.data.repo

import androidx.lifecycle.LiveData
import id.smartdev.notaku.data.db.ProductDao
import id.smartdev.notaku.data.model.Product
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductRepository @Inject constructor(
    productDao: ProductDao,
    compositeDisposable: CompositeDisposable
) {
    private val pro: ProductDao = productDao
    private val com: CompositeDisposable = compositeDisposable

    fun getAll(): LiveData<List<Product>> {
        return pro.getAll()
    }

    fun getById(id: Int): LiveData<Product> {
        return pro.getById(id)
    }

    fun insert(product: Product) {
        com.add(Observable.fromCallable { pro.insert(product) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
        )
    }

    fun update(product: Product) {
        com.add(Observable.fromCallable { pro.update(product) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
        )
    }

    fun delete(product: Product) {
        com.add(Observable.fromCallable { pro.delete(product) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
        )
    }
}