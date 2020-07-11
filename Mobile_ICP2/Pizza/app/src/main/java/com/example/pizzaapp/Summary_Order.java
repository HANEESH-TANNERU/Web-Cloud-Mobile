package com.example.pizzaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
// Create Order Summary
public class Summary_Order extends AppCompatActivity {

    TextView textsummary;
    Button OrderB;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_order);
        textsummary = findViewById(R.id.summaryText);
        OrderB = findViewById(R.id.orderPizza);
        textsummary.setText(Html.fromHtml("<u>Your Order Summary</u><br/><br/>"));
        if (getIntent() != null) {
            textsummary.append(getIntent().getStringExtra("orderSummary"));
        } else {
            textsummary.setText("You have no orders !!");
        }
        textsummary.append(Html.fromHtml("<br/>"));
        textsummary.setVisibility(View.VISIBLE);

        OrderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reDirectToOrderingPage();
            }
        });
    }
        public void reDirectToOrderingPage() {
            Intent intent = new Intent(Summary_Order.this, Order_Pizza.class);
            startActivity(intent);
        }

}