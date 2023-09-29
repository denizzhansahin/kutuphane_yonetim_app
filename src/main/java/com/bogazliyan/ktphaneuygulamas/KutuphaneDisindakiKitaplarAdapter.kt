package com.bogazliyan.ktphaneuygulamas



import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

open class KutuphaneDisindakiKitaplarAdapter(private val mList: List<KutuphaneDisindakiKitaplarDataClass>) :
    RecyclerView.Adapter<KutuphaneDisindakiKitaplarAdapter.ViewHolder>() {


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kutuphane_disinda_kitaplar_cardview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        //holder.textView.text = itemsViewModel.zaman
        holder.kitap_isim_kdk.text = itemsViewModel.kitap_isim
        holder.kitap_sayfa_sayi_kdk.text = itemsViewModel.kitap_sayfa_sayi
        holder.kitap_teslim_kdk.text = itemsViewModel.kitap_teslim
        holder.kitap_teslim_alan_kisi_kdk.text = itemsViewModel.kitap_teslim_alan_kisi
        holder.kitap_teslim_alan_kisi_telefon_kdk.text = itemsViewModel.kitap_teslim_alan_kisi_telefon
        holder.kitap_yayinevi.text = itemsViewModel.kitap_yayinevi
        holder.kitap_yazar_kdk.text = itemsViewModel.kitap_yazar
        holder.kitap_teslim_alma_tarih_kdk.text = itemsViewModel.kitap_teslim_alma_tarih



    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var textView: TextView = itemView.findViewById(R.id.textView9)
        var kitap_isim_kdk : TextView = itemView.findViewById(R.id.kitap_isim_kdk)
        var kitap_sayfa_sayi_kdk : TextView = itemView.findViewById(R.id.kitap_sayfa_sayi_kdk)
        var kitap_teslim_kdk : TextView = itemView.findViewById(R.id.kitap_teslim_kdk)
        var kitap_teslim_alan_kisi_kdk : TextView = itemView.findViewById(R.id.kitap_teslim_alan_kisi_kdk)
        var kitap_teslim_alan_kisi_telefon_kdk : TextView = itemView.findViewById(R.id.kitap_teslim_alan_kisi_telefon_kdk)
        var kitap_yayinevi : TextView = itemView.findViewById(R.id.kitap_yayinevi)
        var kitap_yazar_kdk : TextView = itemView.findViewById(R.id.kitap_yazar_kdk)
        var kitap_teslim_alma_tarih_kdk : TextView = itemView.findViewById(R.id.kitap_teslim_alma_tarih_kdk)


    }

}
