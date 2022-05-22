package com.example.semana17login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        auth = FirebaseAuth.getInstance()
        btnlo.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        btnre.setOnClickListener {
            if (idemail.text.isEmpty()||idcontra.text.isEmpty()||idconficontra.text.isEmpty()){
                Toast.makeText(this, "Vacio los campos", Toast.LENGTH_SHORT).show()
            }
            else{
                if (idconficontra.text.toString().equals(idcontra.text.toString())){
                    if (idcontra.text.toString().length<6){
                        Toast.makeText(this, "Minimo 6 caracteres", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        auth.createUserWithEmailAndPassword(idemail.text.toString(),idcontra.text.toString()).addOnCompleteListener(this){task ->
                            if (task.isSuccessful){
                                Log.d(ContentValues.TAG,"createUserWithEmail:success")
                                val usuario=auth.currentUser
                                Toast.makeText(this, "Se ha guardado bien profe", Toast.LENGTH_SHORT).show()
                                idcontra.text=null
                                idemail.text=null
                                idconficontra.text=null
                            }
                            else{
                                Log.w(ContentValues.TAG,"createUserWithEmail:failure",task.exception)
                                Toast.makeText(this, "No se pudo anda jugar LOL", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(this, "No son iguales las contraseñas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}