package hu.nje.tienda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String databaseName = "signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT, lastName TEXT, firstName TEXT, userName TEXT, cityName TEXT, streetName TEXT, streetNumbers TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists alluser");
    }

    public Boolean insertData(String email, String password, String lastName, String firstName, String userName, String cityName, String streetName, String streetNumbers){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Automatikusan a kötelező nagybetűvel kezdődő szavakat kezdő nagybetűkké alakítása (pl.: ferenc => Ferenc)
        String uppercaseLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        String uppercaseFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        String uppercaseCityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String uppercaseStreetName = streetName.substring(0, 1).toUpperCase() + streetName.substring(1);
        String uppercaseUserName = userName.substring(0, 1).toUpperCase() + userName.substring(1);

        contentValues.put("email",email.toUpperCase());
        contentValues.put("password",password);
        contentValues.put("lastName",uppercaseLastName);
        contentValues.put("firstName",uppercaseFirstName);
        contentValues.put("userName",uppercaseUserName);
        contentValues.put("cityName",uppercaseCityName);
        contentValues.put("streetName",uppercaseStreetName);
        contentValues.put("streetNumbers",streetNumbers);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }

    public Boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        System.out.println("email:" + email);
        System.out.println("jelszo:" + password);
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0){
            System.out.println("válasz: true");
            return true;
        }else{
            System.out.println("válasz: false");
            return false;
        }

    }

    public Boolean checkEveryData(String email, String password, String lastName, String firstName, String userName, String cityName, String streetName, String streetNumbers){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ? and lastName = ? and firstName = ? and userName = ? and cityName = ? and streetName = ? and streetNumbers = ?", new String[]{email , password, lastName, firstName, userName , cityName, streetName, streetNumbers});

        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }

    }

}
