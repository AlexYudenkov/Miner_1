package com.example.kotlin1

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.kotlin1.fragmentpack.OneStone
import com.example.kotlin1.fragmentpack.TwoStone
import kotlinx.android.synthetic.main.stone.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity2 : AppCompatActivity() {
    val stonefirst = OneStone()
    val stonesecond = TwoStone()
    var manager: FragmentManager = fragmentManager
    var transiction: FragmentTransaction = manager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val Bur: ImageView = findViewById(R.id.bur1)
        val Ground1: View = findViewById(R.id.container)
        val Ground2: View = findViewById(R.id.container2)
        val Tunnel1: ImageView = findViewById(R.id.Tunnel)
        Tunnel1.y = Tunnel1.y - 2020 // Почему-то тунели из двух активити связаны, если убарть это изменение координат, то тунель во втором активити будет на месте координатах первого
        val ExitHome: ImageView = findViewById(R.id.exithome)
        val textView: TextView = findViewById(R.id.textView3)
        var i = 0



        val imageViews: MutableList<ImageView> =
                ArrayList()

        start()
        move(Bur)
        move(Tunnel1)


        ExitHome.setOnClickListener {
            onDestroy()
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


                move2(Ground1, 1)
                move2(Ground2, 2)




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



    private fun move2(view: View, i: Int ){
       if(view.y < -1700){
           randomCord(view, i)
           view.y += 3850
       }
        else
        {
            view.visibility = View.VISIBLE
            val flingAnimation = FlingAnimation(view, DynamicAnimation.Y)
            flingAnimation.setStartVelocity(-350f);
            flingAnimation.friction = 0.3f;
            flingAnimation.start();
        }


    }

    private fun animateImageViews(imageViews: List<ImageView>) {
        for (imageView in imageViews) {
            animateImageView(imageView)
        }
    }

    private fun randomCord(view: View, i: Int)
    {  val SignRandom = Random()
        if(SignRandom.nextBoolean()) {
            if (i == 1) {
                val random = Random()
                stonefirst.stone.y += random.nextInt(30)
                stonefirst.stone.x -= random.nextInt(30)
                stonefirst.stone1.y += random.nextInt(30)
                stonefirst.stone1.x -= random.nextInt(30)
                stonefirst.stone2.y += random.nextInt(30)
                stonefirst.stone2.x -= random.nextInt(30)
                stonefirst.stone3.y += random.nextInt(30)
                stonefirst.stone3.x -= random.nextInt(30)
            }
            if (i == 2) {
                val random = Random()
                stonesecond.stone.y -= random.nextInt(30)
                stonesecond.stone.x += random.nextInt(30)
                stonesecond.stone1.y -= random.nextInt(30)
                stonesecond.stone1.x += random.nextInt(30)
                stonesecond.stone2.y -= random.nextInt(30)
                stonesecond.stone2.x += random.nextInt(30)
                stonesecond.stone3.y -= random.nextInt(30)
                stonesecond.stone3.x += random.nextInt(30)

            }

        }
        else{
            if (i == 1) {
                val random = Random()
                stonefirst.stone.y -= random.nextInt(30)
                stonefirst.stone.x += random.nextInt(30)
                stonefirst.stone1.y -= random.nextInt(30)
                stonefirst.stone1.x += random.nextInt(30)
                stonefirst.stone2.y -= random.nextInt(30)
                stonefirst.stone2.x += random.nextInt(30)
                stonefirst.stone3.y -= random.nextInt(30)
                stonefirst.stone3.x += random.nextInt(30)
            }
            if (i == 2) {
                val random = Random()
                stonesecond.stone.y += random.nextInt(30)
                stonesecond.stone.x -= random.nextInt(30)
                stonesecond.stone1.y += random.nextInt(30)
                stonesecond.stone1.x -= random.nextInt(30)
                stonesecond.stone2.y += random.nextInt(30)
                stonesecond.stone2.x -= random.nextInt(30)
                stonesecond.stone3.y += random.nextInt(30)
                stonesecond.stone3.x -= random.nextInt(30)

            }

        }
    }


    private fun start(){
        transiction = manager.beginTransaction()
        transiction.add(R.id.container2, stonesecond)
        transiction.add(R.id.container, stonefirst)
        transiction.commit()

    }

    private fun animateImageView(imageView: ImageView) {
        val drawable = imageView.drawable
        if (drawable is Animatable) {
            val animatable = drawable as Animatable
            animatable.start()
        }
    }

    // запретить нажимать кнопку back
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) { true }
        else {false}
    }
}

