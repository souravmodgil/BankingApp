package com.bankingapp.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankingapp.R;
import com.bankingapp.sql.Accouts;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<Accouts> accoutsList;

    public UsersRecyclerAdapter(List<Accouts> listUsers) {
        this.accoutsList = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.txtaccountnumber.setText(accoutsList.get(position).accountnumber);
        holder.txtbalance.setText("$"+accoutsList.get(position).balance);

    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+accoutsList.size());
        return accoutsList.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView txtaccountnumber;
        public AppCompatTextView txtbalance;


        public UserViewHolder(View view) {
            super(view);
            txtaccountnumber = (AppCompatTextView) view.findViewById(R.id.txtaccountnumber);
            txtbalance = (AppCompatTextView) view.findViewById(R.id.txtbalance);

        }
    }


}
