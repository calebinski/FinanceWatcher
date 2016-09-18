package com.calebhillhouse.financewatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button currentFinances;
    Button setFinances;
    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find buttons
        currentFinances = (Button)findViewById(R.id.current_finances_button);
        setFinances = (Button)findViewById(R.id.set_finances_button);
        settings = (Button)findViewById(R.id.settings_button);

        //set listener to look for button click
        currentFinances.setOnClickListener(new View.OnClickListener(){
            //when you click it...
            public void onClick(View v){
                //open new activity (allowances)
                Intent intent = new Intent(MainActivity.this, CurrentFinanceInfo.class);
                startActivity(intent);
            }
        });

        //set listener to look for button click


        //set listener to look for button click
    }

}
