package id.smartdev.notaku.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import id.smartdev.notaku.data.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getById(id: Int): LiveData<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(product: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)
}