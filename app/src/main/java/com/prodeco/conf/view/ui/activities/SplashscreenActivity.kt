package com.prodeco.conf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.prodeco.conf.R
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        ivLogoPlatziConf.startAnimation(animacion)

        // Cambiamos de vista al MainActivity
        val intent = Intent(this, MainActivity::class.java)

        //Ejecutar funcion al terminar la animacion
        animacion.setAnimationListener(object : Animation.AnimationListener{
            // Evento si la animacion se repite infinitamente
            override fun onAnimationRepeat(p0: Animation?) {

            }

            // Evento Cuando termina la animacion
            override fun onAnimationEnd(p0: Animation?) {
                // Llamamos la variable inten para el cambio de vista
                startActivity(intent)
                // Despues destruimos la animacion
                finish()
            }

            // Evento Cuando inicia la animacion
            override fun onAnimationStart(p0: Animation?) {

            }

        })
    }
}