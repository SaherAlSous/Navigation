package com.bignerdranch.android.navigation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    /**
     * Creating a Nav controller and instantiate it in onViewCreated
     */
    private lateinit var navController : NavController
    private lateinit var navMenuBottom : BottomNavigationView
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navView: NavigationView

    /**
     * working on the navigation bar
     */
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * setting up navigation menus
         */
        navController =findNavController(R.id.nav_host_fragment)
        navMenuBottom = findViewById(R.id.bottom_navigation)
        navMenuBottom.setupWithNavController(navController)

        /**
         * working on [Navigation]
         */
        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout) //accessing the nav bar & drawer layout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout) //Creating a back arrow & drawer Layout

        /**
         * Creating the Navigation Drawer
         */
        navView = findViewById(R.id.navigation_view)
        NavigationUI.setupWithNavController(navView,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        /**
         * This function allow the back arrow to move the view to previous fragment.
         * and display the name of the fragment on the navigation bar
         */
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }

}