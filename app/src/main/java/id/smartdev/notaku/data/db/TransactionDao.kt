package id.smartdev.notaku.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.smartdev.notaku.data.model.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY id ASC")
    fun getAll(): LiveData<List<Transaction>>
}