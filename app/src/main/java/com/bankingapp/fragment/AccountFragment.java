package com.bankingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankingapp.R;
import com.bankingapp.adapter.UsersRecyclerAdapter;
import com.bankingapp.database.AppDataBase;
import com.bankingapp.sql.Accouts;
import com.bankingapp.utility.CreateUserGenerator;
import com.bankingapp.utility.SaveSharedPreference;

import java.util.List;

public class AccountFragment extends Fragment {

    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<Accouts> accoutsList;
    private UsersRecyclerAdapter usersRecyclerAdapter;


    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        recyclerViewUsers = rootView.findViewById(R.id.recyclerViewUsers);
        initObjects();
        return rootView;
    }



    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        AppDataBase database = AppDataBase.getAppDatabase(getActivity());


        SaveSharedPreference sh_Pref = new SaveSharedPreference();
        int userid = sh_Pref.getUserid(getActivity());
        accoutsList =  CreateUserGenerator.with(database).getAccoutList(userid);
        usersRecyclerAdapter = new UsersRecyclerAdapter(accoutsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);




    }

}
