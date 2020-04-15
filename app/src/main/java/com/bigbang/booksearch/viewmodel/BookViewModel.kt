package com.bigbang.booksearch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bigbang.booksearch.database.FirebaseEvents
import com.bigbang.booksearch.model.BookResult
import com.bigbang.booksearch.model.FavoriteBook
import com.bigbang.booksearch.network.BookRetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var retrofitInstance: BookRetrofitInstance
    private lateinit var firebaseEvents: FirebaseEvents

    fun getGBooks(query: String): Observable<BookResult> {
        return retrofitInstance
            .getGBooks(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun addBook(book: FavoriteBook) {
        firebaseEvents.addNewBook(book)
    }

    init {
        retrofitInstance = BookRetrofitInstance()
        firebaseEvents = FirebaseEvents()
    }
}
