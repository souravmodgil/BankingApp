package com.bankingapp.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bankingapp.R;
import com.bankingapp.database.AppDataBase;
import com.bankingapp.sql.Accouts;
import com.bankingapp.utility.CreateUserGenerator;
import com.bankingapp.utility.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class BillFragment extends Fragment {
    private MaterialSpinner bill_spinner,account_balance;
    private TextInputEditText textInputEditTextBillAmount;
    private AppCompatButton appCompatButtonPayBill;
    private List<Accouts> accoutsList;
    int accountnumber =0;
    String accNumber = "";
    int billamount =0;

    public BillFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bill, container, false);
        bill_spinner = rootView.findViewById(R.id.bill_spinner);
        account_balance = rootView.findViewById(R.id.account_balance);
        textInputEditTextBillAmount = rootView.findViewById(R.id.textInputEditTextBillAmount);
        appCompatButtonPayBill = rootView.findViewById(R.id.appCompatButtonPayBill);

        textInputEditTextBillAmount.setEnabled(false);

        List<String> list = new ArrayList<String>();
        list.add("Hydro");
        list.add("Water");
        list.add("Gas");
        list.add("Phone");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bill_spinner.setAdapter(dataAdapter);

        SaveSharedPreference sh_Pref = new SaveSharedPreference();
        int userid = sh_Pref.getUserid(getActivity());
        AppDataBase database = AppDataBase.getAppDatabase(getActivity());
        accoutsList =  CreateUserGenerator.with(database).getAccoutList(userid);


        ArrayAdapter<Accouts> adapter =
                new ArrayAdapter<Accouts>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, accoutsList);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        account_balance.setAdapter(adapter);
        appCompatButtonPayBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(billamount ==0)
                {
                    Toast.makeText(getActivity(), "Please Select Bill Type", Toast.LENGTH_LONG).show();
                    return;
                }
                if(accountnumber ==0)
                {
                    Toast.makeText(getActivity(), "Please Select Account Number", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    AppDataBase database = AppDataBase.getAppDatabase(getActivity());
                   Accouts accouts =  CreateUserGenerator.with(database).getAccouts(accNumber);

                   if(accouts.balance>0 && accouts.balance > billamount)
                    accouts.balance = accouts.balance -billamount;
                    else
                   {
                       Toast.makeText(getActivity(), "Your Balance  is not sufficient", Toast.LENGTH_LONG).show();
                       return;
                   }

                    CreateUserGenerator.with(database).updateAccout(accouts);
                     accouts =  CreateUserGenerator.with(database).getAccouts(accNumber);
                    Toast.makeText(getActivity(), "Bill is paid Your remaining amount in   " +accouts.accountnumber + " is $" + accouts.balance, Toast.LENGTH_LONG).show();


                }



            }
        });

        account_balance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


               if(position>=0) {
                   accNumber = accoutsList.get(position ).accountnumber;
                   accountnumber = 1;
                   Toast.makeText(getActivity(), accNumber, Toast.LENGTH_LONG);
               }
               else {
                   accountnumber = 0;
                   accNumber = "";
               }




                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                Toast.makeText(getActivity(), "Not Selected", Toast.LENGTH_LONG).show();
            }

        });

        bill_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

               switch (position)
               {
                   case 0:
                       textInputEditTextBillAmount.setText("$100");
                       billamount =100;
                       break;
                   case 1:
                       textInputEditTextBillAmount.setText("$60");
                       billamount =60;
                       break;
                   case 2:
                       textInputEditTextBillAmount.setText("$40");
                       billamount =40;
                       break;
                   case 3:
                       textInputEditTextBillAmount.setText("$30");
                       billamount =30;
                       break;
                   case -1:
                       textInputEditTextBillAmount.setText("");
                       billamount =0;
                           break;


               }


                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return rootView;
    }
}