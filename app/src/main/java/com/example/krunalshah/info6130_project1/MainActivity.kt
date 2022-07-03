package com.example.krunalshah.info6130_project1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransactionManager: FragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransactionManager.add(R.id.fragmentContainer, MainFragment())
        fragmentTransactionManager.addToBackStack(null)
        fragmentTransactionManager.commit()
    }
}