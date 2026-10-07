package com.example.aisyah_3tic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aisyah_3tic.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding // tambah
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater) // tambah
        setContentView(binding.root) // tambah

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE) // sharedPref itu brankas
        // Kondisi jika isLogin bernilai true
//        val isLogin = sharedPref.getBoolean("isLogin", false)
//        if (isLogin) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//            return
//        }

        binding.btnLogin.setOnClickListener { // ganti jadi binding
            val user = binding.edtUsername.text.toString()
            val pass = binding.edtPassword.text.toString()

            if (user.isNotEmpty() && user == pass) {
                val editor = sharedPref.edit() // jika bisa login maka disimpan ke sharedpref
                editor.putBoolean("isLogin", true)
                editor.putString("username", user)
                editor.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }
}