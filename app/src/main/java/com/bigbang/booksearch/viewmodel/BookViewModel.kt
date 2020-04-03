package com.bigbang.booksearch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bigbang.booksearch.database.BookRepository
import com.bigbang.booksearch.database.Favorite
import com.bigbang.booksearch.model.BookResult

class BookViewModel(application: Application): AndroidViewModel(application) {

    private var bookRepository: BookRepository
    private var bookResultLiveData: LiveData<BookResult>

    init {
        bookRepository = BookRepository()
        bookResultLiveData = bookRepository.getBookResultLiveData()
    }

    fun searchBooks(query: String){
        bookRepository.searchBooks(query)
    }

    fun insertFavorites(favorite: Favorite){
        bookRepository.insertFavorites(favorite)
    }

    fun getBookResultLiveData(): LiveData<BookResult>{
        return bookResultLiveData
    }
}