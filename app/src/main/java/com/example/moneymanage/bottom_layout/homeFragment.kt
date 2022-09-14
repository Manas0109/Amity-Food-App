package com.example.moneymanage.bottom_layout

import Adapter.RecyclerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moneymanage.JuiceActivity
import com.example.moneymanage.R
import com.google.firebase.database.FirebaseDatabase


class homeFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    var objTitle = ArrayList<String>()
    var objPrice = ArrayList<String>()
    var objImage = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageView = view.findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/foodapplication-34a34.appspot.com/o/food%20display%20images%2Fburger_icon.png?alt=media&token=363d4bee-defb-443e-aa27-acbc0ad6b867")
            .into(imageView)

        var juiceicon = view.findViewById<ImageView>(R.id.juice_icon)
        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/foodapplication-34a34.appspot.com/o/food%20display%20images%2Fpacked_juiceicon.png?alt=media&token=f831cd1d-75d5-4813-9dff-8298d21c19a9")
            .into(juiceicon)
        juiceicon.setOnClickListener{
            val testingfrag: Fragment = TestingFragment()
            getActivity()?.getSupportFragmentManager()?.beginTransaction()?.replace(R.id.mainLayout, testingfrag)?.commit();

        }

        var recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        layoutManager = LinearLayoutManager(getActivity())
        recyclerview.layoutManager = layoutManager

        var adapter = RecyclerAdapter(this, objTitle, objPrice, objImage)
        getData(adapter, recyclerview)
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
