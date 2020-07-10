package com.example.kotlin1

import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val Bur: ImageView = findViewById(R.id.bur)
        val ExitHome: ImageView = findViewById(R.id.exithome)
        val textView: TextView = findViewById(R.id.textView3)

        val imageViews: MutableList<ImageView> =
                ArrayList()

        ExitHome.setOnClickListener {
            val randomIntent = Intent(this, MainActivity::class.java)
            startActivity(randomIntent)}

        imageViews.add(findViewById<View>(R.id.bur) as ImageView)

        //экземпляр класса
        val person = Score(0)
        //animateImageViews(imageViews)
        var a = true
        Bur.setOnClickListener {
                animateImageViews(imageViews)
                person.increment()
                textView.text = person.score.toString()
        }
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

