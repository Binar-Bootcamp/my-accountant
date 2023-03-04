package com.binaracademy.myaccountant.ui.income;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.binaracademy.myaccountant.R;

public class IncomeActivity extends AppCompatActivity {
    EditText input_gaji;
    Button button_save;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "income";
    private static final String KEY_INCOME = "gaji";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_income);

        input_gaji = findViewById(R.id.input_income);
        button_save = findViewById(R.id.btn_save);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =  sharedPreferences.edit();
                editor.putString(KEY_INCOME, input_gaji.getText().toString());
                editor.apply();

//                Intent intent = new Intent(IncomeFragment.this.ProfileFragment.class);
//                startActivity(intent);

                Toast.makeText(IncomeActivity.this, "Gaji Sudah Diinputkan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
