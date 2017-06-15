package com.example.moryta.persistencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.SharedPreferences.*;

public class LoginActivity extends AppCompatActivity {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String KEEP_CONNECTED = "keep_connected";

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.cbKeepConnected)
    CheckBox cbKeepConnected;

    SharedPreferences sp;

    private String username;
    private String password;
    boolean keepConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }

    @OnClick(R.id.btLogin)
    public void OnClickLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if ("admin".equals(username) && "123".equals(password)) {
            keepConnected = cbKeepConnected.isChecked();

            if (keepConnected) {
                storeLoginInfo(username, password, keepConnected);
            } else {
                clearLogInfo();
            }
        } else {
            Toast.makeText(this, "Usuário/Senha inválido", Toast.LENGTH_SHORT)
                .show();
        }
    }

    private void init() {
        sp = getPreferences(MODE_PRIVATE);
        username = sp.getString(USERNAME, null);
        password = sp.getString(PASSWORD, null);
        keepConnected = sp.getBoolean(KEEP_CONNECTED, false);

        etUsername.setText(username);
        etPassword.setText(password);
        cbKeepConnected.setChecked(keepConnected);
    }

    private void storeLoginInfo(String username, String password, Boolean keepConnected) {
        boolean storedKeepConnected = sp.getBoolean(KEEP_CONNECTED, false);

        Editor editor = sp.edit();

        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);

        if (keepConnected != storedKeepConnected) {
            editor.putBoolean(KEEP_CONNECTED, keepConnected);
        }

        editor.apply();
    }

    private void clearLogInfo() {
        Editor editor = sp.edit();
        editor.putString(USERNAME, "");
        editor.putString(PASSWORD, "");
        editor.putBoolean(KEEP_CONNECTED, false);
        editor.apply();
    }
}
