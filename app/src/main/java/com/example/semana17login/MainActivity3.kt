package com.example.semana17login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class MainActivity3 : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var botonelegir: Button
    lateinit var botonesubir: Button
    lateinit var imagensubir: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        botonelegir=findViewById(R.id.btnelegir)
        botonesubir=findViewById(R.id.btnsubir)
        imagensubir=findViewById(R.id.idimagen)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        botonelegir.setOnClickListener {
            elegirimagen()
        }
        botonesubir.setOnClickListener {
            subirimagen()
        }
    }
    private fun elegirimagen(){
        val i=Intent()
        i.type="image/*"
        i.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(i,"Seleccione una imagen"),PICK_IMAGE_REQUEST)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== Activity.RESULT_OK){
            if(data==null||data.data==null){
                return
            }
            filePath=data.data
            try {
                val bitmap= MediaStore.Images.Media.getBitmap(this.contentResolver,filePath)
                imagensubir.setImageBitmap(bitmap)
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
    private fun subirimagen(){
        if(filePath !=null){
            val storage=storageReference?.child("hola123/"+ UUID.randomUUID().toString())
            val uploadTask=storage?.putFile(filePath!!)
            Toast.makeText(this, "La imagen se subio exsitosamente", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Seleccione una imagen.plis", Toast.LENGTH_SHORT).show()
        }
    }
}