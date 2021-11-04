package org.android.daggerhilt.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.android.daggerhilt.R
import org.android.daggerhilt.network.ResultWrapper
import org.android.daggerhilt.network.response.get_movies.GetMoviesResponse
import org.android.daggerhilt.ui.movie_details.MovieDetailsActivity

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var isFavoriteList = false
    private val movieAdapter : MovieAdapter by lazy {
        MovieAdapter(requireContext(), viewModel.movieList)
    }

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            isFavoriteList = it.getBoolean(ARG_IS_FAVORITE)
        }

        if (isFavoriteList)
            viewModel.getFavoriteMoviesFromDatabase()
        else
            viewModel.getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObservables()
    }

    private fun initView() {

        movieAdapter.favoriteListener = {
            viewModel.movieList[it].isFavorite = !viewModel.movieList[it].isFavorite
            viewModel.updateMovie(viewModel.movieList[it])

            movieAdapter.notifyItemChanged(it)
        }

        movieAdapter.rowListener = {
            val intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MovieDetailsActivity.ARG_MOVIE_ENTITY, viewModel.movieList[it])
            startActivity(intent)
        }

        movieRecyclerview.adapter = movieAdapter
    }

    private fun setUpObservables(){
        viewModel.getMoviesResponse.observe(viewLifecycleOwner){
            when(it){
                is ResultWrapper.ApiLoading -> showProgressBar(it.isLoading)
                is ResultWrapper.ApiSuccess -> onGetMovieListSuccess(it.body)
                is ResultWrapper.ApiError -> { }
            }
        }

        viewModel.isRefreshList.observe(viewLifecycleOwner){
            if (it){
                movieAdapter.notifyDataSetChanged()
                emptyListTextView.visibility = View.GONE
            }
            else
                emptyListTextView.visibility = View.VISIBLE

        }
    }

    private fun onGetMovieListSuccess(body: GetMoviesResponse) {
        if (isFavoriteList)
            viewModel.getFavoriteMoviesFromDatabase()
        else
            viewModel.getAllMoviesFromDatabase()
    }

    private fun showProgressBar(isShow : Boolean){
        progressbar.visibility = if (isShow)
            View.VISIBLE
        else
            View.GONE
    }

    companion object {
        private const val ARG_IS_FAVORITE = "ARG_IS_FAVORITE"

        @JvmStatic
        fun newInstance(isFavoriteList: Boolean) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_FAVORITE, isFavoriteList)
                }
            }
    }
}