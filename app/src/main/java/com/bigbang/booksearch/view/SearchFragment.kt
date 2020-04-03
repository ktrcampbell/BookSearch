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
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigbang.booksearch.R
import com.bigbang.booksearch.adapter.BookAdapter
import com.bigbang.booksearch.model.BookItem
import com.bigbang.booksearch.model.BookResult
import com.bigbang.booksearch.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.book_search_frag_layout.*

class SearchFragment: Fragment(), BookAdapter.BookClickListener {

    private lateinit var viewModel: BookViewModel
    private lateinit var adapter: BookAdapter

    private lateinit var searchButton: Button
    private lateinit var keywordEditText: EditText

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
        viewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        viewModel.init()
        viewModel.getBookResultLiveData().observe(this, Observer<BookResult>(){
            fun onChanged(bookResult: BookResult){
                if (bookResult != null){
                    adapter.setResults(bookResult.items)
                }
            }
        })

        search_results_recyclerview.adapter = adapter
        search_results_recyclerview.layoutManager = LinearLayoutManager(context)

        fun performSearch() {
            viewModel.searchBooks(keywordEditText.toString())
        }
        searchButton.setOnClickListener(View.onClickListener()){
            fun onClick(view: View){
                val anim = AlphaAnimation(0.5f, 1.0f)
                anim.duration(50)
                anim.startOffset(200)
                anim.repeatMode(Animation.REVERSE)
                anim.repeatCount(Animation.INFINITE)
                search_button.startAnimation()
                performSearch()
            }
        }

    }


    override fun openBookClick(position: Int, bookItem: BookItem, bookImageView: ImageView) {
        val EXTRA_ITEM: String = "item.key"
        val EXTRA_IMAGE: String = "image.key"

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, bookImageView, bookImageView.transitionName).toBundle()
        )
        Intent(this, BookDetailActivity::class)
            .putExtra(EXTRA_ITEM, bookItem)
            .putExtra(EXTRA_IMAGE, bookImageView)
            .let{
        startActivity(it, options)
    }

}
}