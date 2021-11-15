package com.example.android.valduezabudgetmonitoring;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Locale;
import java.util.UUID;

public class ExpenseFragment extends Fragment {
    private Expense MMExpense;
    private EditText mTitleField;
    private static final String ARG_EXPENSE_ID = "expense_id";

    public static ExpenseFragment newInstance(UUID expenseId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSE_ID, expenseId);
        ExpenseFragment fragment = new ExpenseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ExpenseFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID expenseId = (UUID) getArguments().getSerializable(ARG_EXPENSE_ID);

        for(Expense expense : BudgetListActivity.sExpenseList){
            if (expense.getmId().equals(expenseId)){
                MMExpense = expense;
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expense,
                container, false);

        mTitleField = v.findViewById(R.id.edit_text_title);
        mTitleField.setText(MMExpense.getmTitle());
        Button mDateButton = v.findViewById(R.id.edit_text_date);

        mDateButton.setText(String.format(Locale.ENGLISH,
                "%1$tY %1$tb %1$td",
                MMExpense.getmDate()));
        mDateButton.setEnabled(false);

        return v;
    }
}