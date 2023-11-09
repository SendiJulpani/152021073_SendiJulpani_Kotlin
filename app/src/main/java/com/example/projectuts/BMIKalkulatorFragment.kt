package com.example.projectuts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectuts.databinding.FragmentBmiKalkulatorBinding

class BMIKalkulatorFragment : Fragment() {

    private lateinit var binding: FragmentBmiKalkulatorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBmiKalkulatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        binding.weightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }

        binding.heightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100

        val weight = binding.weightPicker.value

        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

        binding.hasilTV.text = String.format("Your BMI is: %.2f", bmi)
        binding.healtyTV.text = String.format("Considered: %s", healtyMessage(bmi))
    }

    private fun healtyMessage(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25.0 -> "Healthy"
            bmi < 30.0 -> "Overweight"
            else -> "Obese"
        }
    }
}
