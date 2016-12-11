package com.calebhillhouse.financewatcher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    int temp;

    double yearlyAllowance;
    double monthlyAllowance;
    double dailyAllowance;
    double bills,payments,subs,yearDouble;

    TextView monthly;
    TextView daily;
    TextView yearly;

    EditText dailyCustom;
    EditText monthlyCustom;
    EditText yearlyCustom;

    Button setNewAllowances;

    String day;
    String month;
    String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allowances);

        //buttons
        setNewAllowances = (Button)findViewById(R.id.set_new_allowances);

        //textviews for estimated allowances
        daily = (TextView)findViewById(R.id.estimated_daily_allowance);
        monthly = (TextView)findViewById(R.id.estimated_monthly_allowance);
        yearly = (TextView)findViewById(R.id.estimated_yearly_allowance);

        //edittexts for custom allowances
        dailyCustom = (EditText)findViewById(R.id.daily_allowance);
        monthlyCustom = (EditText)findViewById(R.id.monthly_allowance);
        yearlyCustom = (EditText)findViewById(R.id.yearly_allowance);

        loadData();

        //convert ints to doubles
        bills = billsValue;
        payments = paymentsValue;
        subs = subsValue;
        yearDouble = yearly_payValue;

        //calculate allowances
        yearlyAllowance = (double)Math.round((yearDouble - subs - payments - bills)*100d)/100d;
        monthlyAllowance = (double)Math.round(((yearDouble - subs - payments - bills)/12)*100d)/100d;
        dailyAllowance = (double)Math.round(((yearDouble - subs - payments - bills)/365)*100d)/100d;

        //set text displays
        daily.setText(Double.toString(dailyAllowance));
        monthly.setText(Double.toString(monthlyAllowance));
        yearly.setText(Double.toString(yearlyAllowance));

        setNewAllowances.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Allowances.this, CurrentFinanceInfo.class);
                String a, b, c;
                a = dailyCustom.getText().toString().trim();
                b = monthlyCustom.getText().toString().trim();
                c = yearlyCustom.getText().toString().trim();

                if(a.equals("") && b.equals("") && c.equals("")){
                    saveData();
                    startActivity(intent);
                }
                else if (a.equals("")){
                    yearlyAllowance = Double.valueOf(yearlyCustom.getText().toString());
                    monthlyAllowance = Double.valueOf(monthlyCustom.getText().toString());
                    saveData();
                    startActivity(intent);
                }
                else if(b.equals("")){
                    dailyAllowance = Double.valueOf(dailyCustom.getText().toString());
                    yearlyAllowance = Double.valueOf(yearlyCustom.getText().toString());
                    saveData();
                    startActivity(intent);
                }
                else if (c.equals("")){
                    dailyAllowance = Double.valueOf(dailyCustom.getText().toString());
                    monthlyAllowance = Double.valueOf(monthlyCustom.getText().toString());
                    saveData();
                    startActivity(intent);
                }

            }
        });
    }

    private void saveData(){
        SharedPreferences sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("daily_allowance",Double.doubleToRawLongBits(dailyAllowance));
        editor.putLong("monthly_allowance",Double.doubleToRawLongBits(monthlyAllowance));
        editor.putLong("yearly_allowance",Double.doubleToRawLongBits(yearlyAllowance));
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
