package com.bogazliyan.ktphaneuygulamas.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bogazliyan.ktphaneuygulamas.databinding.FragmentGalleryBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import org.w3c.dom.Text

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var buttonKitapEkle: Button

    //textler
    private lateinit var kitap_isim1 : EditText
    private lateinit var kitap_id1 : EditText
    private var kitap_teslim1 = "Hayır"
    private lateinit var kitap_sayfa_sayi1 : EditText
    private lateinit var kitap_yayinevi1 : EditText
    private lateinit var kitap_yazar1 : EditText

    private val TAG = "GalleryFragment"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        kitap_isim1 = binding.kitapIsim1
        kitap_id1 = binding.kitapId
        kitap_yayinevi1 = binding.kitapYayinevi1
        kitap_yazar1 = binding.kitapYazar
        kitap_sayfa_sayi1 = binding.kitapSayfaSayi


        buttonKitapEkle = binding.kitapEkle

        buttonKitapEkle.setOnClickListener {

            firebaseAuth = FirebaseAuth.getInstance()
            firestore = FirebaseFirestore.getInstance()

            val currentUser = firebaseAuth.currentUser
            var firebaseId: String? = null
            if (currentUser != null) {
                firebaseId = currentUser.uid
            }

            //Toast.makeText(requireContext(), "Gallery button clicked! ${firebaseId.toString()}", Toast.LENGTH_SHORT).show()


            firestore.collection("kullaniciBilgileri").document(firebaseId.toString()).get().addOnSuccessListener { documentFiels->
                var alanDegeri_kontrol = documentFiels.getString("kutuphane_bilgi")
                var alanDegeri : String? = null
                if(alanDegeri_kontrol != null){
                    alanDegeri = alanDegeri_kontrol.toString()
                    //Toast.makeText(requireContext(), "Gallery button clicked! ${alanDegeri.toString()}", Toast.LENGTH_SHORT).show()

                }

                //var data = documentFiels.data
                //var boyut = data!!.size.toString()

                val collectionReference = firestore.collection("${alanDegeri.toString()}")
                val count_sonuc = ""

                // Koleksiyon içindeki belge sayısını alın
                collectionReference.get()
                    .addOnCompleteListener(OnCompleteListener { task: Task<QuerySnapshot> ->
                        if (task.isSuccessful) {
                            // Belge sayısını alın
                            val documentCount = task.result?.size() ?: 0
                            val count_sonuc=documentCount.toString()

                            val currentTime = Timestamp.now()

                            firestore.collection("${alanDegeri.toString()}")
                                .document("kitap_"+count_sonuc)
                                .set(
                                    mapOf(
                                        "kitap_id" to kitap_id1.text.toString(),
                                        "kitap_isim" to kitap_isim1.text.toString(),
                                        "kitap_sayfa_sayi" to kitap_sayfa_sayi1.text.toString(),
                                        "kitap_yazar" to kitap_yazar1.text.toString(),
                                        "kitap_yayinevi" to kitap_yayinevi1.text.toString(),
                                        "kitap_teslim" to kitap_teslim1.toString(),
                                        "kitap_teslim_alan_kisi" to "yok",
                                        "kitap_teslim_alan_kisi_telefon" to "yok",
                                        "kitap_teslim_alma_tarih" to currentTime,

                                    ), SetOptions.merge()
                                )

                            println("Koleksiyon içindeki belge sayısı: $documentCount")
                            Toast.makeText(requireContext(), "Gallery button clicked! ${count_sonuc.toString()}", Toast.LENGTH_SHORT).show()

                        } else {
                            println("Belge sayısı alınamadı: ${task.exception?.message}")
                        }
                    })






            }

            //Toast.makeText(requireContext(), "Gallery button clicked! ${kitap_isim1.text.toString()}", Toast.LENGTH_SHORT).show()



        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}