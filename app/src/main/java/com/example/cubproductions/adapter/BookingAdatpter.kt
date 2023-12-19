package com.example.cubproductions.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cubproductions.R
import com.example.cubproductions.data.UserBooking

class BookingAdatpter(private val userList: ArrayList<UserBooking>)
    : RecyclerView.Adapter<BookingAdatpter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: UserBooking) {

            itemView.findViewById<TextView>(R.id.sessionType).text = book.brandName
            itemView.findViewById<TextView>(R.id.sessionDate).text = book.dateOfSession

            if(book.typeOfSession == "Fashion Photography"){
                itemView.findViewById<ImageView>(R.id.ImageBook).setImageResource(
                    R.drawable.fashon
                )
                itemView.findViewById<TextView>(R.id.sessionType)
                    .setTextColor(Color.BLACK)
                itemView.findViewById<TextView>(R.id.sessionDate)
                    .setTextColor(Color.BLACK)
            }else if(book.typeOfSession == "Product Photography"){
                itemView.findViewById<ImageView>(R.id.ImageBook).setImageResource(
                    R.drawable.product
                )
            }else if(book.typeOfSession == "Weeding Photography"){
                itemView.findViewById<ImageView>(R.id.ImageBook).setImageResource(
                    R.drawable.weeding
                )
            }else {
                itemView.findViewById<ImageView>(R.id.ImageBook).setImageResource(
                    R.drawable.food
                )
            }

        }
    }
}