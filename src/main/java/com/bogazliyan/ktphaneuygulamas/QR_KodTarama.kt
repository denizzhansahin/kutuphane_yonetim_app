package com.bogazliyan.ktphaneuygulamas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
class QR_KodTarama : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_kod_tarama)
    }
}
*/


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator

class QR_KodTarama : AppCompatActivity() {

    var deger : Any? = "NOT FOUND QR CODE : ERROR"

    private val TAG = "MainActivity555555555"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_kod_tarama)



        // QR kodunu tara
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("QR kodunu tara")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(true) // Kamera yönünü kilitleme
        integrator.initiateScan()



        var button_home_go : Button = findViewById(R.id.button3)
        button_home_go.setOnClickListener{
            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }






    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // QR kodunun sonuçlarını işle
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents.isNullOrEmpty()) {

            val textView: TextView = findViewById(R.id.textView11)
            deger = "NOT FOUND QR CODE"
            textView.text = "NOT FOUND QR CODE"

        } else {

            val textView: TextView = findViewById(R.id.textView11)
            deger = result.contents.toString()

            textView.text = deger.toString()

            /*
            val baslat_intent = Intent(this, KitapTeslimAlmak::class.java)
            baslat_intent.putExtra("bilgi", deger.toString())
            startActivity(baslat_intent)
             */

            firebaseAuth = FirebaseAuth.getInstance()
            firestore = FirebaseFirestore.getInstance()

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
                    Toast.makeText(
                        this,
                        "${alanDegeri.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val collectionReference = firestore.collection("${alanDegeri.toString()}")

                    collectionReference.document("${deger.toString()}").get().addOnSuccessListener {
                        var alanDegeri_kontrol2 = it.getString("kitap_teslim")

                        Toast.makeText(
                            this,
                            "${alanDegeri_kontrol2.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()

                        if (alanDegeri_kontrol2 == "Hayır") {
                            val baslat_intent = Intent(this, KitapTeslimAlmak::class.java)
                            baslat_intent.putExtra("bilgi", deger.toString())
                            startActivity(baslat_intent)
                        }

                        if (alanDegeri_kontrol2 == "Evet") {
                            val baslat_intent = Intent(this, KisiKitapIade::class.java)
                            baslat_intent.putExtra("bilgi", deger.toString())
                            startActivity(baslat_intent)
                        }

                    }

                }
            }



        }
    }

    fun copyToClipboard(text: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", text)
        clipboardManager.setPrimaryClip(clipData)
    }
}