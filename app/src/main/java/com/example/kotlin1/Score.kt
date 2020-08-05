package com.example.kotlin1

import android.content.Context

class Score(Score: Int) {
    var Person_game: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var score: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var Record: Int = 0

    var aa:Int=0


    fun increment(){
        this.score++
        if(score > Record) {
            Record=score
        }
        }
    }
