package com.example.aisyah_3tic.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.aisyah_3tic.Home.pertemuan_5.LimaActivity
import com.example.aisyah_3tic.Home.pertemuan_6.EnamActivity
import com.example.aisyah_3tic.LoginActivity
import com.example.aisyah_3tic.R
import com.example.aisyah_3tic.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val nama = requireActivity().intent.getStringExtra("nama")
        val umur = requireActivity().intent.getIntExtra("umur", 0)

        Log.w("Hasil", "Nama: $nama Umur: $umur")

        binding.btnKembali.setOnClickListener { // ganti jadi binding

//            val intent = Intent(this@MainActivity, LoginActivity::class.java)
//            startActivity(intent) //tambah
            requireActivity().finish()
        }

        binding.btnToLima.setOnClickListener { // ganti jadi binding
            val intent = Intent(requireContext(), LimaActivity::class.java)
            startActivity(intent)
        }

        binding.btnToEnam.setOnClickListener { // ganti jadi binding
            val intent = Intent(requireContext(), EnamActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

//        binding.nama.text = nama

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup"){
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    Log.e("Info Snackbar","Snackbar ditutup")
                }
                .show()
        }

        binding.btnAlertDialog.setOnClickListener{
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    requireActivity().finish()
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