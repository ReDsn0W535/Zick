package com.example.volod.zick.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.volod.zick.Presenter.LoginPresenterImpl;
import com.example.volod.zick.Presenter.Presenters;
import com.example.volod.zick.R;

public class LoginActivity extends AppCompatActivity implements LoginInterface {
    private Presenters.LoginPresenter presenter;
    private Button register;
    private Button sign_up;
    private EditText login;
    private EditText mail;
    private EditText pass;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getPreferences(MODE_PRIVATE);
        presenter = new LoginPresenterImpl(this);
        startFaceActivityIfUserDataValid();
        if (preferences.contains("username")) Log.d("LOGIN VIEW TAG", "username contains:  " + preferences.getString("username", "empty value"));
        if (preferences.contains("password")) Log.d("LOGIN VIEW TAG", "password contains:  "+ preferences.getString("password", "empty value"));
        if (preferences.contains("mail")) Log.d("LOGIN VIEW TAG", "mail contains:  "+ preferences.getString("mail", "empty value"));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        register = (Button) findViewById(R.id.register);
        sign_up = (Button) findViewById(R.id.sign_button);
        login = (EditText) findViewById(R.id.Login);
        pass = (EditText) findViewById(R.id.Pass);
        mail = (EditText) findViewById(R.id.mail);
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().contains("@")){
                    mail.setError("this mail not valid");
                    setButtonsNonClickable();
                } else setButtonsClickable();
            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 6){
                    pass.setError("password must be at least 6 symbols");
                    setButtonsNonClickable();
                } else setButtonsClickable();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveUserData(preferences, "username", login.getText().toString() );
                presenter.saveUserData(preferences, "password", pass.getText().toString());
                presenter.saveUserData(preferences, "mail", mail.getText().toString());
                presenter.register(login.getText().toString(), pass.getText().toString(), mail.getText().toString());
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sign_up(login.getText().toString(), pass.getText().toString(), mail.getText().toString());
            }
        });
    }

    @Override
    public void getRegistrationErrorWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Такое имя или почта уже используется!")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void getDisconnectErrorWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Важное сообщение!")
                .setMessage("Возникла ошибка при попытке соединения с сервером!")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void startFaceActivity() {
        Intent intent = new Intent(this, FaceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    void setButtonsNonClickable(){
        register.setClickable(false);
        sign_up.setClickable(false);
    }

    void setButtonsClickable(){
        register.setClickable(true);
        sign_up.setClickable(true);
    }




    void startFaceActivityIfUserDataValid(){
        if (preferences.contains("mail") && preferences.contains("password") && preferences.contains("username")){
            presenter.sign_up(preferences.getString("username", ""), preferences.getString("password", ""), preferences.getString("mail", ""));
            Log.d("LOGIN VIEW TAG", "starting face activity");
        }
    }
}
