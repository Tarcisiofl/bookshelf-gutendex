package br.com.tarcisiofl.bookshelf.di

import androidx.room.Room
import br.com.tarcisiofl.bookshelf.data.local.BookshelfDatabase
import br.com.tarcisiofl.bookshelf.data.local.BookshelfDatabase.Companion.DB_NAME
import br.com.tarcisiofl.bookshelf.data.local.Converters
import br.com.tarcisiofl.bookshelf.data.remote.BookApi
import br.com.tarcisiofl.bookshelf.data.remote.BookApi.Companion.BASE_URL
import br.com.tarcisiofl.bookshelf.data.repository.BooksRepository
import br.com.tarcisiofl.bookshelf.presentation.details.BookDetailsViewModel
import br.com.tarcisiofl.bookshelf.presentation.listing.BookListingViewModel
import com.squareup.moshi.Moshi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    addHttpBuilders()
    addDatabase()
    addRepository()
    addData()
    addViewModels()
}

private fun Module.addHttpBuilders() {
    single {
        Moshi.Builder().build()
    }
    single { Converters(moshi = get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

private fun Module.addDatabase() {
    single {
        Room.databaseBuilder(
            androidApplication(),
            BookshelfDatabase::class.java,
            DB_NAME
        ).addTypeConverter(get<Converters>()).build()
    }
}

private fun Module.addData() {
    single<BookApi> { get<Retrofit>().create(BookApi::class.java) }
}

private fun Module.addRepository() {
    single { BooksRepository(bookshelfDatabase = get(), bookApi = get()) }
}

private fun Module.addViewModels() {
    viewModel { BookListingViewModel(repository = get()) }
    viewModel { BookDetailsViewModel(repository = get()) }
}