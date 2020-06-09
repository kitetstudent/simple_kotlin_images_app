package com.example.wt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemListener {
    lateinit var interstitialAd: InterstitialAd
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        myAdapter = MyAdapter(getData(), this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = viewManager
            adapter = myAdapter
        }

        mainBan.loadAd(AdRequest.Builder().build())

        initAd()
    }

    private fun initAd() {
        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                if(Common.ad){
                    showAd()
                }
            }
        }
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun showAd() {
        if (interstitialAd != null) {
            if (interstitialAd.isLoaded) {
                interstitialAd.show()
            }
        }
    }

    private fun getData(): ArrayList<Category> {
        val categories: ArrayList<Category> = ArrayList()
        categories.add(Category("Start From The Bottom", getString(R.string.a30), "a30"))
        categories.add(Category("Don’t Always Use Suggested Moves", getString(R.string.a29), "a29"))
        categories.add(
            Category(
                "Reshuffle Your Candies (Without Losing Any Lives)",
                getString(R.string.a28),
                "a28"
            )
        )
        categories.add(
            Category(
                "Changing The Clock For Extra Lives",
                getString(R.string.a27),
                "a27"
            )
        )
        categories.add(
            Category(
                "Accepting Extra Lives (And Other Gifts) From Friends",
                getString(R.string.a26),
                "a26"
            )
        )
        categories.add(Category("Hitting Quit To Check Progress", getString(R.string.a25), "a25"))
        categories.add(Category("Destroy All Distractions", getString(R.string.a24), "a24"))
        categories.add(
            Category(
                "Turn Off Boosts For The Next Level",
                getString(R.string.a23),
                "a23"
            )
        )
        categories.add(Category("Save The Fish For Last", getString(R.string.a22), "a22"))
        categories.add(Category("Pay Attention To Your Stripes", getString(R.string.a21), "a21"))
        categories.add(Category("Know Your Chocolate", getString(R.string.a20), "a20"))
        return categories
    }

    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        var dialog: AlertDialog? = null
        builder.setTitle("Exit the application?")
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setPositiveButton("Exit") { dialog, _ ->
            dialog.dismiss()
            finishAffinity()
        }
        dialog = builder.create()
        dialog.show()
    }

    override fun onClickItem(category: Category) {
        val intent = Intent(this, ScrollingActivity::class.java).apply {
            putExtra("title", category.title)
            putExtra("image", category.image)
            putExtra("desc", category.desc)
        }
        startActivity(intent)
    }
}

data class Category(val title: String, val desc: String, val image: String)
