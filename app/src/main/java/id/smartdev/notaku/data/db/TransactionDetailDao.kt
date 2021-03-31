package id.smartdev.notaku.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.smartdev.notaku.data.model.TransactionDetail

@Dao
interface TransactionDetailDao {
    @Query("SELECT * FROM transaction_details ORDER BY id ASC")
    fun getAll(): LiveData<List<TransactionDetail>>
}