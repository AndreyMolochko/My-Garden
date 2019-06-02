package com.ostrovec.mygarden

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSignIn: TextView
    private lateinit var buttonSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_welcome_page)
        initViews()
        initListeners()
    }

    private fun initViews() {
        buttonSignIn = findViewById(R.id.welcome_page_sign_in)
        buttonSignUp = findViewById(R.id.welcome_page_sign_up)
    }

    private fun initListeners(){
        buttonSignIn.setOnClickListener{
            println("click on the sign in button")
        }

        buttonSignUp.setOnClickListener {
            println("click on the sign up button")
        }
    }
}
