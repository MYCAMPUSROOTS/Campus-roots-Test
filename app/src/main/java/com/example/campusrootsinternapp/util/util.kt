package com.example.campusrootsinternapp.util

import android.os.Handler

fun delayFor(millseconds:Long,action:() -> Unit ){
    Handler().postDelayed({
        action()
    }, millseconds)
}