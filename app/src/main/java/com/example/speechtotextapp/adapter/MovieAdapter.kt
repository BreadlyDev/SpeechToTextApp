package com.example.speechtotextapp.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.media.ImageWriter
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.MovieCardBinding
import com.example.speechtotextapp.responses.MovieResponse

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    var list: List<MovieResponse> = listOf()

    class MovieHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = MovieCardBinding.bind(item)

        fun bind(movie: MovieResponse) = with(binding) {
            txtTitle.text = movie.title
            txtDescription.text = movie.description
            imgMovie.setImageURI(Uri.parse(movie.image))
//            videoMovie.setVideoURI(Uri.parse(movie.file))
//            videoMovie.setOnClickListener {
//                videoMovie.start()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(newList: List<MovieResponse>) {
        list = newList
        notifyDataSetChanged()
    }
}