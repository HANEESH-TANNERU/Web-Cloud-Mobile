package com.example.pizzaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

// Home Activity to show Two Order Buttons.
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // On Click of Order Pizza
    public void orderPizza(View view) {
        Intent intent = new Intent(MainActivity.this, Order_Pizza.class);
        startActivity(intent);
    }

}