package com.bigbang.booksearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bigbang.booksearch.R
import com.bigbang.booksearch.model.BookItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.book_item_layout.view.*

class BookAdapter(var bookList: List<BookItem>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private lateinit var mBookClickListener: BookClickListener

    inner class BookViewHolder(itemView: View, mBookClickListener: BookClickListener): RecyclerView.ViewHolder(itemView) {

        lateinit var bookClickListener: BookClickListener

        override onClick(v: View?, bookClickListener: BookClickListener) {
            this.bookClickListener = bookClickListener
            bookClickListener.openBookClick(adapterPosition)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        return BookViewHolder(view, mBookClickListener)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.itemView.apply {
            bookList[position].apply {

                if (volumeInfo.imageLinks != null)
                    Glide.with(context)
                        .applyDefaultRequestOptions(RequestOptions().circleCrop())
                        .load(volumeInfo.imageLinks.smallThumbnail)
                        .into(book_imageview)

                book_title_textview.text = volumeInfo.title
                if (volumeInfo.authors != null)
                    book_author_textview.text = volumeInfo.authors.toString()
                published_date_textview.text = volumeInfo.publishedDate
            }
        }
    }

    fun setResults(bookList: List<BookItem>){
        this.bookList = bookList
        notifyDataSetChanged()
    }

    interface BookClickListener {
        fun openBookClick(position: Int, bookItem: BookItem, bookDetailImageView: ImageView)

    }

}