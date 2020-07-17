package com.example.kotlin1

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val Bur: ImageView = findViewById(R.id.bur1)
        val Ground: ImageView = findViewById(R.id.ground)
        val Tunnel1: ImageView = findViewById(R.id.Tunnel)
        Tunnel1.y = Tunnel1.y - 2020 // Почему-то тунели из двух активити связаны, если убарть это изменение координат, то тунель во втором активити будет на месте координатах первого
        val ExitHome: ImageView = findViewById(R.id.exithome)
        val textView: TextView = findViewById(R.id.textView3)

        val imageViews: MutableList<ImageView> =
                ArrayList()

        move(Bur)
        move(Tunnel1)

        ExitHome.setOnClickListener {
            val randomIntent = Intent(this, MainActivity::class.java)
            startActivity(randomIntent)
            }

        imageViews.add(findViewById<View>(R.id.bur1) as ImageView)

        //экземпляр класса
        val person = Score(0)
        //animateImageViews(imageViews)
        var a = false
        Bur.setOnClickListener {
                if (!a){
                    animateImageViews(imageViews)
                    a = true
                }
                person.increment()
                textView.text = person.score.toString()
        }
    }

    private fun move(Imageview :ImageView){
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        flingAnimation.setStartVelocity(260f);
        flingAnimation.friction = 0.1f;
        flingAnimation.start();
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

