package com.bigbang.booksearch.database

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bigbang.booksearch.model.BookResult
import com.bigbang.booksearch.network.BookService
import com.bigbang.booksearch.util.Constants.Companion.BASE_URL
import com.bigbang.booksearch.util.DebugLogger
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository {

    private lateinit var bookService: BookService
    private lateinit var bookResultLiveData: MutableLiveData<BookResult>
    private lateinit var client: OkHttpClient

    private lateinit var dbReference: DatabaseReference
    private lateinit var favoriteBooks: MutableLiveData<List<Favorite>>

    fun BookRepository(): ??? {

        bookResultLiveData = MutableLiveData()

            var interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            bookService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookService::class.java)

        }

    fun searchBooks(query: String) {
        bookService.searchBooks(query)
            ?.enqueue(object : Callback<BookResult>) {
                fun onResponse(call: Call<BookResult>, response: Response<BookResult>) {
                    if (response.body() != null) {
                        bookResultLiveData.postValue(response.body())
                    }
                }

                fun onFailure(call: Call<BookResult>, t: Throwable) {
                    DebugLogger.logError(t)
                    bookResultLiveData.postValue(null)
                }
            }
    }

    fun insertFavorites(bFavorite: Favorite){
        val dbReference = FirebaseDatabase.getInstance().getReference().child("favorite")
        val dbKey: String = dbReference.push().key.toString()
        if(dbKey != null){
            dbReference.child(dbKey).setValue(bFavorite)
        }



    }

    fun getBookResultLiveData(): LiveData<BookResult>{
        return bookResultLiveData
    }
}