package me.robbin.rmpermission

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import me.rmpermission.library.RmPermission

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_permission.setOnClickListener {
            RmPermission.request(
                this,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ) { allGrant, denied ->
                if (allGrant) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
//            val intent = Intent(Intent.ACTION_CALL)
//            intent.data = Uri.parse("tel:10086")
//            startActivity(intent)
            Toast.makeText(this, "All granted.", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}
