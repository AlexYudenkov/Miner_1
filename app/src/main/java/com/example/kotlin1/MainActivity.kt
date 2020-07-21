package com.example.kotlin1

import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.kotlin1.MainActivity2


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //присваиваем кнопкам реальные id
        val Bur: ImageView = findViewById(R.id.bur)
        val Tunnel: ImageView = findViewById(R.id.tunnel)
        val Menu1: ImageView = findViewById(R.id.menu)
        val Exit1: ImageView = findViewById(R.id.exit)


        var fallingAnimation = AnimationUtils.loadAnimation(this,
                R.anim.down)
        fallingAnimation.fillAfter=true
        //определение размера экрана
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x
        val height: Int = size.y

        Bur.setOnClickListener {
            //move(Bur,0)
            //move(Tunnel,0)
            Bur.startAnimation(fallingAnimation)
            // задержка 3 секунды перед переходом
            val handler = android.os.Handler()
            handler.postDelayed({ val randomIntent = Intent(this, MainActivity2::class.java)
                startActivity(randomIntent)
                overridePendingTransition(R.anim.diagonaltranslate,R.anim.alpha)},2000 ) //specify the number of milliseconds
            }






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


    private fun move(Imageview :ImageView,height: Int) {
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        flingAnimation.setStartVelocity(900f)
        flingAnimation.friction = 0.13f
        flingAnimation.start()
        //val a  = Imageview.windowVisibility
        //if (a == 4){
            //System.exit(0)
        //var a = Imageview.visibility
          //  if (a==View.VISIBLE){
            //Imageview.visibility = View.GONE}
        //}

    }


    }


