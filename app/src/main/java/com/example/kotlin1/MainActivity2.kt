package com.example.kotlin1

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
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

        fun onBackPressed(){
            super.onBackPressed()
            val randomIntent = Intent(this, MainActivity::class.java)
            startActivity(randomIntent)
        }


        val Bur: ImageView = findViewById(R.id.bur1)
        val GroundBot: ImageView = findViewById(R.id.groundbot3)
        val ExitHome: ImageView = findViewById(R.id.exithome)
        val textView: TextView = findViewById(R.id.textView3)


        //обработка нажатия кнопки наза



        val imageViews: MutableList<ImageView> =
                ArrayList()


        movestart(GroundBot, Bur)



        ExitHome.setOnClickListener {
            onDestroy()
        }

        imageViews.add(findViewById<View>(R.id.bur1) as ImageView)

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
        var a = false
        Bur.setOnClickListener {
            if (!a) {
                animateImageViews(imageViews)
                a = true
            }


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
        if(Imageview.y <= -2200){
            Imageview.y += 4000
        }
            else {
            val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
            flingAnimation.setStartVelocity(-260f);
            flingAnimation.friction = 0.1f;
            flingAnimation.start();
        }
    }

    private fun movestart(Imageview: ImageView, Imageview2: ImageView) {
        Imageview.y +=590f
        val flingAnimation = FlingAnimation(Imageview, DynamicAnimation.Y)
        val flingAnimation2 = FlingAnimation(Imageview2, DynamicAnimation.Y)
        flingAnimation.setStartVelocity(300f);
        flingAnimation.friction = 0.1f;
        flingAnimation.start();
        flingAnimation2.setStartVelocity(300f);
        flingAnimation2.friction = 0.1f;
        flingAnimation2.start();
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

