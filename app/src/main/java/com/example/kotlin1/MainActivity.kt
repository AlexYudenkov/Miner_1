package com.example.kotlin1

//анимация

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
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
                    overridePendingTransition(R.anim.diagtranslate,R.anim.alpha)}, 2500)  //specify the number of milliseconds
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


        var aa:Int=0
        val sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE)
        if(sharedPref.contains("score")) {
            activityMainBinding.record2.setText(sharedPref.getInt("score",aa).toString())
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
