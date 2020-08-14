package com.example.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private var canNavigateUp: Boolean = false
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
                // top level starting points, should be ids of fragments and / or navGraphs included in the global_nav_graph
                setOf(
                        R.id.fragment_home,
                        R.id.nav_graph_settings
                ), drawerLayout
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (canNavigateUp) {
            navigationController.navigateUp()
        } else {
            NavigationUI.navigateUp(navigationController, appBarConfiguration)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun setNavigation(navigateUpEnabled: Boolean) {
        supportActionBar?.let { actionBar ->
            canNavigateUp = navigateUpEnabled
            if (navigateUpEnabled) {
                // set up icon
                actionBar.setHomeAsUpIndicator(null)
                lockUnlockDrawer(lock = true)
            } else {
                // set drawer icon
                actionBar.setDisplayShowHomeEnabled(true)
                lockUnlockDrawer(lock = false)
            }
        }
    }

    private fun lockUnlockDrawer(lock: Boolean) {
        drawerLayout?.setDrawerLockMode(if (lock) {
            DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        } else {
            DrawerLayout.LOCK_MODE_UNLOCKED }
        )
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment
        navigationController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navigationController, appBarConfiguration)
        navigationView?.let {
            NavigationUI.setupWithNavController(it, navigationController)
        }
    }
}