package com.coding.a27rockpaperscissor

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.coding.a27rockpaperscissor.R
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class MainActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance()
    var rootRef = db.reference
    var gameRef = rootRef.child("game")

    //    get UI elements
    var textView: TextView? = null
    var rock: Button? = null
    var paper: Button? = null
    var scissor: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        rootRef.child("Users").child("01").child("Email").setValue("some@cool.com");
//        rootRef.child("Users").child("01").child("name").setValue("sam");
        textView = findViewById<View>(R.id.textView) as TextView
        rock = findViewById<View>(R.id.rock) as Button
        paper = findViewById<View>(R.id.paper) as Button
        scissor = findViewById<View>(R.id.scissor) as Button
        rock!!.setOnClickListener { gameRef.setValue("Rock") }
        paper!!.setOnClickListener { gameRef.setValue("Paper") }
        scissor!!.setOnClickListener { gameRef.setValue("Scissor") }
    }

    override fun onStart() {
        super.onStart()
        gameRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val text = dataSnapshot.value.toString()
                textView!!.text = text
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("TAG", "Something is missing here")
            }
        })
    }
}