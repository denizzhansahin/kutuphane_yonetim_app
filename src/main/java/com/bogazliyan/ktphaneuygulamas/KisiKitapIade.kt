package com.bogazliyan.ktphaneuygulamas

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.Locale

class KisiKitapIade : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kisi_kitap_iade)

        val  intent = intent
        val bilgi = intent.getStringExtra("bilgi")

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        var kitap_isim1 : TextView = findViewById(R.id.kitap_isim1)
        var kitap_yayinevi1 : TextView = findViewById(R.id.kitap_yayinevi1)
        var kitap_sayfa_sayi1 : TextView = findViewById(R.id.kitap_sayfa_sayi1)
        var kitap_yazar_sayi1 : TextView = findViewById(R.id.kitap_yazar_sayi1)
        var kitap_teslim_bilgi1 : TextView = findViewById(R.id.kitap_teslim_bilgi1)
        var kitap_teslim_kisi_bilgi : TextView = findViewById(R.id.kitap_teslim_kisi_bilgi)
        var kitap_teslim_kisi_telefon : TextView = findViewById(R.id.kitap_teslim_kisi_bilgi_telefon)
        var kitap_teslim_alma_tarih : TextView = findViewById(R.id.kitap_teslim_alma_tarih)


        val currentUser1 = firebaseAuth.currentUser
        var firebaseId1: String? = null

        if (currentUser1 != null) {
            firebaseId1 = currentUser1.uid
        }

        firestore.collection("kullaniciBilgileri").document(firebaseId1.toString()).get()
            .addOnSuccessListener { documentFiels ->
                var alanDegeri_kontrol = documentFiels.getString("kutuphane_bilgi")
                var alanDegeri: String? = null
                if (alanDegeri_kontrol != null) {
                    alanDegeri = alanDegeri_kontrol.toString()
                    //Toast.makeText(requireContext(), "Gallery button clicked! ${alanDegeri.toString()}", Toast.LENGTH_SHORT).show()

                }

                firestore.collection("${alanDegeri.toString()}")
                    .document("${bilgi.toString()}").get()
                    .addOnSuccessListener {
                        /////
                        val alanDegeri_kitap_id = it.getString("kitap_id")
                        val alanDegeri_kitap_isim = it.getString("kitap_isim")
                        val alanDegeri_kitap_sayfa_sayi = it.getString("kitap_sayfa_sayi")
                        val alanDegeri_kitap_teslim = it.getString("kitap_teslim")
                        val alanDegeri_kitap_teslim_kisi_bilgi =
                            it.getString("kitap_teslim_alan_kisi")
                        val alanDegeri_kitap_teslim_alan_kisi_telefon =
                            it.getString("kitap_teslim_alan_kisi_telefon")
                        val alanDegeri_kitap_yayinevi = it.getString("kitap_yayinevi")
                        val alanDegeri_kitap_yazar = it.getString("kitap_yazar")

                        val timestamp = it.getTimestamp("kitap_teslim_alma_tarih")

                        // Timestamp'i gg/aa/yy formatına dönüştürün
                        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                        val formattedDate = dateFormat.format(timestamp?.toDate() ?: 0)



                        kitap_isim1.text = alanDegeri_kitap_isim.toString()
                        kitap_yayinevi1.text = alanDegeri_kitap_yayinevi.toString()
                        kitap_sayfa_sayi1.text = alanDegeri_kitap_sayfa_sayi.toString()
                        kitap_yazar_sayi1.text = alanDegeri_kitap_yazar.toString()
                        kitap_teslim_bilgi1.text = alanDegeri_kitap_teslim.toString()
                        kitap_teslim_kisi_bilgi.text =
                            alanDegeri_kitap_teslim_kisi_bilgi.toString()
                        kitap_teslim_kisi_telefon.text =
                            alanDegeri_kitap_teslim_alan_kisi_telefon.toString()
                        kitap_teslim_alma_tarih.text =
                            formattedDate.toString()
                        /////
                    }
            }


        val currentUser = firebaseAuth.currentUser
        var firebaseId: String? = null
        if (currentUser != null) {
            firebaseId = currentUser.uid
        }



        var button_iade_onay : Button = findViewById(R.id.islem)
        button_iade_onay.setOnClickListener {

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

                        "kitap_teslim_alan_kisi" to "yok",
                        "kitap_teslim_alan_kisi_telefon" to "yok",
                        "kitap_teslim_alma_tarih" to currentTime,
                        "kitap_teslim" to "Hayır",

                        ), SetOptions.merge())

                }
            }

            val baslat_intent = Intent(this, MainActivity2::class.java)
            startActivity(baslat_intent)

        }



    }
}