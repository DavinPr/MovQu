package com.app.moviecatalogue.presentation.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.moviecatalogue.R
import com.app.moviecatalogue.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.root)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie, R.id.navigation_tvshow, R.id.navigation_favorite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.apply {
            itemIconTintList = null
            navController.addOnDestinationChangedListener { _, destination, arguments ->
                binding.toolbar.toolbarTitle.text = destination.label
            }
            setupWithNavController(navController)
        }
        binding.toolbar.btnProfile.setOnClickListener {
            Toast.makeText(this, "Implemented Soon", Toast.LENGTH_SHORT).show()
        }
    }
}

