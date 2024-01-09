package com.example.mathsgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition : Button//
    private lateinit var subtraction : Button
    lateinit var multi : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.buttonadd)
        subtraction = findViewById(R.id.buttonsub)
        multi = findViewById(R.id.buttonmulti)

        addition.setOnClickListener {
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)
        }

        subtraction.setOnClickListener {
            val x = Intent(this@MainActivity,GameSub::class.java)
            startActivity(x)
        }

        multi.setOnClickListener {
            val y = Intent(this@MainActivity,GameMul::class.java)
            startActivity(y)
        }

    }
}