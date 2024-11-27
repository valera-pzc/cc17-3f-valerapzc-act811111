package com.example.shelfapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookListAdapter(
    // on below line we are passing variables
    // as course list and context
    private var bookList: ArrayList<BookModel>,
    private var ctx: Context
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookListAdapter.BookViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_book,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return BookListAdapter.BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookListAdapter.BookViewHolder, position: Int) {
        val bookInfo = bookList[position]

        var modifiedThumbnailUrl = bookInfo.thumbnail
        if (modifiedThumbnailUrl.startsWith("http://")) {
            modifiedThumbnailUrl = modifiedThumbnailUrl.replace("http://", "https://")
            bookInfo.thumbnail = modifiedThumbnailUrl
        }

        //check for thumbnail errors
        Log.d("BookThumbnail", "Thumbnail URL: ${modifiedThumbnailUrl}")
        // below line is use to set image from URL in our image view.
        Picasso.get().load(bookInfo.thumbnail).error(R.drawable.ic_launcher_background) .into(holder.bookIV);
        holder.bookTitleTV.text = bookInfo.title
        holder.bookPagesTV.text = "Pages : " + bookInfo.pageCount

        // below line is use to add on click listener for our item of recycler view.
        holder.itemView.setOnClickListener {
            // inside on click listener method we are calling a new activity
            // and passing all the data of that item in next intent.
            val i = Intent(ctx, BookInfoActivity::class.java)
            i.putExtra("title", bookInfo.title)
            i.putExtra("genre", bookInfo.subtitle)
            i.putExtra("authors", bookInfo.authors)
            i.putExtra("publisher", bookInfo.publisher)
            i.putExtra("publishedDate", bookInfo.publishedDate)
            i.putExtra("description", bookInfo.description)
            i.putExtra("pageCount", bookInfo.pageCount)
            i.putExtra("thumbnail", bookInfo.thumbnail)
            i.putExtra("previewLink", bookInfo.previewLink)
            i.putExtra("infoLink", bookInfo.infoLink)
            i.putExtra("buyLink", bookInfo.buyLink)

            // after passing that data we are
            // starting our new  intent.
            ctx.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our
        // course name text view and our image view.
        val bookTitleTV: TextView = itemView.findViewById(R.id.idTVBookName)
        val bookPagesTV: TextView = itemView.findViewById(R.id.idTVBookPages)
        val bookIV: ImageView = itemView.findViewById(R.id.idIVBook)
    }
}