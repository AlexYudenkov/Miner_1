package com.example.kotlin1

class Stone (WidthScreen: Int, HeightScreen: Int) {
    private val widthScreen = WidthScreen
    private val heightScreen = HeightScreen
    var coordX : Int = 2*(0..widthScreen).random()
    var coordY : Int = heightScreen+900
    var Size: Int = 0
}
