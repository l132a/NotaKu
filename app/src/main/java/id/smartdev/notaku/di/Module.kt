package id.smartdev.notaku.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.smartdev.notaku.data.db.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    @Singleton
    fun provideContext(context: Context): Context = context

    @Provides
    @DbName
    @Singleton
    fun provideDbName() = "notaku.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context, @DbName dbName: String
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

    @Provides
    @Singleton
    fun provideCustomerDao(appDatabase: AppDatabase) = appDatabase.customerDao()

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase) = appDatabase.productDao()

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: AppDatabase) = appDatabase.transactionDao()

    @Provides
    @Singleton
    fun provideTransactionDetailDao(appDatabase: AppDatabase) = appDatabase.transactionDetailDao()

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()
}