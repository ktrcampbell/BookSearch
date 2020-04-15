package com.bigbang.booksearch.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigbang.booksearch.R
import com.bigbang.booksearch.adapter.BookAdapter
import com.bigbang.booksearch.model.BookItem
import com.bigbang.booksearch.model.BookResult
import com.bigbang.booksearch.model.FavoriteBook
import com.bigbang.booksearch.util.DebugLogger.Companion.logError
import com.bigbang.booksearch.viewmodel.BookViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.book_item_layout.*
import kotlinx.android.synthetic.main.book_search_frag_layout.*

class SearchFragment: Fragment(){

    private var compDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var viewModel: BookViewModel
    private lateinit var adapter: BookAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_search_frag_layout, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookAdapter(ArrayList())
        search_results_recyclerview.adapter = adapter
        search_results_recyclerview.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        search_button.setOnClickListener (View.OnClickListener { performSearch() })

    }
    fun performSearch() {

        compDisposable.add(
            viewModel.getGBooks(search_edittext.text.toString().trim()).subscribe({ displayBooks->
                displayBooks(displayBooks)

            }, {
                    throwable:Throwable?->
                throwable?.let { logError(it) }
            })
        )
    }

    private fun displayBooks(displayBooks: BookResult) {
        adapter.bookList = displayBooks.items
        adapter.notifyDataSetChanged()
        compDisposable.clear()
    }

}