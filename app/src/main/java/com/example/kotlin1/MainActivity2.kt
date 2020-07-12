package com.example.kotlin1

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val Bur: ImageView = findViewById(R.id.bur)
        val ExitHome: ImageView = findViewById(R.id.exithome)
        val textView: TextView = findViewById(R.id.textView3)
        val container: FrameLayout = findViewById(R.id.container)


        val displaymetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymetrics)
        val screenWidth = displaymetrics.widthPixels
        val screenHeight = displaymetrics.heightPixels






        val Stone = Stone(screenWidth, screenHeight)

        val iv = ImageView(this)
        iv.setImageResource(R.drawable.ic_stone_noshadow)
        container.addView(iv,Stone.coordX,Stone.coordY)
        iv.scaleX = 0.1f
        iv.scaleY = 0.1f






        val imageViews: MutableList<ImageView> =
                ArrayList()

        ExitHome.setOnClickListener {
            val randomIntent = Intent(this, MainActivity::class.java)
            startActivity(randomIntent)
            }

        imageViews.add(findViewById<View>(R.id.bur) as ImageView)

        //экземпляр класса
        val person = Score()
        //animateImageViews(imageViews)
        var a = false
        Bur.setOnClickListener {
                if (!a){
                    animateImageViews(imageViews)
                    //движение до середины
                    move(Bur, 100f,0.1f)
                    a = true
                }
                move(iv,-100f,0.1f)
                person.increment()
                textView.text = person.score.toString()
        }
    }
    private fun move(Imageview :ImageView,Velocity : Float, friction : Float){
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        flingAnimation.setStartVelocity(Velocity);
        flingAnimation.friction = friction;
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

