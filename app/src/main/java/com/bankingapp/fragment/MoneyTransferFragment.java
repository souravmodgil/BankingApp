package com.bankingapp.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
import com.bankingapp.helpers.InputValidation;
import com.bankingapp.sql.Accouts;
import com.bankingapp.utility.CreateUserGenerator;
import com.bankingapp.utility.SaveSharedPreference;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MoneyTransferFragment  extends Fragment {

    private MaterialSpinner from_account_spinner,to_account_spinner;
    private TextInputEditText textInputEditTextMoneyTransfer;
    private TextInputLayout textInputLayoutMoneyTransfer;
    private AppCompatButton appCompatButtonTransferAmount;
    private List<Accouts> accoutsList;
    private List<Accouts> myAccoutsList;
    int myaccountnumber =0;
    String myaccNumber = "";
    String toaccNumber = "";
    int toaccountnumber =0;
    private InputValidation inputValidation;

    public MoneyTransferFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_money_transfer, container, false);
        from_account_spinner = rootView.findViewById(R.id.from_account);
        to_account_spinner = rootView.findViewById(R.id.to_account);
        textInputLayoutMoneyTransfer = rootView.findViewById(R.id.textInputLayoutMoneyTransfer);
        textInputEditTextMoneyTransfer = rootView.findViewById(R.id.textInputEditTextMoneyTransfer);
        appCompatButtonTransferAmount = rootView.findViewById(R.id.appCompatButtonTransferAmount);

        SaveSharedPreference sh_Pref = new SaveSharedPreference();
        int userid = sh_Pref.getUserid(getActivity());
        AppDataBase database = AppDataBase.getAppDatabase(getActivity());
        myAccoutsList =  CreateUserGenerator.with(database).getAccoutList(userid);
        accoutsList = CreateUserGenerator.with(database).getAllAccoutList();

        ArrayAdapter<Accouts> toadapter =
                new ArrayAdapter<Accouts>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, accoutsList);
        toadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Accouts> fromadapter =
                new ArrayAdapter<Accouts>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, myAccoutsList);
        fromadapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        from_account_spinner.setAdapter(fromadapter);
        to_account_spinner.setAdapter(toadapter);
        inputValidation = new InputValidation(getActivity());
        appCompatButtonTransferAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!inputValidation.isInputEditTextFilled(textInputEditTextMoneyTransfer, textInputLayoutMoneyTransfer, getString(R.string.error_message_Transfer))) {
                    return;
                }
                if(myaccountnumber == 0)
                {
                    Toast.makeText(getActivity(), "Please Select your accout from which you want to tranfered amount ", Toast.LENGTH_LONG).show();
                    return;
                }
                if(toaccountnumber == 0)
                {
                    Toast.makeText(getActivity(), "Please Select the accout to  which you want to tranfered", Toast.LENGTH_LONG).show();
                    return;
                }

                if(myaccNumber.equals(toaccNumber))
                {
                    Toast.makeText(getActivity(), "You cannot transfer the money to same account", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    AppDataBase database = AppDataBase.getAppDatabase(getActivity());
                    Accouts myaccout = CreateUserGenerator.with(database).getAccouts(myaccNumber);
                    Accouts toAccount = CreateUserGenerator.with(database).getAccouts(toaccNumber);
                    int amountTranfer = Integer.parseInt(textInputEditTextMoneyTransfer.getText().toString());




                    if(myaccout.balance>0 && myaccout.balance > amountTranfer  && amountTranfer>0)
                        myaccout.balance = myaccout.balance -amountTranfer;
                    else
                    {
                        Toast.makeText(getActivity(), "Your Account  Balance  is not sufficient", Toast.LENGTH_LONG).show();
                        return;
                    }
                    CreateUserGenerator.with(database).updateAccout(myaccout);

                    if(amountTranfer>0)
                    toAccount.balance = toAccount.balance +amountTranfer;
                    CreateUserGenerator.with(database).updateAccout(toAccount);

                    Toast.makeText(getActivity(), "Money has been transfer successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        from_account_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                if(position>=0) {
                    myaccNumber = myAccoutsList.get(position ).accountnumber;
                    myaccountnumber = 1;

                }
                else {
                    myaccountnumber = 0;
                    myaccNumber = "";
                }




                // your code here
            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });


        to_account_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                if(position>=0) {
                    toaccNumber = accoutsList.get(position ).accountnumber;
                    toaccountnumber = 1;

                }
                else {
                    toaccountnumber = 0;
                    toaccNumber = "";
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
