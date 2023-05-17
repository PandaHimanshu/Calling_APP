package com.example.call

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var etnumber:EditText
    lateinit var btn:Button
    var usernumber:String=""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etnumber=findViewById(R.id.editTextNumber)
        btn=findViewById(R.id.button)

        btn.setOnClickListener {
            usernumber=etnumber.text.toString()
            startCall(usernumber)
        }
    }

    fun startCall(usernumber:String){

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),100)
        }
        else{
            val intent=Intent(Intent.ACTION_CALL)
            intent.data= Uri.parse("tel:$usernumber")
            startActivity(intent)
        }
    }
}