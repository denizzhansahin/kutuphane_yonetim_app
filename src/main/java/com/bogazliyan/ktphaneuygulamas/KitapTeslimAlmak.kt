package com.bogazliyan.ktphaneuygulamas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.Timestamp
import com.google.firebase.firestore.SetOptions


class KitapTeslimAlmak : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitap_teslim_almak)


        val  intent = intent
        val bilgi = intent.getStringExtra("bilgi")

        var kisi_isim : EditText = findViewById(R.id.kisi_teslim_alan)
        var kisi_tel_no : EditText = findViewById(R.id.kisi_tel_no)



        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val currentUser = firebaseAuth.currentUser
        var firebaseId: String? = null
        if (currentUser != null) {
            firebaseId = currentUser.uid
        }


        var button_teslim_onay : Button = findViewById(R.id.button_teslim)
        button_teslim_onay.setOnClickListener {
            firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get().addOnSuccessListener { documentFiels ->
                var alanDegeri_kontrol = documentFiels.getString("kutuphane_bilgi")
                var alanDegeri: String? = null
                if (alanDegeri_kontrol != null) {
                    alanDegeri = alanDegeri_kontrol.toString()
                    Toast.makeText(
                        this,
                        "${alanDegeri.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val collectionReference = firestore.collection("${alanDegeri.toString()}")

                    val currentTime = Timestamp.now()

                    collectionReference.document("${bilgi.toString()}").set(mapOf(

                        "kitap_teslim_alan_kisi" to kisi_isim.text.toString(),
                        "kitap_teslim_alan_kisi_telefon" to kisi_tel_no.text.toString(),
                        "kitap_teslim_alma_tarih" to currentTime,
                        "kitap_teslim" to "Evet",

                        ), SetOptions.merge())

                }
            }

            val baslat_intent = Intent(this, MainActivity2::class.java)
            startActivity(baslat_intent)

        }







    }
}