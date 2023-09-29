package com.bogazliyan.ktphaneuygulamas

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale

class TumKitaplar : AppCompatActivity() {

    private val TAG = "MainActivity5"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kutuphane_disinda_kitaplar)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_kdk)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val currentUser = firebaseAuth.currentUser
        var firebaseId: String? = null
        if (currentUser != null) {
            firebaseId = currentUser.uid
        }

        firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get().addOnSuccessListener { documentFiels ->
            var alanDegeri_kontrol = documentFiels.getString("kutuphane_bilgi")
            var alanDegeri: String? = null
            if (alanDegeri_kontrol != null) {
                alanDegeri = alanDegeri_kontrol.toString()
                Toast.makeText(this, "Gallery button clicked! ${alanDegeri.toString()}", Toast.LENGTH_SHORT).show()

            }

            var data = ArrayList<KutuphaneDisindakiKitaplarDataClass>()

            val collectionReference = firestore.collection("${alanDegeri.toString()}")

            collectionReference.get().addOnSuccessListener { documents ->
                val data2 =  ArrayList<KutuphaneDisindakiKitaplarDataClass>() // Alan adı ve belge kimliğini saklamak için bir liste

                for (document in documents) {
                    if (document.getString("kitap_teslim") != null ) {

                        val data = document.data // Belge içindeki tüm veriyi alın
                        // Belge içindeki her alanı kontrol edin
                        // Değer eşleşiyorsa

                        val alanDegeri_kitap_id = document.getString("kitap_id")
                        val alanDegeri_kitap_isim = document.getString("kitap_isim")
                        val alanDegeri_kitap_sayfa_sayi = document.getString("kitap_sayfa_sayi")
                        val alanDegeri_kitap_teslim = document.getString("kitap_teslim")
                        val alanDegeri_kitap_teslim_kisi_bilgi =
                            document.getString("kitap_teslim_alan_kisi")
                        val alanDegeri_kitap_teslim_alan_kisi_telefon =
                            document.getString("kitap_teslim_alan_kisi_telefon")
                        val alanDegeri_kitap_yayinevi = document.getString("kitap_yayinevi")
                        val alanDegeri_kitap_yazar = document.getString("kitap_yazar")

                        val timestamp = document.getTimestamp("kitap_teslim_alma_tarih")

                        // Timestamp'i gg/aa/yy formatına dönüştürün
                        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                        val formattedDate = dateFormat.format(timestamp?.toDate() ?: 0)

                        data2.add(
                            KutuphaneDisindakiKitaplarDataClass(
                                alanDegeri_kitap_id.toString(),
                                alanDegeri_kitap_isim.toString(),
                                alanDegeri_kitap_sayfa_sayi.toString(),
                                alanDegeri_kitap_teslim.toString(),
                                alanDegeri_kitap_teslim_kisi_bilgi.toString(),
                                alanDegeri_kitap_teslim_alan_kisi_telefon.toString(),
                                alanDegeri_kitap_yayinevi.toString(),
                                alanDegeri_kitap_yazar.toString(),
                                formattedDate.toString(),

                                )
                        )


                    }//
                } //


                val adapter = KutuphaneDisindakiKitaplarAdapter(data2)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            }.addOnFailureListener { exception ->
                // Sorgu sırasında bir hata oluşursa burada işlemler yapabilirsiniz
            }


            /*
            collectionReference.document().get().addOnSuccessListener {
                if (it.data == null){
                    Toast.makeText(this, "Hata Kod : Size = null", Toast.LENGTH_SHORT).show()

                }
                else if(it.data!= null)
                {
                    for(document in it.data!!){
                        if (document.key.toString() == "kitap_teslim"){
                            if (document.value.toString() != "yok"){

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

                                data.add(KutuphaneDisindakiKitaplarDataClass(
                                    alanDegeri_kitap_id.toString(),
                                    alanDegeri_kitap_isim.toString(),
                                    alanDegeri_kitap_sayfa_sayi.toString(),
                                    alanDegeri_kitap_teslim.toString(),
                                    alanDegeri_kitap_teslim_kisi_bilgi.toString(),
                                    alanDegeri_kitap_teslim_alan_kisi_telefon.toString(),
                                    alanDegeri_kitap_yayinevi.toString(),
                                    alanDegeri_kitap_yazar.toString(),

                                ))
                            }

                        }
                    }
                }
            }

             */
        }


    }
}