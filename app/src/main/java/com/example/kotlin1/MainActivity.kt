package com.example.kotlin1

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.kotlin1.databinding.ActivityMainBinding


//называем кнопки

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //присваиваем кнопкам реальные id

        activityMainBinding.bur.setOnClickListener {
            move(activityMainBinding.bur)
            move(activityMainBinding.groundbot2)
            // задержка 2 секунды перед переходом
            val handler2 = android.os.Handler()
            handler2.postDelayed({ val randomIntent = Intent(this, MainActivity2::class.java)
                startActivity(randomIntent) }, 2000) //specify the number of milliseconds

        }


        activityMainBinding.menu.setOnClickListener {
            val menuInten = Intent(this, SettingsActivity::class.java)
            startActivity(menuInten)
        }
        //создаем экземпляр листенера


        activityMainBinding.exit.setOnClickListener {
            System.exit(0)
        }


        val imageViews: MutableList<ImageView> =
            ArrayList()

        imageViews.add(findViewById<View>(R.id.bur) as ImageView)

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


    private fun move(Imageview :ImageView) {
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        flingAnimation.setStartVelocity(1050f);
        flingAnimation.friction = 0.1f;
        flingAnimation.start();

    }


}
