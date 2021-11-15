package com.example.android.valduezabudgetmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ExpenseListFragment extends Fragment {

    private RecyclerView mExpenseRecyclerView;
    private ExpenseAdapter mAdapter;
    TextView tvBal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        mExpenseRecyclerView = view.findViewById(R.id.expense_recycler_view);
        mExpenseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        Button buttonGoToBalAct = view.findViewById(R.id.btnGoToBalanceActivity);
        buttonGoToBalAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BalanceActivity.class);
                startActivity(intent);
            }
        });

        Button buttonGoToAddExp = view.findViewById(R.id.btnGoToAddExpense);
        buttonGoToAddExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddExpenseActivity.class);
                startActivity(intent);
            }
        });

        tvBal = view.findViewById(R.id.tvBalance);
        tvBal.setText("BALANCE: " + BudgetListActivity.balance + " PHP ");
        tvBal = view.findViewById(R.id.tvBalanceCurrent);
        tvBal.setText("AVAIL:   " + BudgetListActivity.balanceCurrent + " PHP ");

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        if(mAdapter == null){
            mAdapter = new ExpenseAdapter(BudgetListActivity.sExpenseList);
            mExpenseRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ExpenseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Expense MMExpense;

        public ExpenseHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_expense, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.expense_title);
            mDateTextView = itemView.findViewById(R.id.expense_date);
        }

        public void bind(Expense expense){
            MMExpense = expense;
            mTitleTextView.setText(MMExpense.getmTitle());
            mDateTextView.setText(
                    MMExpense.getmAmount() + " PHP - " +
                    String.format(Locale.ENGLISH,
                    "%1$tY %1$tb %1$td",
                    MMExpense.getmDate()));
        }

        @Override
        public void onClick(View view) {
            Intent intent = ExpensePagerActivity.newIntent(getActivity(), MMExpense.getmId());
            startActivity(intent);
        }
    }

    private class ExpenseAdapter extends RecyclerView.Adapter<ExpenseHolder>{

        private List<Expense> MMExpenses;

        public ExpenseAdapter(List<Expense> expenses){
            MMExpenses = expenses;
        }

        @Override
        public ExpenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ExpenseHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ExpenseHolder holder, int position) {
            Expense expense = MMExpenses.get(position);
            holder.bind(expense);
        }

        @Override
        public int getItemCount() {
            try {
                return MMExpenses.size();
            } catch (Exception e){
                return 0;
            }
        }
    }
}