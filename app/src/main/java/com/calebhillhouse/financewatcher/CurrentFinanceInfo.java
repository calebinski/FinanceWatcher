package com.calebhillhouse.financewatcher;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by caleb on 9/16/2016.
 */
public class CurrentFinanceInfo extends AppCompatActivity {
    Button allowances;
    Button newPurchase;

    TextView yearlyLeftText;
    TextView monthlyLeftText;
    TextView dailyLeftText;

    Double dailyAllowance, monthlyAllowance, yearlyAllowance;
    Double dailyAllowanceLeft, monthlyAllowanceLeft, yearlyAllowanceLeft;
    Double amount;

    Long temp;

    RadioButton grocery;
    RadioButton entertainment;
    RadioButton electronics;
    RadioButton clothing;
    RadioButton other;

    Button purchaseType;
    Button purchaseAmount;

    EditText purchaseAmountInput;

    ArrayList<Purchase> listOfPurchases = new ArrayList<Purchase>();

    Purchase purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_finance_info);

        //load in allowances
        //loadData();

        //buttons
        allowances = (Button)findViewById(R.id.allowances_button);
        newPurchase = (Button)findViewById(R.id.new_purchase);

        //text views
        yearlyLeftText = (TextView)findViewById(R.id.yearly_allowance_left);
        monthlyLeftText = (TextView)findViewById(R.id.monthly_allowance_left);
        dailyLeftText = (TextView)findViewById(R.id.daily_allowance_left);

        allowances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentFinanceInfo.this, Allowances.class);
                startActivity(intent);
            }
        });

        newPurchase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                /*
                final Dialog dialog = new Dialog(CurrentFinanceInfo.this);
                dialog.setContentView(R.layout.purchase_type);
                dialog.setCancelable(true);
                dialog.setTitle("Select Purchase Type");

                grocery = (RadioButton)dialog.findViewById(R.id.groceries);
                entertainment = (RadioButton)dialog.findViewById(R.id.entertainment);
                electronics = (RadioButton)dialog.findViewById(R.id.electronics);
                clothing = (RadioButton)dialog.findViewById(R.id.clothing);
                other = (RadioButton)dialog.findViewById(R.id.other);

                purchaseType = (Button)dialog.findViewById(R.id.purchase_type);

                dialog.show();*/

                AlertDialog.Builder alert = new AlertDialog.Builder(CurrentFinanceInfo.this);
                alert.setTitle("Select Purchase Type");
                alert.setMessage("Please select the type of purchase.");

                grocery = new RadioButton(CurrentFinanceInfo.this);
                entertainment = new RadioButton(CurrentFinanceInfo.this);
                electronics = new RadioButton(CurrentFinanceInfo.this);
                clothing = new RadioButton(CurrentFinanceInfo.this);
                other = new RadioButton(CurrentFinanceInfo.this);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                grocery.setLayoutParams(lp);
                alert.setView(grocery);
                entertainment.setLayoutParams(lp);
                alert.setView(entertainment);
                electronics.setLayoutParams(lp);
                alert.setView(electronics);
                clothing.setLayoutParams(lp);
                alert.setView(clothing);
                other.setLayoutParams(lp);
                alert.setView(other);

                RadioGroup radioGroup = new RadioGroup();

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CurrentFinanceInfo.this);
                        alertDialog.setTitle("Purchase Amount");
                        alertDialog.setMessage("Please enter the purchase amount.");

                        EditText input = new EditText(CurrentFinanceInfo.this);
                        input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setLayoutParams(lp);
                        alertDialog.setView(input);

                        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        alertDialog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialog.show();
                    }
                });
                alert.show();


                /*purchaseType.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CurrentFinanceInfo.this);
                        alertDialog.setTitle("Purchase Amount");
                        alertDialog.setMessage("Please enter the purchase amount.");

                        EditText input = new EditText(CurrentFinanceInfo.this);
                        input.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setLayoutParams(lp);
                        alertDialog.setView(input);

                        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        alertDialog.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialog.show();
                    }
                });*/
            }
        });
    }
/*
    private void loadData(){
        SharedPreferences sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        temp = sp.getLong("daily_allowance", temp);
        dailyAllowance = Double.longBitsToDouble(temp);

        temp = sp.getLong("monthly_allowance", temp);
        monthlyAllowance = Double.longBitsToDouble(temp);

        temp = sp.getLong("yearly_allowance", temp);
        yearlyAllowance = Double.longBitsToDouble(temp);
    }*/
}
