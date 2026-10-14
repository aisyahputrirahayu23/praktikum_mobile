package com.example.aisyah_3tic.Home.pertemuan_5

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aisyah_3tic.R
import com.example.aisyah_3tic.databinding.ActivityLimaBinding
import com.example.aisyah_3tic.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding // tambah
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater) // tambah
        setContentView(binding.root) // tambah
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengaktifkan toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web PCR"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://pcr.ac.id")

        // Agar Toolbar hide/show saat scroll web
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // sembunyikan
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true) // tampilkan
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}