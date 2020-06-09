package com.example.wt

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        Common.ad = false

        text_title.text = intent.getStringExtra("title")
        text_desc.text = intent.getStringExtra("desc")
        val string = "https://your_domain/WT_images/" + intent.getStringExtra("image") + ".jpg"
        Picasso.get()
            .load(string)
            .placeholder(R.drawable.a1)
            .into(toolbar_imageview)

        contentBan.loadAd(AdRequest.Builder().build())
    }
}
