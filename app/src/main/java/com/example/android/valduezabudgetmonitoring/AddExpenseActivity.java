package com.example.android.valduezabudgetmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Button buttonAddExpense = findViewById(R.id.btnAddExpense);
        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextActivity();
            }
        });
    }

    public void openNextActivity() {
        EditText et1 = findViewById(R.id.editTextAddExpense);
        int amount = Integer.parseInt(et1.getText().toString());
        EditText etTitle = findViewById(R.id.editTextAddExpTitle);

        if(BudgetListActivity.balanceCurrent < amount){
            Toast.makeText(this, "Amount is over your budget!",
                    Toast.LENGTH_SHORT).show();
        } else {
            BudgetListActivity.balanceCurrent -= amount;

            Expense expense = new Expense();
            expense.setmTitle(etTitle.getText().toString());
            expense.setmAmount(amount);

            BudgetListActivity.sExpenseList.add(expense);
            Intent intent = new Intent(this, BudgetListActivity.class);
            startActivity(intent);
        }

    }
}