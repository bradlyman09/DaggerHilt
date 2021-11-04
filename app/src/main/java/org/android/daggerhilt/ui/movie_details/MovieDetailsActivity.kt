package org.android.daggerhilt.ui.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import org.android.daggerhilt.R
import org.android.daggerhilt.room.entity.MovieEntity

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movieEntity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        intent?.let {
            it.getParcelableExtra<MovieEntity>(ARG_MOVIE_ENTITY)?.let {
                movieEntity = it
            }
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer, MovieDetailsFragment.newInstance(movieEntity))
        }
    }

    companion object{
        const val ARG_MOVIE_ENTITY = "ARG_MOVIE_ENTITY"
    }
}