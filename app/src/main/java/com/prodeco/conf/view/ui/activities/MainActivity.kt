package com.prodeco.conf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.prodeco.conf.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agregamos el ToolBar Personalizado que creamos
        setActionBar(findViewById(R.id.toolbarMain))

        configNav()
    }

    fun configNav() {
        // configurar la navegacion atravez del navController que creamos para el principal fragmento: fragContent
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent))
    }
}