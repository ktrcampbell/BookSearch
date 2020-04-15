package com.bigbang.booksearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bigbang.booksearch.R
import com.bigbang.booksearch.model.BookItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.book_item_layout.view.*

class BookAdapter(var bookList: List<BookItem>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.itemView.apply {
            //open book detail
//            this.setOnClickListener{currentView ->
//                bookClickListener.openBookClick()
//            }
            bookList[position].apply {
                    Glide.with(context)
                        .applyDefaultRequestOptions(RequestOptions().circleCrop())
                        .load(volumeInfo.imageLinks.smallThumbnail.replace("http://", "https://"))
                        .into(book_imageview)
                ViewCompat.setTransitionName(book_imageview, id)

                book_title_textview.text = volumeInfo.title
                volumeInfo.authors?.let { authors->
                    book_author_textview.text = authors.toString()
                }

                published_date_textview.text = volumeInfo.publishedDate
            }
        }
    }

    fun setResults(bookList: List<BookItem>){
        this.bookList = bookList
        notifyDataSetChanged()
    }

    }
