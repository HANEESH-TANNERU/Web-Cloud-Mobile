package com.csee5590.helloworldapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.widget.Switch;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Initializing variables
    Switch Loggingin;
    EditText ControlUsername;
    EditText ControlPassword;
    TextView ControlStatus;
    String uname;
    String pwd;
    boolean flag = false;
    //Function to acquire login credentials and comparing with the given credentials
    //if matched then it redirects to welcome page else it displays incorrect password
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlUsername = findViewById(R.id.editTextTextPersonName);
        ControlPassword = findViewById(R.id.editTextNumberPassword);
        ControlStatus = findViewById(R.id.textView3);
        uname = ControlUsername.getText().toString();
        pwd = ControlPassword.getText().toString();
        Loggingin = findViewById(R.id.switch1);
        Loggingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ControlUsername.getText().toString().isEmpty() && !ControlPassword.getText().toString().isEmpty()) {
                    if (ControlUsername.getText().toString().equals("haneesh") && ControlPassword.getText().toString().equals("04021996"))
                    { flag = true; }
                }
                if (!flag)
                { ControlStatus.setText("The password and id doesnot match, Please try again"); }
                else
                { reDirectToWelcomePage(); }
            }
        });
    }
    public void reDirectToWelcomePage () {
        Intent intent = new Intent(MainActivity.this, Logoff.class);
        startActivity(intent);
    }
}