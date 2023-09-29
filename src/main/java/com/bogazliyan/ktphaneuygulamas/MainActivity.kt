package com.bogazliyan.ktphaneuygulamas

/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
 */


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.bogazliyan.ktphaneuygulamas.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()


        binding.gizlilik3.setOnClickListener {
            val intent = Intent(this@MainActivity, GizlilikPolitikasi::class.java)
            startActivity(intent)
        }

        binding.button7.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity3::class.java)
            startActivity(intent)
        }

        binding.buttonGiris.setOnClickListener {
            val email = binding.emailGiris.text.toString()
            val pass = binding.sifreGiris.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    val currentUser = firebaseAuth.currentUser
                    var firebaseId: String? = null
                    if (currentUser != null) {
                        firebaseId = currentUser.uid
                    }


                    if (it.isSuccessful) {

                        // Firestore'dan belgeyi alma
                        firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get().addOnSuccessListener { documentSnapshot ->
                            val alanDegeri = documentSnapshot.getString("yonetici_yetki")
                            Toast.makeText(this, firebaseId.toString(), Toast.LENGTH_SHORT).show()

                            // Alan değerine göre farklı işlemler yapma
                            if (alanDegeri == "Hayır") {
                                // "Evet" değeri ise başka bir activity aç
                                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                startActivity(intent)
                            } else if (alanDegeri == "Evet") {
                                // "Hayır" değeri ise başka bir activity aç
                                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                startActivity(intent)
                            }
                        }.addOnFailureListener { e ->
                            // Hata durumunda işlemleri ele alma
                            Log.e(TAG, "Veri alma hatası: $e")
                        }
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        var firebaseId: String? = null
        if (currentUser != null) {
            firebaseId = currentUser.uid
        }
        if(firebaseAuth.currentUser != null){
            // Firestore'dan belgeyi alma
            firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get().addOnSuccessListener { documentSnapshot ->
                val alanDegeri = documentSnapshot.getString("yonetici_yetki")

                // Alan değerine göre farklı işlemler yapma
                if (alanDegeri == "Hayır") {
                    // "Evet" değeri ise başka bir activity aç
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(intent)
                } else if (alanDegeri == "Evet") {
                    // "Hayır" değeri ise başka bir activity aç
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { e ->
                // Hata durumunda işlemleri ele alma
                Log.e(TAG, "Veri alma hatası: $e")
            }
        }
    }

}