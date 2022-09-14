package Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moneymanage.R
import com.example.moneymanage.bottom_layout.homeFragment
import java.util.*


class RecyclerAdapter(
    val context: homeFragment,
    var objTitle: ArrayList<String>, var objPrice: ArrayList<String>, var objImage: ArrayList<String>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Toast.makeText(parent.context, "this is toast from rv adapter", Toast.LENGTH_SHORT).show()
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(v)
    }

val TAG = "MainActivity"

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = objTitle[position]
        holder.itemPrice.text = objPrice[position]
        Log.d("manas", "the size is ${objImage[position]}")

        Glide.with(context)
             .load(objImage[position])
             .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return objTitle.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView
        lateinit var  rl : RelativeLayout

        fun getIvImage(): ImageView{
            return itemImage
        }

        init{
            itemImage = itemView.findViewById(R.id.item_Image)
            itemTitle = itemView.findViewById(R.id.item_Title)
            itemPrice = itemView.findViewById(R.id.item_Price)
            rl = itemView.findViewById(R.id.blue_chips_onclick)


            rl.setOnClickListener {
                Log.d(TAG, "onClick code works")
            }
        }
    }
}