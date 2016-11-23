package com.calebhillhouse.financewatcher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by caleb on 9/16/2016.
 */
public class Allowances extends AppCompatActivity {

    int billsValue;
    int paymentsValue;
    int subsValue;
    int yearly_payValue;
    double yearlyAllowance;
    double monthlyAllowance;
    double dailyAllowance;

    TextView monthly;
    TextView daily;
    TextView yearly;

    EditText dailyCustom;
    EditText monthlyCustom;
    EditText yearlyCustom;

    String day;
    String month;
    String year;

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allowances);

        //textviews for estimated allowances
        daily = (TextView)findViewById(R.id.estimated_daily_allowance);
        monthly = (TextView)findViewById(R.id.estimated_monthly_allowance);
        yearly = (TextView)findViewById(R.id.estimated_yearly_allowance);

        //edittexts for custom allowances
        dailyCustom = (EditText)findViewById(R.id.daily_allowance);
        monthlyCustom = (EditText)findViewById(R.id.monthly_allowance);
        yearlyCustom = (EditText)findViewById(R.id.yearly_allowance);

        loadData();

        yearlyAllowance = yearly_payValue - subsValue - paymentsValue - billsValue;
        monthlyAllowance = yearlyAllowance/12;
        dailyAllowance = yearlyAllowance/365;

        daily.setText(Double.toString(dailyAllowance));
        monthly.setText(Double.toString(monthlyAllowance));
        yearly.setText(Double.toString(yearlyAllowance));
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
