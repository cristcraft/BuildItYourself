package com.BuildItYourself.BIY.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.BuildItYourself.BIY.R
import com.BuildItYourself.BIY.database.UsuarioDAO
import com.BuildItYourself.BIY.fragments.MiPerfilFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initViews()
        setUpNavigation()
        usuarioDAO = UsuarioDAO(this)

    }

    private fun setUpNavigation() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_inicio ->{
                //redireccinar al inicio

            }
            R.id.menu_productos ->{
                //redireccinar al inicio

            }
            R.id.menu_configuracion ->{
                //redireccinar al inicio

            }
            R.id.menu_cerrar_sesion ->{
                //redireccinar al inicio


            }
            R.id.menu_perfil ->{
                //redireccinar al perfil
                loadFragment(MiPerfilFragment())

            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        val trasaction = supportFragmentManager.beginTransaction()
        trasaction.replace(R.id.fragment_container, fragment)
        trasaction.addToBackStack(null)
        trasaction.commit()
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.main)
        navigationView = findViewById(R.id.navigationView)

        findViewById<android.widget. ImageView>(R.id.ic_menu).setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

    }
}