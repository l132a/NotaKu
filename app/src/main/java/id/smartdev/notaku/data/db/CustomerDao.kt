package id.smartdev.notaku.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import id.smartdev.notaku.data.model.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers ORDER BY id ASC")
    fun getAll(): LiveData<List<Customer>>

    @Query("SELECT * FROM customers WHERE id = :id")
    fun getById(id: Int): LiveData<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(customer: List<Customer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(customer: Customer)

    @Update
    fun update(customer: Customer)

    @Delete
    fun delete(customer: Customer)
}