package com.softvision.alexlepadatu.typicode.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.softvision.alexlepadatu.typicode.R
import com.softvision.alexlepadatu.typicode.presentation.fragment.AlbumsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var albumsFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (albumsFragment == null)
            albumsFragment = AlbumsListFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, albumsFragment)
            .commit()
    }
}
