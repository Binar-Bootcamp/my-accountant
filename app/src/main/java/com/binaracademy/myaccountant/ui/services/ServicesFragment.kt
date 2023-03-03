package com.binaracademy.myaccountant.ui.services

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.binaracademy.myaccountant.databinding.FragmentServicesBinding
import com.binaracademy.myaccountant.ui.counter.CounterActivity


@Suppress("UNREACHABLE_CODE")
class ServicesFragment : Fragment() {
    private lateinit var binding: FragmentServicesBinding
    private lateinit var button: CardView


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        binding.cvCounter.setOnClickListener {
            activity?.let {
                val intent = Intent(it, CounterActivity::class.java)
                it.startActivity(intent)
            }


        }


    }


}








