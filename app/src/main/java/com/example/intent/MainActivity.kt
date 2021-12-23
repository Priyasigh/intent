package com.example.intent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnemail:Button
    lateinit var btngmail:Button
    lateinit var btnwebsite:Button
    lateinit var btnsms:Button
    lateinit var btnCallUser:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCallUser= findViewById(R.id.btncall)
        btnemail=findViewById(R.id.btnemail)
        btngmail=findViewById(R.id.btngmail)
        btnsms=findViewById(R.id.btnsms)
        btnwebsite=findViewById(R.id.btnweb)
    }


    override fun onStart() {
        super.onStart()

        btnemail.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("pq9909312@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "send Email message")
            intent.putExtra(Intent.EXTRA_TEXT, "Welcome To my Email")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, ""))
            }
        }

        btngmail.setOnClickListener {
            val UriText = "mailto:pq9909312@gmail.com" +
                    "?subject=" + Uri.encode("About") +
                    "&body=" + Uri.encode("helloo world....")


            val uri = Uri.parse(UriText)

            val sendIntent = Intent(Intent.ACTION_SENDTO)
            sendIntent.data = uri
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(sendIntent, "send Email"))
            }
        }

        btnwebsite.setOnClickListener {
            val browserintent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            if (browserintent.resolveActivity(packageManager) != null) {
                startActivity(browserintent)
            }
        }

        btnsms.setOnClickListener {
            val smsUri = Uri.parse("9173310184")
            val intent = Intent(Intent.ACTION_VIEW, smsUri)
            intent.putExtra("address", "hari")
            intent.putExtra("sms_body", "hello")
            intent.type = "vnd.android-dir/mms-sms"

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        btnCallUser.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:8320273480")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestForPermission()
            } else {
                startActivity(callIntent)
            }
        }
    }

            private fun requestForPermission() {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }

            override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<out String>,
                grantResults: IntArray
            ) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)

                if (requestCode == 1) {
                    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }





