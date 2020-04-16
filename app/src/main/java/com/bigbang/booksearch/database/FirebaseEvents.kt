package com.bigbang.booksearch.database

import com.bigbang.booksearch.model.FavoriteBook
import com.bigbang.booksearch.util.DebugLogger.Companion.logDebug
import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlin.collections.ArrayList

class FirebaseEvents {
    private val booksReference: DatabaseReference
    private val bookListObservable = PublishSubject.create<List<FavoriteBook>>()
    fun addNewBook(book: FavoriteBook) {
        val bookKey = booksReference.push().key
        if (bookKey != null) booksReference.child(bookKey).setValue(book) else logDebug("db update failed")
    }

    //logDebug("Book List Size before return " + bookList.size());
    val favBookList: Observable<List<FavoriteBook>>
        get() {
            val bookList: MutableList<FavoriteBook> = ArrayList()
            booksReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    logDebug("inside FirebaseHelper -> onDataChange()")
                    for (currentSnap in dataSnapshot.children) {
                        val currentBook = currentSnap.getValue(FavoriteBook::class.java)!!
                        bookList.add(currentBook)
                    }
                    bookListObservable.onNext(bookList)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    logDebug("db error")
                }
            })
            //logDebug("Book List Size before return " + bookList.size());
            return bookListObservable
        }

    init {
        booksReference = FirebaseDatabase.getInstance().reference.child("books")
    }
}