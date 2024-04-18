package com.example.speechtotextapp.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.media.ImageWriter
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.MovieCardBinding
import com.example.speechtotextapp.responses.MovieResponse
import com.squareup.picasso.Picasso

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    var list: List<MovieResponse> = listOf()

    class MovieHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = MovieCardBinding.bind(item)

        fun bind(movie: MovieResponse) = with(binding) {
            txtTitle.text = movie.title
            txtDescription.text = movie.subtitles
            Picasso.get().load(movie.image).into(imgMovie)
            btnMore.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val bundle = Bundle().apply {
                    putString("movieTitle", movie.title)
                    putString("movieSubtitles", movie.subtitles)
                    putString("movieVideo", movie.file)
                }
                navController.navigate(R.id.action_MovieFragment_to_DescriptionFragment, bundle)
            }
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