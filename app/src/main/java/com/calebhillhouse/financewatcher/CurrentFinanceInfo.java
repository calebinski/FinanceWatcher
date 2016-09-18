package com.calebhillhouse.financewatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;

/**
 * Created by caleb on 9/16/2016.
 */
public class CurrentFinanceInfo extends AppCompatActivity {
    Button daily;
    Button monthly;
    Button yearly;
    Button allowances;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_finance_info);

        daily = (Button)findViewById(R.id.daily_finances_button);
        monthly = (Button)findViewById(R.id.monthly_finances_button);
        yearly = (Button)findViewById(R.id.yearly_finances_button);
        allowances = (Button)findViewById(R.id.allowances_button);

        allowances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentFinanceInfo.this, Allowances.class);
                startActivity(intent);
            }
        });
    }
}
