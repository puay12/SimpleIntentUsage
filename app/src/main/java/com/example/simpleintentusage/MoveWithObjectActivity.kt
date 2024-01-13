package com.example.simpleintentusage

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObject: TextView = findViewById(R.id.tv_object_received)

        val person = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Person>(EXTRA_PERSON, Person::class.java)
        } else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Person>(EXTRA_PERSON)
        }

        if (person != null){
            tvObject.text = "Name: ${person.name ?: "-"}\nAge: ${person.age.toString()}\nEmail: ${person.email ?: "-"}\nLocation: ${person.city ?: "-"}"
        }
    }
}