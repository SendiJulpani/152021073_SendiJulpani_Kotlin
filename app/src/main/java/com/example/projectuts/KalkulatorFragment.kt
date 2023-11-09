package com.example.projectuts

import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.projectuts.databinding.FragmentKalkulatorBinding

class KalkulatorFragment : Fragment() {

    private var _binding: FragmentKalkulatorBinding? = null
    private val binding get() = _binding!!

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKalkulatorBinding.inflate(inflater, container, false)
        val view = binding.root

        setupUI()

        return view
    }

    private fun setupUI() {
        // Inisialisasi tombol dan tambahkan onClickListener
        val numberButtons = arrayOf(
            binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9,
            binding.buttonDot
        )

        val operatorButtons = arrayOf(
            binding.buttonAdd, binding.buttonSubtract, binding.buttonMultiply, binding.buttonDivide
        )

        binding.buttonAC.setOnClickListener { allClearAction() }
        binding.buttonBackspace.setOnClickListener { backSpaceAction() }
        binding.buttonEquals.setOnClickListener { equalsAction() }

        // Atur onClickListener untuk tombol angka
        numberButtons.forEach { button ->
            button.setOnClickListener { numberAction(button) }
        }

        // Atur onClickListener untuk tombol operator
        operatorButtons.forEach { button ->
            button.setOnClickListener { operationAction(button) }
        }
    }

    fun numberAction(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (canAddDecimal)
                    binding.workingTV.append(view.text)
                canAddDecimal = false
            } else
                binding.workingTV.append(view.text)
            canAddOperation = true
        }
    }

    fun operationAction(view: View) {
        if (view is Button && canAddOperation) {
            binding.workingTV.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun allClearAction() {
        binding.workingTV.text = ""
        binding.resultTV.text = ""
    }

    fun backSpaceAction() {
        val length = binding.workingTV.length()
        if (length > 0)
            binding.workingTV.text = binding.workingTV.text.subSequence(0, length - 1)
    }

    fun equalsAction() {
        binding.resultTV.text = calculateResult()
    }

    private fun calculateResult(): String {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()) return ""
        val timesDivision = timeDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""
        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float
        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }
        return result
    }

    private fun timeDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('x') || list.contains('/')) {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size
        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when (operator) {
                    'x' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }
            if (i > restartIndex)
                newList.add(passedList[i])
        }
        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (character in binding.workingTV.text) {
            if (character.isDigit() || character == '.')
                currentDigit += character
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }
        if (currentDigit != "")
            list.add(currentDigit.toFloat())
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
