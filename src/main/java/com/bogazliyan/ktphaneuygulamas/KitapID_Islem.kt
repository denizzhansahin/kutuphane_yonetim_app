package com.bogazliyan.ktphaneuygulamas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class KitapID_Islem : AppCompatActivity() {

    private val TAG = "MainActivity555555555"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitap_id_islem)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val currentUser = firebaseAuth.currentUser
        var firebaseId: String? = null
        if (currentUser != null) {
            firebaseId = currentUser.uid
        }


        var metin_islem : EditText = findViewById(R.id.editTextText6)

        var button_islem : Button = findViewById(R.id.button5)
        button_islem.setOnClickListener {

            if(metin_islem.text.toString().isNotEmpty())
            {
            firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get().addOnSuccessListener { documentFiels ->
                var alanDegeri_kontrol = documentFiels.getString("kutuphane_bilgi")
                var alanDegeri: String? = null
                if (alanDegeri_kontrol != null) {
                    alanDegeri = alanDegeri_kontrol.toString()
                    /*Toast.makeText(
                        this,
                        "${alanDegeri.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()

                     */

                    val collectionReference = firestore.collection("${alanDegeri.toString()}")

                    collectionReference.document("${metin_islem.text.toString()}").get()
                        .addOnSuccessListener {
                            var alanDegeri_kontrol2 = it.getString("kitap_teslim")

                            Toast.makeText(
                                this,
                                "${alanDegeri_kontrol2.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()

                            if (alanDegeri_kontrol2 == "Hayır") {
                                val baslat_intent = Intent(this, KitapTeslimAlmak::class.java)
                                baslat_intent.putExtra("bilgi", "${metin_islem.text.toString()}")
                                startActivity(baslat_intent)
                            } else if (alanDegeri_kontrol2 == "Evet") {
                                val baslat_intent = Intent(this, KisiKitapIade::class.java)
                                baslat_intent.putExtra("bilgi", "${metin_islem.text.toString()}")
                                startActivity(baslat_intent)
                            } else {
                                Toast.makeText(
                                    this,
                                    "Kitap ID hatalı veya bilgi bulunamadı",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        }

                }
            }
            }



        }

    }
}