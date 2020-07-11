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

    private static final Integer base_rate = 10;
    private static final Integer chicken_rate = 3;
    private static final Integer sasauge_rate = 2;
    private static final Integer pepperoni_rate = 2;
    private static final Integer op_rate = 1;
    float totalPrice;
    Integer quantity = 1;
    String orderSummary;

    // From the Layout
    EditText userNameText;
    TextView quantityTextView;
    CheckBox chickenChecked, baconChecked, pepperoniChecked, opChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_order);

        // Fetching By Id's
        quantityTextView = findViewById(R.id.quantity_text_view);
        userNameText = findViewById(R.id.name);
        chickenChecked = findViewById(R.id.chicken_checkbox);
        baconChecked = findViewById(R.id.sausage_checkbox);
        pepperoniChecked = findViewById(R.id.pepperoni_checkbox);
        opChecked = findViewById(R.id.veggies_checkbox);
    }

    // Check for UserName error
    private boolean isUserEmpty(){
        // Checking If username is present or not
        String userName = userNameText.getText().toString();
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
        boolean chickenFlag = chickenChecked.isChecked();
        boolean baconFlag = baconChecked.isChecked();
        boolean pepperoniFlag = pepperoniChecked.isChecked();
        boolean opFlag = opChecked.isChecked();

        // Get the Total Price.
        totalPrice = calculatePrice(chickenFlag, baconFlag, pepperoniFlag, opFlag, quantity);
        // Creating Order Summary
        return fetchOrderSummary(userNameText.getText().toString(), chickenFlag, baconFlag, pepperoniFlag, opFlag, totalPrice);
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


    private String fetchOrderSummary(String userName, boolean chickenFlag, boolean baconFlag,
                                     boolean pepperoniFlag, boolean opFlag, float totalPrice) {
        return getString(R.string.order_summary_name,userName) +"\n"+
                getString(R.string.order_summary_chicken, BooleanUtils.toStringYesNo(chickenFlag))+"\n"+
                getString(R.string.order_summary_bacon,BooleanUtils.toStringYesNo(baconFlag)) +"\n"+
                getString(R.string.order_summary_pepperoni,BooleanUtils.toStringYesNo(pepperoniFlag)) +"\n"+
                getString(R.string.order_summary_op,BooleanUtils.toStringYesNo(opFlag)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,totalPrice) +"\n"+
                getString(R.string.thank_you);
    }

    // Method to Calculate total Price
    private float calculatePrice(boolean chicken, boolean bacon, boolean pepperoni, boolean op, Integer quantity) {
        int basePrice = base_rate;
        if (chicken) {
            basePrice += chicken_rate;
        }
        if (bacon) {
            basePrice += sasauge_rate;
        }
        if (pepperoni){
            basePrice += pepperoni_rate;
        }
        if(op){
            basePrice += op_rate;
        }
        return quantity * basePrice;
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     */
    public void more(View view) {
        if (quantity < 20) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select less than 20 Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 20 Pizzas";
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
        callIntent.setData(Uri.parse("tel:2242799513"));
        // Redirecting to Email Intent
        startActivity(Intent.createChooser(callIntent, ""));
    }

}