package com.example.kotlin1

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess
import com.example.kotlin1.MainActivity2 as MainActivity2


//называем кнопки

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //присваиваем кнопкам реальные id
        val Play: ImageView = findViewById(R.id.start)
        val Menu1: ImageView = findViewById(R.id.menu)
        val Exit1: ImageView = findViewById(R.id.exit)

        Play.setOnClickListener {
            val randomIntent = Intent(this, MainActivity2::class.java)
            startActivity(randomIntent)}

        Menu1.setOnClickListener {
                val menuInten = Intent(this, SettingsActivity::class.java)
                startActivity(menuInten)
            }
                //создаем экземпляр листенера


        Exit1.setOnClickListener {
                    System.exit(0)
                }


        val imageViews: MutableList<ImageView> =
            ArrayList()

        imageViews.add(findViewById<View>(R.id.imageView2) as ImageView)

        animateImageViews(imageViews)
    }

    private fun animateImageViews(imageViews: List<ImageView>) {
        for (imageView in imageViews) {
            animateImageView(imageView)
        }
    }

    private fun animateImageView(imageView: ImageView) {
        val drawable = imageView.drawable
        if (drawable is Animatable) {
            val animatable = drawable as Animatable
            animatable.start()
        }
    }
    }


