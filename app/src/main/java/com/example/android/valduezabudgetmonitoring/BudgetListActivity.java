package com.example.android.valduezabudgetmonitoring;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class BudgetListActivity extends SingleFragmentActivity {
    public static int balanceCurrent;
    public static int balance;
    public static List<Expense> sExpenseList = new ArrayList<>();

    @Override
    protected Fragment createFragment(){
        return new ExpenseListFragment();
    }
}