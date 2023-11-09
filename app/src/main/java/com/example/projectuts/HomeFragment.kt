package com.example.projectuts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.projectuts.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // Deklarasi variabel lainnya

    private var calculateImageView: ImageView? = null
    private  var BMIKalkulatorImageView: ImageView? = null
    private  var SuhuImageView: ImageView? = null
    private  var ConvertImageView: ImageView? = null
    private  var ProfileImageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inisialisasi view, binding, dan lainnya

        // Menambahkan OnClickListener ke calculateImageView
        calculateImageView = view.findViewById(R.id.calculate)
        calculateImageView?.setOnClickListener {
            val fragmentKalkulator = KalkulatorFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentKalkulator)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        BMIKalkulatorImageView = view.findViewById(R.id.bmi)
        BMIKalkulatorImageView?.setOnClickListener {
            val fragmentBMI= BMIKalkulatorFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentBMI)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        SuhuImageView = view.findViewById(R.id.suhu)
        SuhuImageView?.setOnClickListener {
            val fragmentSuhu= SuhuFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentSuhu)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        ConvertImageView = view.findViewById(R.id.konversi)
        ConvertImageView?.setOnClickListener {
            val fragmentConvert= KonversiMataUangFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentConvert)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        ProfileImageView = view.findViewById(R.id.profile)
        ProfileImageView?.setOnClickListener {
            val fragmentProfile = ProfileFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentProfile)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        return view
    }
}
