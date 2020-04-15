package com.bigbang.booksearch.network

import com.bigbang.booksearch.model.BookResult
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/books/v1/volumes")
    fun getGBooks(@Query("q") query: String): Observable<BookResult>
}