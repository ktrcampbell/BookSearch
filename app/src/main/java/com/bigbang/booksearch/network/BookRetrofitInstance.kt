package com.bigbang.booksearch.network

import com.bigbang.booksearch.model.BookResult
import com.bigbang.booksearch.network.BookService
import com.bigbang.booksearch.util.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BookRetrofitInstance {
    private val bookService: BookService
    private val client: OkHttpClient
    private val retrofitInstance: Retrofit
        private get() = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun createGoogleBooksService(retrofitInstance: Retrofit): BookService {
        return retrofitInstance.create(BookService::class.java)
    }

    fun getGBooks(query: String): Observable<BookResult> {
        return bookService.getGBooks(query)
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        bookService = createGoogleBooksService(retrofitInstance)
    }
}