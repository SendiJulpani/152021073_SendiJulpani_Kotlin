package com.example.projectuts

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.projectuts.databinding.ActivityNavigatorBinding
import com.google.android.material.navigation.NavigationView

class NavigatorActivity : AppCompatActivity() {

    lateinit var toogle : ActionBarDrawerToggle
    lateinit var drawnerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigator)

        drawnerLayout = findViewById(R.id.drawnerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                // ... (previous menu items)

                R.id.nav_logout -> showLogoutConfirmationDialog()
            }
            true
        }

        toogle = ActionBarDrawerToggle(this, drawnerLayout, R.string.open, R.string.close)
        drawnerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_kalkulator -> replaceFragment(KalkulatorFragment(), it.title.toString())
                R.id.nav_bmi -> replaceFragment(BMIKalkulatorFragment(), it.title.toString())
                R.id.nav_matauang -> replaceFragment(KonversiMataUangFragment(), it.title.toString())
                R.id.nav_suhu -> replaceFragment(SuhuFragment(), it.title.toString())
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_profile -> replaceFragment(ProfileFragment(), it.title.toString())
                R.id.nav_logout -> showLogoutConfirmationDialog()
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentLayout,fragment)
        fragmentTransaction.commit()
        drawnerLayout.closeDrawers()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toogle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showLogoutConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Are you sure you want to log out?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            // Handle the logout action here
            // You can start a new LoginActivity or clear user data as needed.
            // For example:
            // val intent = Intent(this, LoginActivity::class.java)
            // startActivity(intent)
            // finish()
        }
        alertDialog.setNegativeButton("No") { _, _ ->
            // Dismiss the dialog
        }
        alertDialog.show()
    }
}