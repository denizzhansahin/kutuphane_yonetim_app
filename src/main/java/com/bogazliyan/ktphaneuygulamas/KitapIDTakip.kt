package com.bogazliyan.ktphaneuygulamas

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale

class KitapIDTakip : AppCompatActivity() {

    private val TAG = "KitapIDTakip"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitap_idtakip)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        var kitap_id_bul_text : EditText = findViewById(R.id.kitap_id_bul)

        var kitap_isim1 : TextView = findViewById(R.id.kitap_isim1)
        var kitap_yayinevi1 : TextView = findViewById(R.id.kitap_yayinevi1)
        var kitap_sayfa_sayi1 : TextView = findViewById(R.id.kitap_sayfa_sayi1)
        var kitap_yazar_sayi1 : TextView = findViewById(R.id.kitap_yazar_sayi1)
        var kitap_teslim_bilgi1 : TextView = findViewById(R.id.kitap_teslim_bilgi1)
        var kitap_teslim_kisi_bilgi : TextView = findViewById(R.id.kitap_teslim_kisi_bilgi)
        var kitap_teslim_kisi_telefon : TextView = findViewById(R.id.kitap_teslim_kisi_bilgi_telefon)
        var kitap_teslim_alma_tarih : TextView = findViewById(R.id.kitap_teslim_alma_tarih)

        var islem : Button = findViewById(R.id.islem)


        islem.setOnClickListener {
            ///
            val currentUser = firebaseAuth.currentUser
            var firebaseId: String? = null
            if (currentUser != null) {
                firebaseId = currentUser.uid
            }

            // `kitap_id_bul_text` edittextinin değerinin boş olmaması için bir kontrol
            if (kitap_id_bul_text.text.isEmpty()) {
                Toast.makeText(this, "Lütfen kitap ID'sini giriniz.", Toast.LENGTH_SHORT).show()

            }
            else{

                firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get()
                    .addOnSuccessListener { documentFiels ->
                        var alanDegeri_kontrol = documentFiels.getString("kutuphane_bilgi")
                        var alanDegeri: String? = null
                        if (alanDegeri_kontrol != null) {
                            alanDegeri = alanDegeri_kontrol.toString()
                            //Toast.makeText(requireContext(), "Gallery button clicked! ${alanDegeri.toString()}", Toast.LENGTH_SHORT).show()

                        }

                        firestore.collection("${alanDegeri.toString()}")
                            .document("${kitap_id_bul_text.text.toString()}").get()
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
            ///
        }
        }




    }
}