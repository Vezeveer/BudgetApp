package com.example.android.valduezabudgetmonitoring;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class ExpensePagerActivity extends AppCompatActivity {

    private static final String EXTRA_EXPENSE_ID =
            "com.example.android.valduezabudgetmonitoring.expense_id";
    private ViewPager mViewPager;
    private List<Expense> MMExpenses;

    public static Intent newIntent(Context packageContext, UUID expenseId) {
        Intent intent = new Intent(packageContext, ExpensePagerActivity.class);
        intent.putExtra(EXTRA_EXPENSE_ID, expenseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_pager);

        UUID expenseId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_EXPENSE_ID);
        mViewPager = findViewById(R.id.expense_view_pager);

        MMExpenses = BudgetListActivity.sExpenseList;

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager){
            @Override
            public Fragment getItem(int position){
                Expense expense = MMExpenses.get(position);
                return ExpenseFragment.newInstance(expense.getmId());
            }

            @Override
            public int getCount(){
                return MMExpenses.size();
            }
        });

        for(int i = 0; i < MMExpenses.size(); i++){
            if(MMExpenses.get(i).getmId().equals(expenseId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}