package com.example.kaiizzer.ppe3;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.kaiizzer.ppe3.myrequest.MyRequest;

import java.util.Map;

import static com.example.kaiizzer.ppe3.myrequest.MyRequest.*;

public class Connexion extends AppCompatActivity {

    private Button btn_send;
    private TextInputLayout til_pseudo, til_password;
    private RequestQueue queue;
    private MyRequest request;
    private ProgressBar pb_loader;
    private Handler handler;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        btn_send = (Button) findViewById(R.id.connecter);
        til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo);
        til_password = (TextInputLayout) findViewById(R.id.til_password);
        pb_loader = (ProgressBar) findViewById(R.id.pb_loader);
        handler = new Handler();
        sessionManager = new SessionManager(this);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pseudo = til_pseudo.getEditText().getText().toString().trim();
                final String password = til_password.getEditText().getText().toString().trim();
                pb_loader.setVisibility(View.VISIBLE);
                if (pseudo.length() > 0 && password.length() > 0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.connection(pseudo, password, new LoginCallback(){
                                @Override
                                public void onSuccess(String id, String pseudo) {
                                    pb_loader.setVisibility(View.GONE);
                                    sessionManager.insertUser(id, pseudo);
                                    Intent intent = new Intent(getApplicationContext(), PremiereActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onSuccess(String id, String pseudo, String nom, String prenom, String email) {

                                }

                                @Override
                                public void onSuccess(String message) {

                                }

                                @Override
                                public void inputErrors(Map<String, String> errors) {

                                }

                                @Override
                                public void onError(String message) {
                                    pb_loader.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    },1000);
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
