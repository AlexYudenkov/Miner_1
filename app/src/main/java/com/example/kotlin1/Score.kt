package com.example.kotlin1

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
    fun increment():Boolean{
        this.score++
        if(score > Record) {
            Record=score
            return true
        }else {
            return false
        }
    }
}