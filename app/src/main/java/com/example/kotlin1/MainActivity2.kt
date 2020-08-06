package com.example.kotlin1

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val Bur: ImageView = findViewById(R.id.bur1)
        val GroundBot: ImageView = findViewById(R.id.groundbot3)
        val ExitHome: ImageView = findViewById(R.id.exithome)
        val textView: TextView = findViewById(R.id.textView3)


        //обработка нажатия кнопки наза

        ExitHome.setOnClickListener {
            val randomIntent = Intent(this, MainActivity::class.java)
            startActivity(randomIntent) }


        movestart(GroundBot, Bur)


        //var record:Int = -1
        //запись насроек
        //добавление рекордов
        val sharedPref = getSharedPreferences("record", Context.MODE_PRIVATE)
        //считывание рекорда из файла настроек
        var record:Int = sharedPref.getInt("score",0)
        //приготовка к записи
        val editor = sharedPref.edit()


        //экземпляр класса очков
        val person = Score(0)
        //записали рекорд предыдущий
        person.Record = record
        //animateImageViews(imageViews)
        Bur.setOnClickListener {



            moveTunnel(Tunnel1)
            moveTunnel(Tunnel2)

            if(person.increment()){
                //записываем в хеш
                editor.putInt("score",person.Record)
                editor.apply()
            }



            textView.text = person.score.toString()
        }


    }




    private fun move(Imageview: ImageView) {
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        flingAnimation.setStartVelocity(260f);
        flingAnimation.friction = 0.1f;
        flingAnimation.start();
    }

    private fun moveTunnel(Imageview: ImageView) {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        //val width: Int = size.x
        val height: Int = size.y

        if(Imageview.y <= -(height)){
            Imageview.y += 2*height
        }
        else {
            val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
            flingAnimation.setStartVelocity(-260f);
            flingAnimation.friction = 0.1f;
            flingAnimation.start();
        }
    }

    //Шаманская штука(лучше не трогать)
    private fun movestart(Imageview: ImageView, Imageview2: ImageView) {
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        val flingAnimation2 = FlingAnimation(Imageview2, DynamicAnimation.Y)
        flingAnimation2.setStartVelocity(250f);
        flingAnimation2.friction = 0.1f;
        flingAnimation2.start();
        val handler = android.os.Handler()
        handler.postDelayed({
            flingAnimation.setStartVelocity(250f);
            flingAnimation.friction = 0.1f;
            flingAnimation.start();}, 1)


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

    // запретить нажимать кнопку back
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            val randomIntent = Intent(this, MainActivity::class.java)
            startActivity(randomIntent)
            true }
        else {false}
    }
}

