package com.example.speechtotextapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.BookItemBinding
import com.example.speechtotextapp.responses.BookResponse
import com.example.speechtotextapp.responses.MovieResponse

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookHolder>() {
    var list: List<BookResponse> = listOf()

    class BookHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = BookItemBinding.bind(item)

        fun bind(movie: BookResponse) = with(binding) {
            titleBook.text = movie.title
            descriptionBook.text = movie.content
            buttonMore.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val bundle = Bundle().apply {
                    putString("movieTitle", movie.title)
                    putString("movieSubtitles", movie.content)
                    putString("movieVideo", movie.author)
                }
                navController.navigate(R.id.action_BookFragment_to_descriptionBookFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(list[position])
    }

 }