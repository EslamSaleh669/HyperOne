package com.HyperOne.ui.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.HyperOne.R
import com.HyperOne.data.model.DataResponseItem
import com.HyperOne.di.room.entity.ItemModel
import com.HyperOne.util.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DataAdapter(
    private val DataList: ArrayList<DataResponseItem>,
    val activity: Activity,
    private val onDownloadClickListner:OnDownloadCLickListener,
    private var OfflineList : List<Int>


) : RecyclerView.Adapter<DataAdapter.DataVHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVHolder {

       return DataVHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_viewshape,parent,false)
        )
    }

    override fun onBindViewHolder(holder: DataVHolder, position: Int) {
        holder.ItemName.text = DataList.get(position).name


        if (DataList.get(position).type == "VIDEO"){
            val requestOptions = RequestOptions()
            requestOptions.isMemoryCacheable

            activity.let{
                Glide.with(it).setDefaultRequestOptions(requestOptions).
                load(DataList.get(position).url).into(holder.VideoItem)

            }
            holder.VideoItem.visibility = View.VISIBLE
            holder.PdfItem.visibility = View.GONE

        }
        else{
            holder.PdfItem.loadUrl("${Constants.FileViewer_URL}${DataList.get(position).url}")
            holder.PdfItem.visibility = View.VISIBLE
            holder.VideoItem.visibility = View.GONE

        }

        Log.d("dataishere",OfflineList.toString())

            if (OfflineList.contains(DataList.get(position).id!!)){
                holder.DownloadBtn.visibility = View.GONE
                holder.DoneBtn.visibility = View.VISIBLE
                Log.d("yeshas","yesyhas")

        }

        holder.DownloadBtn.setOnClickListener {
            holder.DownloadBtn.visibility = View.GONE
            holder.DoneBtn.visibility = View.VISIBLE
            onDownloadClickListner.onDawnloadClicked(DataList[position])
        }

    }

    override fun getItemCount() = DataList.size


    class DataVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ItemName : TextView =itemView.findViewById(R.id.itemname)
        val PdfItem : WebView = itemView.findViewById(R.id.itemsrcp)
        val VideoItem : ImageView = itemView.findViewById(R.id.itemsrcv)
        val DownloadBtn : ImageView = itemView.findViewById(R.id.downloadbtn)
        val DoneBtn : ImageView = itemView.findViewById(R.id.donebtn)


    }
    public interface OnDownloadCLickListener {
        fun onDawnloadClicked(item:DataResponseItem )
    }

}