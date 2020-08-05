package com.example.kotlin1

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
//анимация

import android.view.animation.AnimationUtils
import androidx.core.view.isGone

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.kotlin1.databinding.ActivityMainBinding


//называем кнопки

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding


    //если активити опять вернулось в работу
    //то оно перезапускается


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var key = true // для фиксации однократного вызова второго активити


        // анимация бура
        val fallingAnimation = AnimationUtils.loadAnimation(this,
                R.anim.down)
        fallingAnimation.fillAfter=true


        activityMainBinding.bur.setOnClickListener {
            move(activityMainBinding.bur)
            move(activityMainBinding.groundbot2)

            //activityMainBinding.bur.startAnimation(fallingAnimation)


            // задержка 2 секунды перед переходом
            val handler2 = android.os.Handler()
            if(key) {
                handler2.postDelayed({
                    val randomIntent = Intent(this, MainActivity2::class.java)
                    //ФЛАГИ АКТИВИТИ НО РАБОТАЮТ НЕМНОГО КРИВО
                    //randomIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    //randomIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(randomIntent)
                    overridePendingTransition(R.anim.diagtranslate,R.anim.alpha)}, 3300)  //specify the number of milliseconds
                key = false
            }
        }


        activityMainBinding.menu.setOnClickListener {
            val menuInten = Intent(this, SettingsActivity::class.java)
            startActivity(menuInten)
        }

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
