package com.example.moneymanage

import Adapter.RecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class JuiceActivity : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    var objTitle = ArrayList<String>()
    var objPrice = ArrayList<String>()
    var objImage = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_juice, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerview = view.findViewById<RecyclerView>(R.id.juice_recycler)
       // layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        //        var adapter = RecyclerAdapter(this, objTitle, objPrice, objImage)
//        getData(adapter, recyclerview)

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

            //Toast.makeText(this, "the size is ${adapter.itemCount}", Toast.LENGTH_SHORT).show()
            Log.d("manas", "the size is ${adapter.itemCount}")
        }
    }
}