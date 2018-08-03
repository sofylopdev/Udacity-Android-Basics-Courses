package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    private int quantity;
    boolean hasWhippedCream;
    boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;
    }

    public void submitOrder(View v) {
        if (quantity > 0) {

            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
            hasWhippedCream = whippedCreamCheckBox.isChecked();

            CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
            hasChocolate = chocolateCheckBox.isChecked();

            EditText name_field = (EditText) findViewById(R.id.name_field);
            String name = name_field.getText().toString();

            String orderMessage = createOrderSummary(hasWhippedCream, hasChocolate, name);

            Intent sendMail = new Intent(Intent.ACTION_SENDTO);
            sendMail.setData(Uri.parse("mailto:"));
            sendMail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_for) + name);
            sendMail.putExtra(Intent.EXTRA_TEXT, orderMessage);
            if (sendMail.resolveActivity(getPackageManager()) != null) {
                startActivity(sendMail);
            }
        }
    }

    private void displayQuantity(int nr) {
        TextView quantity_value = (TextView) findViewById(R.id.quantity_value);
        quantity_value.setText("" + nr);
    }

    public void increment(View v) {
        if (quantity < 100) {
            quantity++;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View v) {
        if (quantity > 0) {
            quantity--;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    private String calculatePrice() {

        int basePrice = 5;
        if (hasChocolate)
            basePrice += 2;
        if (hasWhippedCream)
            basePrice += 1;

        return NumberFormat.getCurrencyInstance().format(quantity * basePrice);
    }

    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate, String name) {

        String orderSummary = getString(R.string.order_summay_name, name);
        orderSummary += "\n" + getString(R.string.add_whipped_cream) + addWhippedCream;
        orderSummary += "\n" + getString(R.string.add_chocolate) + addChocolate;
        orderSummary += "\n" + getString(R.string.quantity_value) + quantity;
        orderSummary += "\n" + getString(R.string.total) + calculatePrice();
        orderSummary += "\n" + getString(R.string.thank_you);
        return orderSummary;
    }

}
