package com.simple.banksystem2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var startBtn :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callViews()
startBtn.setOnClickListener {
    val i =Intent(this,CustomerActivity::class.java)
    startActivity(i)
}
    }
    private fun callViews(){
        startBtn =findViewById(R.id.start)
    }
}