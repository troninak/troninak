package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RegistrationActivity extends AppCompatActivity {
    EditText loginEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        loginEditText = findViewById(R.id.editTextLogin);
        emailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        repeatPasswordEditText = findViewById(R.id.editTextPasswordRepeat);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (checkFields()) {
                writeToFile(String.valueOf(loginEditText.getText()), this, "login");
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    protected boolean checkFields() {
        if (loginEditText.length() == 0 && emailEditText.length() == 0) {
            loginEditText.setError("Введите логин");
            emailEditText.setError("Введите email");
            return false;
        }
        else if (loginEditText.length() == 0) {
            loginEditText.setError("Введите логин");
            return false;
        }
        else if (emailEditText.length() == 0) {
            emailEditText.setError("Введите email");
            return false;
        }
        if (passwordEditText.length() == 0) {
            passwordEditText.setError("Введите пароль");
            return false;
        }
        if (repeatPasswordEditText.length() == 0) {
            repeatPasswordEditText.setError("Повторите пароль");
            return false;
        }
        if (passwordEditText.length() < 8) {
            passwordEditText.setError("Введите пароль длинее 8 символов");
            return false;
        }
        if (!String.valueOf(repeatPasswordEditText.getText()).equals(String.valueOf(passwordEditText.getText()))) {
            passwordEditText.setError("Пароли не совпадают");
            repeatPasswordEditText.setError("Пароли не совпадают");
            return false;
        }
        return false;
    }
    private void writeToFile(String data, Context context, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName + ".txt", MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed:" + e);
        }
    }
}