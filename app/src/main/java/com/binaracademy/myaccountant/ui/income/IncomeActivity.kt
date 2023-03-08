package com.binaracademy.myaccountant.ui.income

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.presenter.InitialContract
import com.binaracademy.myaccountant.data.presenter.InitialPresenter
import com.binaracademy.myaccountant.databinding.ActivityIncomeBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.util.helpers.intentTo
import kotlinx.coroutines.launch

class IncomeActivity : AppCompatActivity(), InitialContract.View {

    private val presenter = InitialPresenter()

    private val binding: ActivityIncomeBinding by lazy {
        ActivityIncomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.setView(this)

        val shared: SharedPreferences = getSharedPreferences("prefData", MODE_PRIVATE)
        val userType = when (shared.getString("saving_type", "")) {
            "Si Life Balance" -> UserType.LIFE_BALANCE
            "Si Paling Hemat" -> UserType.PALING_HEMAT
            "Si Tukang Shopping" -> UserType.TUKANG_SHOPPING
            else -> UserType.LIFE_BALANCE
        }

        binding.btnSave.setOnClickListener {
            val income = binding.inputIncome.text

            lifecycleScope.launch {
                presenter.saveInitialIncome(userType, income.toString().toLong())
            }
        }
    }

    override fun onSaveInitialError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInitialSuccess(amount: Long) {
        val shared: SharedPreferences = getSharedPreferences("prefData", MODE_PRIVATE)
        val editor = shared.edit()
        editor.apply {
            putString("gaji", amount.toString())
            apply()
        }
        intentTo(MainActivity::class.java)
    }
}