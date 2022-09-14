package com.example.moneymanage

import Adapter.RecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class Secondactivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    private var layoutManager: RecyclerView.LayoutManager? = null
    var objTitle = ArrayList<String>()
    var objPrice = ArrayList<String>()
    var objImage = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondactivity)

        bottomNav = findViewById(R.id.bottom_navigation) as BottomNavigationView
        val juiceintent = Intent(this, JuiceActivity::class.java)

        var imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/foodapplication-34a34.appspot.com/o/food%20display%20images%2Fburger_icon.png?alt=media&token=363d4bee-defb-443e-aa27-acbc0ad6b867")
            .into(imageView)
        imageView.setOnClickListener(){
            startActivity(juiceintent)
        }



        var juiceicon = findViewById<ImageView>(R.id.juice_icon)
        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/foodapplication-34a34.appspot.com/o/food%20display%20images%2Fpacked_juiceicon.png?alt=media&token=f831cd1d-75d5-4813-9dff-8298d21c19a9")
            .into(juiceicon)
        juiceicon.setOnClickListener(){
            startActivity(juiceintent)
        }


        var recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager

//        Toast.makeText(this, "heeeee", Toast.LENGTH_SHORT).show()

       // var adapter = RecyclerAdapter(this, objTitle, objPrice, objImage)
        //getData(adapter, recyclerview)
//        Log.d("manas", adapter.itemCount.toString())
//        Toast.makeText(this, "the size is ${adapter.itemCount}", Toast.LENGTH_SHORT).show()
//        recyclerview.adapter=adapter
//        recyclerview.visibility = View.VISIBLE

    }

    private fun getData(adapter: RecyclerAdapter, recyclerview: RecyclerView) {
        var dbref = FirebaseDatabase.getInstance().reference.child("item_title_data")
        dbref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result;
                for (i in snapshot.children) {
                    for (j in i.children) {
                        if (j.key == "item_title") {
                            objTitle.add(j.value.toString())
                            //Log.d("manas", j.value.toString())
                        }
                        if (j.key == "item_price") {
                            objPrice.add(j.value.toString())
                            //Log.d("manas", j.value.toString())
                        }
                        if (j.key == "item_image") {
                            objImage.add(j.value.toString())
                            //Log.d("manas", j.value.toString())
                        }
                    }
                }
            }
            recyclerview.adapter = adapter

            Toast.makeText(this, "the size is ${adapter.itemCount}", Toast.LENGTH_SHORT).show()
            Log.d("manas", "the size is ${adapter.itemCount}")
        }
    }
}
