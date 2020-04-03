package com.bigbang.booksearch.view

import android.content.Intent.getIntent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bigbang.booksearch.R
import com.bigbang.booksearch.database.Favorite
import com.bigbang.booksearch.model.BookItem
import com.bigbang.booksearch.viewmodel.BookViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_detail_layout.*

class BookDetailActivity : AppCompatActivity() {

    private lateinit var mViewModel: BookViewModel
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail_layout)

        supportPostponeEnterTransition();

        val extras = Bundle.getIntent().getExtras()
        val bookItem = BookItem.extras.getParcelable(SearchFragment.EXTRA_ITEM)

        title_detail_textview.text(bookItem.volumeInfo.title)
        author_detail_textview.text(bookItem.volumeInfo.authors)
        publishdate_detail_textview.text(bookItem.volumeInfo.publishedDate)
        description_detail.text(bookItem.volumeInfo.description)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = String.extras.getString(SearchFragment.EXTRA_IMAGE)
            book_detail_imageview.transitionName(imageTransitionName)
        }
        Glide.with(this)
            .load(bookItem.volumeInfo.imageLinks.smallThumbnail)
            .noFade()
            .into(book_detail_imageview)
    }

    @OnClick(R.id.save_button)
        fun saveBookClick(){
        mViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        val favBook = Favorite()
        saveButton.setOnClickListener(View.OnClickListener {
            mViewModel.insertFavorites(favBook)
            Toast.makeText(this, "Book inserted successfully!", Toast.LENGTH_SHORT).show()

        })
        }





    }

