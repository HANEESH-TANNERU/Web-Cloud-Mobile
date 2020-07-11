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

    TextView summaryText;
    Button OrderButton;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summary);
        summaryText = findViewById(R.id.summaryText);
        OrderButton = findViewById(R.id.orderPizza);
        summaryText.setText(Html.fromHtml("<u>Your Order Summary</u><br/><br/>"));
        if (getIntent() != null) {
            summaryText.append(getIntent().getStringExtra("orderSummary"));
        } else {
            summaryText.setText("You have no orders !!");
        }
        summaryText.append(Html.fromHtml("<br/>"));
        summaryText.setVisibility(View.VISIBLE);

        OrderButton.setOnClickListener(new View.OnClickListener() {
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