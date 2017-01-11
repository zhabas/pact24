package com.vibenetwork.vibenetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button signInButton=(Button) findViewById(R.id.signInButton);
        Button signUpButton=(Button) findViewById(R.id.signUpButton);
        Button adminButton=(Button) findViewById(R.id.adminButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent signInIntent=new Intent(Home.this,SignIn.class);
                startActivity(signInIntent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent signUpIntent=new Intent(Home.this,SignUp.class);
                startActivity(signUpIntent);
            }
        });


    }
}
