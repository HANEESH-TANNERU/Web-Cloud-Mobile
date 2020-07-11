package com.example.pizzaapp;

import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.BooleanUtils;


public class Order_Pizza extends AppCompatActivity{

    private static final Integer basic_rate = 20;
    private static final Integer cheese_rate = 1;
    private static final Integer pork_rate = 3;
    private static final Integer onion_rate = 3;
    private static final Integer sauce_rate = 1;
    float totalPrice;
    Integer quantity = 1;
    String orderSummary;

    // From the Layout
    EditText username1;
    TextView quantityTextView;
    CheckBox cheese1, pork1, onion1, sauce1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_order);

        // Fetching By Id's
        quantityTextView = findViewById(R.id.quantity_text_view);
        username1 = findViewById(R.id.name);
        cheese1 = findViewById(R.id.cheese);
        pork1 = findViewById(R.id.pork);
        onion1 = findViewById(R.id.onion);
        sauce1 = findViewById(R.id.sauce);
    }

    // Check for UserName error
    private boolean isUserEmpty(){
        // Checking If username is present or not
        String userName = username1.getText().toString();
        if(userName == null || userName.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = "User Name is Required";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }

    // Fetch Details
    private String fetchDetails() {
        boolean cheese2 = cheese1.isChecked();
        boolean pork2 = pork1.isChecked();
        boolean  onion2= onion1.isChecked();
        boolean  sauce2 = sauce1.isChecked();

        // Get the Total Price.
        totalPrice = calculatePrice(cheese2, pork2, onion2, sauce2, quantity);
        // Creating Order Summary
        return fetchOrderSummary(username1.getText().toString(), cheese2, pork2, onion2, sauce2, totalPrice);
    }

    // On Click of Order Summary
    public void orderSummary(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent intent = new Intent(Order_Pizza.this, Summary_Order.class);
            intent.putExtra("orderSummary", orderSummary);
            startActivity(intent);
        }
    }

    // OnClick of Order
    public void orderPizzaMain(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            // The intent does not have a URI, so declare the "text/plain" MIME type
            emailIntent.setType("plain/text");
            // Recipients
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"PizzaOrder@gmail.com"});
            // Adding Subject
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
            // Adding the Order Summary Text
            emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
            // Redirecting to Email Intent
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }

    // On Click of Order Pizza


    private String fetchOrderSummary(String userName, boolean cheese1, boolean pork1,
                                     boolean onion1, boolean sauce1, float total1) {
        return getString(R.string.summary_order_name,userName) +"\n"+
                getString(R.string.summary_order_cheese, BooleanUtils.toStringYesNo(cheese1))+"\n"+
                getString(R.string.summary_order_pork,BooleanUtils.toStringYesNo(pork1)) +"\n"+
                getString(R.string.summary_order_onion,BooleanUtils.toStringYesNo(onion1)) +"\n"+
                getString(R.string.summary_order_sauce,BooleanUtils.toStringYesNo(sauce1)) +"\n"+
                getString(R.string.summaryMessage,quantity)+"\n"+
                getString(R.string.summary_order_total_price,totalPrice) +"\n"+
                getString(R.string.thank_you);
    }

    // Method to Calculate total Price
    private float calculatePrice(boolean cheese, boolean pork, boolean onion, boolean sauce, Integer quantity) {
        int basePrice = basic_rate;
        if (cheese) {
            basePrice += cheese_rate;
        }
        if (pork) {
            basePrice += pork_rate;
        }
        if (onion){
            basePrice += onion_rate;
        }
        if(sauce){
            basePrice += sauce_rate;
        }
        return quantity * basic_rate;
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     */
    public void more(View view) {
        if (quantity < 10) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select less than 10 Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 10 Pizzas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     */
    public void less(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("CoffeOrder", "Please select atleast one Pizza");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select atleast 1 Pizza";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    // Displays the Quantity
    private void display(int number) {
        quantityTextView.setText("" + number);
    }


    // On Click of Call
    public void callStore(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:205566441"));
        // Redirecting to Email Intent
        startActivity(Intent.createChooser(callIntent, ""));
    }

}