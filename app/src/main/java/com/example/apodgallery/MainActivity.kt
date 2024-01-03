package com.example.apodgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.apodgallery.databinding.ActivityMainBinding
import com.example.apodgallery.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {
            bottomNav.setupWithNavController(navController)
            toolbarApp.apply {
                setupWithNavController(navController)
                inflateMenu(R.menu.about)
                setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.about -> {
                            showAboutDialog()
                            true
                        }
                        else -> false
                    }
                }
            }
        }
    }

    private fun showAboutDialog() {
        val aboutText = SpannableString(
            "I'm Jhony Guerra, passionate about crafting mobile experiences on Android using Kotlin.\n\n" +
                    "Let's connect and create something amazing together!\n\n" +
                    "Email: jhonybguerra@gmail.com\n" +
                    "Phone: +55 (11) 98672-6064\n" +
                    "GitHub: https://github.com/jhonybguerra\n" +
                    "LinkedIn: https://www.linkedin.com/in/jhonybguerra/"
        ).apply {
            Linkify.addLinks(this, Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS)
        }

        val dialog = AlertDialog.Builder(this, R.style.SpaceThemeDialog)
            .setTitle("About the Developer")
            .setMessage(aboutText)
            .setPositiveButton("Ok", null)
            .create()

        dialog.show()
        dialog.findViewById<TextView>(android.R.id.message)?.movementMethod = LinkMovementMethod.getInstance()

    }
}