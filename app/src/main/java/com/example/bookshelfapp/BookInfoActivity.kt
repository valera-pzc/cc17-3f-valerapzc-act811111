package com.example.shelfapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class BookInfoActivity : AppCompatActivity() {

    lateinit var titleTV: TextView
    lateinit var genreTV: TextView
    lateinit var publisherTV: TextView
    lateinit var descTV: TextView
    lateinit var authorTV: TextView
    lateinit var previewBtn: Button
    lateinit var buyBtn: Button
    lateinit var bookIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)

        titleTV = findViewById(R.id.idTVTitle)
        genreTV = findViewById(R.id.idTVGenre)
        publisherTV = findViewById(R.id.idTVpublisher)
        descTV = findViewById(R.id.idTVDescription)
        authorTV = findViewById(R.id.idTVAuthor)
        previewBtn = findViewById(R.id.idBtnPreview)
        buyBtn = findViewById(R.id.idBtnBuy)
        bookIV = findViewById(R.id.idIVbook)

        val title = intent.getStringExtra("title")
        val genre = intent.getStringExtra("genre")
        val publisher = intent.getStringExtra("publisher")
        val description = intent.getStringExtra("description")
        val authorName = intent.getStringExtra("authorName")
        val thumbnail = intent.getStringExtra("thumbnail")
        val previewLink = intent.getStringExtra("previewLink")
        val buyLink = intent.getStringExtra("buyLink")

        titleTV.text = title
        genreTV.text = genre
        publisherTV.text = publisher
        descTV.text = description
        authorTV.text = "Author: $authorName"
        Picasso.get().load(thumbnail).into(bookIV)

        previewBtn.setOnClickListener {
            if (previewLink.isNullOrEmpty()) {
                Toast.makeText(this, "No preview Link present", Toast.LENGTH_SHORT).show()
            } else {
                val uri: Uri = Uri.parse(previewLink)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }

        buyBtn.setOnClickListener {
            if (buyLink.isNullOrEmpty()) {
                Toast.makeText(this, "No buy page present for this book", Toast.LENGTH_SHORT).show()
            } else {
                val uri = Uri.parse(buyLink)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }
}
