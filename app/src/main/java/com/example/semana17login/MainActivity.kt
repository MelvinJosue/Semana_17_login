package com.example.semana17login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtcuenta.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
            finish()
        }
        btningresar.setOnClickListener {
            if (txtemail.text.isEmpty()||txtcontra.text.isEmpty()) {
                Toast.makeText(this, "Rellene los campos prehestablecidos", Toast.LENGTH_SHORT).show()
            }
            else {
                val correo = txtemail.text.toString()
                val contrasenia=txtcontra.text.toString()
                auth.signInWithEmailAndPassword(correo,contrasenia).addOnCompleteListener(this){
                    if (it.isSuccessful){
                        val i = Intent(this, MainActivity3::class.java)
                        startActivity(i)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Credenciales incorrectas.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}