package com.bogazliyan.ktphaneuygulamas.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bogazliyan.ktphaneuygulamas.KitapIDTakip
import com.bogazliyan.ktphaneuygulamas.KutuphaneDisindaKitaplar
import com.bogazliyan.ktphaneuygulamas.MainActivity2
import com.bogazliyan.ktphaneuygulamas.TumKitaplar
import com.bogazliyan.ktphaneuygulamas.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null


    private lateinit var buttonIDTakip: Button
    private lateinit var buttonVerilenTakip: Button
    private lateinit var buttonTumKitaplar: Button
    private lateinit var buttonGecmisIslemler: Button


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        buttonIDTakip = binding.buttonIdTakip
        buttonIDTakip.setOnClickListener {
            Toast.makeText(requireContext(), "takip button clicked! ", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), KitapIDTakip::class.java)
            startActivity(intent)
        }

        buttonVerilenTakip = binding.buttonVerilenKitaplar
        buttonVerilenTakip.setOnClickListener {
            Toast.makeText(requireContext(), "verilen button clicked! ", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), KutuphaneDisindaKitaplar::class.java)
            startActivity(intent)
        }

        buttonTumKitaplar = binding.buttonTumKitaplar
        buttonTumKitaplar.setOnClickListener {
            Toast.makeText(requireContext(), "tüm button clicked! ", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), TumKitaplar::class.java)
            startActivity(intent)
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}