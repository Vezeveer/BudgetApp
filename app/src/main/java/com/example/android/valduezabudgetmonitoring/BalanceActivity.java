package com.example.android.valduezabudgetmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BalanceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        Button button = findViewById(R.id.btnAddBalance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();
            }
        });
    }

    public void openNextActivity() {
        EditText et1 = findViewById(R.id.tiBalanceInput);
        BudgetListActivity.balanceCurrent += Integer.parseInt(et1.getText().toString());
        BudgetListActivity.balance += Integer.parseInt(et1.getText().toString());

        Intent intent = new Intent(this, BudgetListActivity.class);
        startActivity(intent);
    }
}