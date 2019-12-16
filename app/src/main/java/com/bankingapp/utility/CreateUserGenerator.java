package com.bankingapp.utility;

import com.bankingapp.database.AppDataBase;
import com.bankingapp.sql.Accouts;
import com.bankingapp.sql.Bill;
import com.bankingapp.sql.Users;

import java.util.Arrays;
import java.util.List;

public class CreateUserGenerator {
    private static CreateUserGenerator instance;
    private static AppDataBase dataBase;

    public static CreateUserGenerator with(AppDataBase appDataBase) {

        if (dataBase == null)
            dataBase = appDataBase;

        if (instance == null)
            instance = new CreateUserGenerator();

        return instance;
    }

    public Accouts getAccouts(String accountNumber)
    {
        if (dataBase == null)
            return null;
        return  dataBase.accountDao().loadAccout(accountNumber);
    }

    public void  updateAccout(Accouts accouts)
    {
        if (dataBase == null)
            return ;
          dataBase.accountDao().update(accouts);
    }

    public void generateUser() {
        if (dataBase == null)
            return;




        Users[] users = new Users[4];
        users[0] = userInstance(1, "Arun900", "0987","123456789");
        users[1] = userInstance(2, "Akash900", "9876","234567890");
        users[2] = userInstance(3, "Surjit900", "8765","345678901");
        users[3] = userInstance(4, "Samam900", "7654","456789012");


    }

    public Users isLogin(String cardno , String pincode)
    {
        if (dataBase == null)
            return null;
        return  dataBase.userDao().isLogin(cardno,pincode);
    }

    public List<Accouts> getAccoutList(int userID)
    {
        if (dataBase == null)
            return null;

        List<Accouts> list = Arrays.asList(dataBase.accountDao().getAccoutList(userID));
        return  list;
    }

    public List<Accouts> getAllAccoutList()
    {
        if (dataBase == null)
            return null;

        List<Accouts> list = Arrays.asList(dataBase.accountDao().loadAllAccouts());
        return  list;
    }

    public void generateBill() {
        if (dataBase == null)
            return;




        Bill[] bills = new Bill[4];
        bills[0] = billInstance(1, "Hydro", 30);
        bills[1] = billInstance(2, "Water", 50);
        bills[2] = billInstance(3, "Gas", 20);
        bills[3] = billInstance(4, "Mobile", 10);

        dataBase.billDao().insert(bills);
    }

    private Users userInstance(int id, String userName, String pincode, String admincardNo) {
        Users users = new Users();

        users.id = id;
        users.adminCardNo=admincardNo;
        users.UserName = userName;
        users.pincode = pincode;
        Accouts accouts = null;



        dataBase.userDao().insert(users);
        switch(id)
        {
            case 1 :

                accouts = accoutsInstance(1,"9872345690", 10000, id);
                dataBase.accountDao().insert(accouts);

                accouts = accoutsInstance(2,"99972345690", 10000, id);
                dataBase.accountDao().insert(accouts);


                break;
            case 2:

                accouts = accoutsInstance(3,"88856478907", 10000, id);
                dataBase.accountDao().insert(accouts);

                accouts = accoutsInstance(4,"8885647895667", 10000, id);
                dataBase.accountDao().insert(accouts);


                break;

            case 3 :

                accouts = accoutsInstance(5,"77756786789", 10000, id);
                dataBase.accountDao().insert(accouts);

                accouts = accoutsInstance(6,"7774569908082", 10000, id);
                dataBase.accountDao().insert(accouts);

                break;
            case 4:

                accouts = accoutsInstance(7,"666768457586", 10000, id);
                dataBase.accountDao().insert(accouts);

                accouts = accoutsInstance(8,"6666096892289", 10000, id);


                break;
        }






        return users;
    }

    private Accouts accoutsInstance(int id ,String accountNumber,int balance, int userid) {
        Accouts accouts = new Accouts();

        accouts.id = id;
        accouts.accountnumber = accountNumber;
        accouts.balance = balance;
        accouts.userid = userid;

        return accouts;
    }

    private Bill billInstance(int id ,String ulitilitName,int charges) {
        Bill bill = new Bill();



        bill.id = id;
        bill.ulitilitName = ulitilitName;
        bill.charges = charges;


        return bill;
    }


}
