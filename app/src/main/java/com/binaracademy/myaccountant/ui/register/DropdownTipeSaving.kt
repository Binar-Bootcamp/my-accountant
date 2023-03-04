package com.binaracademy.myaccountant.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.FragmentRegisterBinding

class DropdownTipeSaving : Fragment(){

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val saving = resources.getStringArray(R.array.type_saving)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, saving)
        binding.savingList.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false )

        val saving = resources.getStringArray(R.array.type_saving)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, saving)
        binding.savingList.setAdapter(arrayAdapter)

            return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}