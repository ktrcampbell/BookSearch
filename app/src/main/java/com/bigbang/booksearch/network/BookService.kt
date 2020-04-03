package com.bigbang.booksearch.network

import com.bigbang.booksearch.model.BookResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class BookService {

    @GET("/books/v1/volumes")
    fun searchBooks(@Query("q") query: String?): Call<BookResult?>? {
        return null
    }
}