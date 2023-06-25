package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.Calendar

class MyAdapter(val context: Context,val listener:INotesRVAdapter):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val allNotes=ArrayList<Note>()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
      val textView=itemView.findViewById<TextView>(R.id.textView)
      val imageView=itemView.findViewById<ImageView>(R.id.imageView)
        val tView=itemView.findViewById<TextView>(R.id.textView2)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(context)
        val viewHolder=MyViewHolder(inflater.inflate(R.layout.each_item,parent,false))
        viewHolder.imageView.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote=allNotes[position]

        val calendar=Calendar.getInstance()
        val currentDate=DateFormat.getDateInstance().format(calendar.time)
        holder.textView.text=currentNote.text
        holder.tView.text=currentDate



    }

    override fun getItemCount(): Int {
   return allNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}
interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}