package com.example.rssfeedpractice

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedpractice.databinding.ItemRowBinding

class RecyclerViewAdapter(private var questions:ArrayList<Question>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding:ItemRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return  ItemViewHolder(ItemRowBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val question = questions[position]
        holder.binding.apply {
            tvTitle.text = question.title
            tvAuthor.text = question.author
            tvCategory.text = question.categories


        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }
    fun update(questions :ArrayList<Question>){
        this.questions = questions
        notifyDataSetChanged()
    }
}