package com.example.kaiizzer.ppe3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PremiereActivity<btn_> extends AppCompatActivity {

    private SessionManager sessionManager;
    private TextView textView;
    private Button btn_logout;
    private Button btn_cmd;
    private Button btn_infos;
    private Button btn_paiement;
    private Button btn_reduction;
    private Button btn_apropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiere);

        textView = (TextView) findViewById(R.id.tv_pseudo);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_cmd = (Button) findViewById(R.id.btn_cmd);
        btn_cmd = (Button) findViewById(R.id.btn_infos);
        btn_cmd = (Button) findViewById(R.id.btn_paiement);
        btn_cmd = (Button) findViewById(R.id.btn_reduction);
        btn_cmd = (Button) findViewById(R.id.btn_apropos);
        sessionManager = new SessionManager(this);
        if(sessionManager.isLogged()){
            String pseudo = sessionManager.getPseudo();
            String id = sessionManager.getId();
            textView.setText(pseudo);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

        btn_cmd = (Button) findViewById(R.id.btn_cmd);
        btn_cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openCommandes();
            }
        });

        btn_infos = (Button) findViewById(R.id.btn_infos);
        btn_infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openInformations();
            }
        });

        btn_paiement = (Button) findViewById(R.id.btn_paiement);
        btn_paiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openPaiement();
            }
        });

        btn_reduction = (Button) findViewById(R.id.btn_reduction);
        btn_reduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openReduction();
            }
        });

        btn_apropos = (Button) findViewById(R.id.btn_apropos);
        btn_apropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                openApropos();
            }
        });
    }

    public void openCommandes() {
        Intent intent = new Intent(this, Commandes.class);
        startActivity(intent);
    }
    public void openInformations() {
        Intent intent = new Intent(this, Informations.class);
        startActivity(intent);
    }
    public void openPaiement() {
        Intent intent = new Intent(this, Paiement.class);
        startActivity(intent);
    }
    public void openReduction() {
        Intent intent = new Intent(this, Reduction.class);
        startActivity(intent);
    }
    public void openApropos() {
        Intent intent = new Intent(this, Apropos.class);
        startActivity(intent);
    }
}


