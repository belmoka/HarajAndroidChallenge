package com.example.harajtask.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harajtask.Data.Data
import com.example.harajtask.Data.ParceData
import com.example.harajtask.R
import com.example.harajtask.Utilities.Utility
import com.example.harajtask.activities.PostActivity
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class DataAdapter(private var mData: List<Data>, private var context: Context):
        RecyclerView.Adapter<DataAdapter.ItemHolder>() {

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView
        val thumb: ImageView
        val username: TextView
        val commentCount: TextView
        val city: TextView
        val date: TextView
        val item: ConstraintLayout
        var data: Data? = null


        @JvmName("setData1")
        fun setData(get: Data, context: Context) {
            data = get
            title.text = get.title
            Glide.with(itemView.context)
                .load(get.thumbUrl)
                .placeholder(Utility.progressCircle(context))
                .centerCrop()
                .into(thumb)
            city.text = get.city
            username.text = get.username
            val count = get.commentCount
            commentCount.text = "($count)"
            date.text = getAgoTime(get.date.toLong())
            if (get.commentCount == 0)
                commentCount.visibility = View.GONE
            itemView.setOnClickListener(View.OnClickListener {
                gotoPost()
            })

        }

        fun getAgoTime(utime: Long): String {
            val date = Date(utime * 1000)
            val p = PrettyTime()
            return p.format(date)
        }

        init {
            // Define click listener for the ViewHolder's View.
            title = itemView.findViewById(R.id.title)
            thumb = itemView.findViewById(R.id.thumb)
            username = itemView.findViewById(R.id.username)
            commentCount = itemView.findViewById(R.id.comment)
            city = itemView.findViewById(R.id.location)
            date = itemView.findViewById(R.id.date)
            item = itemView.findViewById(R.id.ad_item)
        }



        fun gotoPost() {
            val pdata = ParceData(data!!.title, data!!.username, data!!.thumbUrl,
                data!!.commentCount, data!!.city, data!!.date, data!!.body)
            val intent: Intent = Intent(itemView.context, PostActivity::class.java)
            intent.putExtra("post", pdata)
            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(mData.get(position), context)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun addData(data: List<Data>) {
        mData = data
    }
}