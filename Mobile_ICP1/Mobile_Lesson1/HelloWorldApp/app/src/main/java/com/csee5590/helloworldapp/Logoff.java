package com.csee5590.helloworldapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
//Java class consisting of a logout button which redirects to login page
public class Logoff extends AppCompatActivity{
    Button Logoff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logoff);
        Logoff = findViewById(R.id.button2);
        Logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logoff.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
