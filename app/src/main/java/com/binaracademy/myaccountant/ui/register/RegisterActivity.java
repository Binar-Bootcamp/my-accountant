package com.binaracademy.myaccountant.ui.register;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.binaracademy.myaccountant.R;

public class RegisterActivity extends AppCompatActivity {

    EditText input_nama;
    Button button_continue;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "register";
    private static final String KEY_NAME = "nama";
    private static final String KEY_SAVING = "saving";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        input_nama = findViewById(R.id.input_nama);
        button_continue = findViewById(R.id.btn_continue);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, input_nama.getText().toString());
                editor.apply();

//                val intent = Intent(this, IncomeActivity::class);
//                Intent intent = new Intent(this, IncomeActivity.class);
//                startActivity(intent);

//                Toast.makeText(DropdownTipeSaving.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
