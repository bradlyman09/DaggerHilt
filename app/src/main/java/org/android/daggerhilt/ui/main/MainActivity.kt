package org.android.daggerhilt.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import org.android.daggerhilt.R
import org.android.daggerhilt.network.ResultWrapper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainer, MovieListFragment.newInstance(false))
        }

        bottomNav.setOnItemSelectedListener {
            val fragment = when(it.itemId){
                R.id.allMovieList -> MovieListFragment.newInstance(false)
                else -> MovieListFragment.newInstance(true)
            }

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainer, fragment)
            }

            return@setOnItemSelectedListener true
        }

        bottomNav.setOnItemReselectedListener {

        }
    }
}