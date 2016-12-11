package com.calebhillhouse.financewatcher;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewFinances extends AppCompatActivity {

    EditText bills;
    EditText payments;
    EditText subs;
    EditText yearly_pay;

    Button estimateFinances;
    Button catExplain;

    int billsValue;
    int paymentsValue;
    int subsValue;
    int yearly_payValue;

    String a, b, c, d;
    private String filename = "info.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_finances);

        estimateFinances = (Button)findViewById(R.id.estimate);
        catExplain = (Button)findViewById(R.id.cat_explain);

        bills = (EditText)findViewById(R.id.bills);
        payments = (EditText)findViewById(R.id.payments);
        subs = (EditText)findViewById(R.id.subs);
        yearly_pay = (EditText)findViewById(R.id.yearly_pay);

        catExplain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewFinances.this);
                alertDialog.setTitle("Category Explanation");
                alertDialog.setMessage("Bills: Any and all bills you must pay at the end of the year.\nPayments: The yearly" +
                        " value of the payments you make towards your house, car, etc.\nSubscriptions: Money you pay for subscriptions" +
                        " to various services such as Netflix, magazines, games, etc.\nYearly Pay: The amount of money you make yearly.");
                alertDialog.show();
            }
        });

        estimateFinances.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                a = bills.getText().toString().trim();
                b = payments.getText().toString().trim();
                c = subs.getText().toString().trim();
                d = yearly_pay.getText().toString().trim();

                if (a.equals("") || b.equals("") || c.equals("")){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewFinances.this);
                    alertDialog.setTitle("WARNING");
                    alertDialog.setMessage("All fields must have a valid number.");
                    alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent2 = new Intent(NewFinances.this, NewFinances.class);
                            startActivity(intent2);
                        }
                    });
                    alertDialog.show();
                }
                else if(d.equals("")){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewFinances.this);
                    alertDialog.setTitle("WARNING");
                    alertDialog.setMessage("Cannot create allowance without a yearly pay.");
                    alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent2 = new Intent(NewFinances.this, NewFinances.class);
                            startActivity(intent2);
                        }
                    });
                }
                else{
                    billsValue = Integer.valueOf(bills.getText().toString());
                    paymentsValue =Integer.valueOf(payments.getText().toString());
                    subsValue = Integer.valueOf(subs.getText().toString());
                    yearly_payValue = Integer.valueOf(yearly_pay.getText().toString());

                    Intent intent = new Intent(NewFinances.this, Allowances.class);

                    saveData();

                    startActivity(intent);
                }
            }
        });
    }

    private void saveData(){
        SharedPreferences sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("bills", billsValue);
        editor.putInt("payments", paymentsValue);
        editor.putInt("subs", subsValue);
        editor.putInt("yearly_pay", yearly_payValue);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        billsValue = sp.getInt("bills",billsValue);
        paymentsValue = sp.getInt("payments", paymentsValue);
        subsValue = sp.getInt("subs", subsValue);
        yearly_payValue = sp.getInt("yearly_pay", yearly_payValue);
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }
}
