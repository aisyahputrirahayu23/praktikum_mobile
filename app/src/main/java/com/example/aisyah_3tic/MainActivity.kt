package com.example.aisyah_3tic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aisyah_3tic.databinding.ActivityLoginBinding
import com.example.aisyah_3tic.databinding.ActivityMainBinding
import com.example.aisyah_3tic.pertemuan_5.LimaActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // tambah
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater) // tambah
        setContentView(binding.root) // tambah
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nama = intent.getStringExtra("nama")
        val umur = intent.getIntExtra("umur", 0)

        Log.w("Hasil", "Nama: $nama Umur: $umur")

        binding.btnKembali.setOnClickListener { // ganti jadi binding

//            val intent = Intent(this@MainActivity, LoginActivity::class.java)
//            startActivity(intent) //tambah
            finish()
        }

        binding.btnToLima.setOnClickListener { // ganti jadi binding
            val intent = Intent(this@MainActivity, LimaActivity::class.java)
            startActivity(intent)
        }

        binding.nama.text = nama

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup"){
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    Log.e("Info Snackbar","Snackbar ditutup")
                }
                .show()
        }

        binding.btnAlertDialog.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    finish()
                    Log.e("Info Dialog","Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog","Anda memilih Tidak!")
                }
                .show()
        }
    }
}