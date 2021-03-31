package id.smartdev.notaku.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.smartdev.notaku.data.model.Customer
import id.smartdev.notaku.data.model.Product
import id.smartdev.notaku.data.model.Transaction
import id.smartdev.notaku.data.model.TransactionDetail
import id.smartdev.notaku.util.Converters

@Database(
    entities = [Customer::class, Transaction::class, TransactionDetail::class, Product::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionDetailDao(): TransactionDetailDao
    abstract fun productDao(): ProductDao
}