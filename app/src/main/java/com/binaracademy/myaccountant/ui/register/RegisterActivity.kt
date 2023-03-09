package com.binaracademy.myaccountant.ui.register

import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.FragmentRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Si Life Balance", "Si Paling Hemat", "Si Tukang Shopping")
        val adapter = ArrayAdapter(this, R.layout.dropdown_items, items)
        binding.savingList.setAdapter(adapter)

        val shared = getSharedPreferences("prefData", MODE_PRIVATE)
        val editor = shared.edit()

        binding.apply {
            btnContinue.setOnClickListener {
                val name = inputNama.text.toString()
                val saving = savingList.text.toString()

                editor.apply {
                    putString("username", name)
                    putString("saving_type", saving)
                    apply()
                }
            }

            btnInfo.setOnClickListener {
                customDialog()
            }
        }

    }

    private fun customDialog(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.info_dialog, null)

        with(builder){
            setView(dialogLayout)
        }
    }
}