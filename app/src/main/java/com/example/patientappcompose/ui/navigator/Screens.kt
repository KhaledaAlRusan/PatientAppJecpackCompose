package com.example.patientappcompose.ui.navigator

sealed class Screens(val route:String){
    object MainScreen:Screens("main_screen")
    object AddScreen:Screens("add_screen")
}
