package com.example.projectuts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment

class KonversiMataUangFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText

    var currencies = arrayOf("Indian Rupees", "US Dollar", "Japanese Yen", "Russian Ruble")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_konversi_mata_uang, container, false)

        spinner1 = view.findViewById(R.id.spinner1)
        spinner2 = view.findViewById(R.id.spinner2)

        ed1 = view.findViewById(R.id.ed1)
        ed2 = view.findViewById(R.id.ed2)

        spinner1.onItemSelectedListener = this
        spinner2.onItemSelectedListener = this

        val ad: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)
        val ad2: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = ad
        spinner2.adapter = ad2

        ed1.doOnTextChanged { _, _, _, _ ->
            if (ed1.isFocused) {
                val amt = if (ed1.text.isEmpty()) 0.0 else ed1.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner1.selectedItem.toString(),
                    spinner2.selectedItem.toString()
                )
                ed2.setText(convertedCurrency.toString())
            }
        }

        ed2.doOnTextChanged { _, _, _, _ ->
            if (ed2.isFocused) {
                val amt = if (ed2.text.isEmpty()) 0.0 else ed2.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner2.selectedItem.toString(),
                    spinner1.selectedItem.toString()
                )
                ed1.setText(convertedCurrency.toString())
            }
        }

        return view
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id) {
            R.id.spinner1 -> {
                val amt = if (ed1.text.isEmpty()) 0.0 else ed1.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner1.selectedItem.toString(),
                    spinner2.selectedItem.toString()
                )
                ed2.setText(convertedCurrency.toString())
            }
            R.id.spinner2 -> {
                val amt = if (ed2.text.isEmpty()) 0.0 else ed2.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner2.selectedItem.toString(),
                    spinner1.selectedItem.toString()
                )
                ed1.setText(convertedCurrency.toString())
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Implement this if needed
    }

    private fun convertCurrency(amt: Double, firstCurrency: String, secondCurrency: String): Double {
        val indianRupee = convertOtherToIndianCurrency(amt, firstCurrency)
        return convertIndianToOtherCurrency(indianRupee, secondCurrency)
    }

    private fun convertIndianToOtherCurrency(indianRupee: Double, secondCurrency: String): Double {
        return indianRupee * when (secondCurrency) {
            "Indian Rupees" -> 1.0
            "US Dollar" -> 0.012
            "Japanese Yen" -> 1.60
            "Russian Ruble" -> 0.93
            else -> 0.0
        }
    }

    private fun convertOtherToIndianCurrency(amt: Double, firstCurrency: String): Double {
        return amt * when (firstCurrency) {
            "Indian Rupees" -> 1.0
            "US Dollar" -> 82.63
            "Japanese Yen" -> 0.62
            "Russian Ruble" -> 1.07
            else -> 0.0
        }
    }
}
