package org.android.daggerhilt.ui.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.row_movies.view.*
import org.android.daggerhilt.R
import org.android.daggerhilt.network.response.get_movies.Movie
import org.android.daggerhilt.room.entity.MovieEntity

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var movieEntity : MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getParcelable<MovieEntity>(ARG_MOVIE_ENTITY)?.let {
                movieEntity = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Glide.with(requireContext())
            .load(movieEntity.image)
            .placeholder(R.drawable.ic_baseline_local_movies_24_black)
            .into(iconImageView)

        tracknameTextView.text = movieEntity.trackName
        collectionPriceTextView.text = "Php ${movieEntity.collectionPrice} "
        genreTextview.text = movieEntity.genre
        descriptionTextview.text = movieEntity.description

        favoriteImageView.setImageResource(getFavoriteDrawable(movieEntity.isFavorite))
    }

    private fun getFavoriteDrawable(isFavorite : Boolean) : Int{
        return if (isFavorite)
            R.drawable.ic_baseline_star_24
        else
            R.drawable.ic_baseline_star_border_24


    }

    companion object {
        private const val ARG_MOVIE_ENTITY = "ARG_MOVIE_ENTITY"

        @JvmStatic
        fun newInstance(movieEntity: MovieEntity) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MOVIE_ENTITY, movieEntity)
                }
            }
    }
}