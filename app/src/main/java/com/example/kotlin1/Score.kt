package com.example.kotlin1

class Score() {
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
    fun increment(){
        this.score++
    }
}