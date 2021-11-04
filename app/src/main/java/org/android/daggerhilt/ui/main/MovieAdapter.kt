package org.android.daggerhilt.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_movies.view.*
import org.android.daggerhilt.R
import org.android.daggerhilt.room.entity.MovieEntity

class MovieAdapter (private val context : Context,
                    private val movieList : MutableList<MovieEntity>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var favoriteListener : ((position : Int) -> Unit) = {}
    var rowListener : ((position : Int) -> Unit) = {}

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.row_movies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(movieList[position].image)
            .placeholder(R.drawable.ic_baseline_local_movies_24_black)
            .into(holder.itemView.iconImageView)

        holder.itemView.tracknameTextView.text = movieList[position].trackName
        holder.itemView.collectionPriceTextView.text = "Php ${movieList[position].collectionPrice} "
        holder.itemView.genreTextview.text = movieList[position].genre

        holder.itemView.favoriteImageView.setImageResource(getFavoriteDrawable(movieList[position].isFavorite))

        holder.itemView.favoriteImageView.setOnClickListener {
            favoriteListener(position)
            holder.itemView.favoriteImageView.setImageResource(getFavoriteDrawable(!movieList[position].isFavorite))
        }

        holder.itemView.setOnClickListener {
            rowListener(position)
        }
    }

    private fun getFavoriteDrawable(isFavorite : Boolean) : Int{
        return if (isFavorite)
                R.drawable.ic_baseline_star_24
            else
                R.drawable.ic_baseline_star_border_24


    }

    override fun getItemCount(): Int = movieList.size


}