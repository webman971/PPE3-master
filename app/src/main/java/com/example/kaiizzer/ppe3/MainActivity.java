package com.example.kaiizzer.ppe3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button sign;
    private Button login;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        if(sessionManager.isLogged()){
            Intent intent = new Intent(this, PremiereActivity.class);
            startActivity(intent);
            finish();
        }

        Intent intent = getIntent();
        if(intent.hasExtra("REGISTER")){
            Toast.makeText(this, intent.getStringExtra("REGISTER"), Toast.LENGTH_SHORT).show();
        }

        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openInscription();
            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openConnexion();
            }
        });
    }

    public void openInscription() {
        Intent intent = new Intent(this, Inscription.class);
        startActivity(intent);
    }

    public void openConnexion() {
        Intent intent = new Intent(this, Connexion.class);
        startActivity(intent);
    }
}

