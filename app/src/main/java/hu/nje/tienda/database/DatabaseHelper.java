package hu.nje.tienda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hu.nje.tienda.services.SecurityService;
import hu.nje.tienda.services.UserDatasService;

public class DatabaseHelper extends SQLiteOpenHelper {

    // TODO README
    /* USER Adatbázis kezelő (de akár később az eladandó tárgyakat is bele lehet fűzni)
    *  Tartalmaz különféle ellenőrzéseket és itt adja hozzá a memóriához az adott
    *  felhasználó adatait "SETTERS" (Csak bejelentkezés után adja hozzá az adatokat)
    * */

    public UserDatasService usrv = new UserDatasService();

    public SecurityService secsrv = new SecurityService();

    public static final String databaseName = "tiendadatas.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT, lastName TEXT, firstName TEXT, userName TEXT, cityName TEXT, streetName TEXT, streetNumbers TEXT, phoneNumber TEXT, birthday TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists alluser");
    }

    // Regisztrációkor ha minden elenőrzés stimmel akkor "bele rakja" az adatokat a db-be
    public Boolean insertData(String email, String password, String lastName, String firstName, String userName, String cityName, String streetName, String streetNumbers, String phoneNumber, String birthday){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Automatikusan a kötelező nagybetűvel kezdődő szavakat kezdő nagybetűkké alakítása (pl.: ferenc => Ferenc)
        String uppercaseLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        String uppercaseFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        String uppercaseCityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String uppercaseStreetName = streetName.substring(0, 1).toUpperCase() + streetName.substring(1);
        String uppercaseUserName = userName.substring(0, 1).toUpperCase() + userName.substring(1);

        /* Dekódolja a jelszót és úgy rakja be a db-be */
        try {
            String secpass = secsrv.encryptAndEncode(password);
            contentValues.put("password", secpass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        contentValues.put("email",email);
        contentValues.put("lastName",uppercaseLastName);
        contentValues.put("firstName",uppercaseFirstName);
        contentValues.put("userName",uppercaseUserName);
        contentValues.put("cityName",uppercaseCityName);
        contentValues.put("streetName",uppercaseStreetName);
        contentValues.put("streetNumbers",streetNumbers);
        contentValues.put("phoneNumber",phoneNumber);
        contentValues.put("birthday",birthday);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1){
            System.out.println("RÁZUULT: " + result);
            return false;
        }else{
            return true;
        }
    }

    // Regisztrációkor ellenőrzi, hogy van-e már ilyen e-mail a db-ben
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }

    // Bejelentkezéskor ellenőrzi hogy van-e ilyen user
    public Boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        try {
            /* A kapott jelszót is dekódolja */
            String secpass = secsrv.encryptAndEncode(password);
            Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, secpass});
            if (cursor.getCount() > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // SETTERS A user adatait memóriába menti ahol a későbbikben adatmegjelenítés miatt szükséges lesz
    public void setDatas(String email){
        this.usrv.setUser_email_address(email);
        this.setdbfirstname(email);
        this.setdblastname(email);
        this.setdbcity(email);
        this.setdbstreetname(email);
        this.setdbstreetnumber(email);
        this.setdbusername(email);
        this.setdbphonenumber(email);
        this.setdbbirthday(email);
    }

    public void setdbfirstname(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor first_name_db = MyDatabase.rawQuery("Select firstName from allusers where email = ?", new String[]{email});
        if (first_name_db.moveToFirst()){
            this.usrv.setUser_first_name(first_name_db.getString(0));
        }else{
            this.usrv.setUser_first_name("Hibás adat");
        }
    }

    public void setdblastname(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor last_name_db = MyDatabase.rawQuery("Select lastName from allusers where email = ?", new String[]{email});
        if (last_name_db.moveToFirst()){
            this.usrv.setUser_last_name(last_name_db.getString(0));
        }else{
            this.usrv.setUser_last_name("Hibás adat");
        }
    }

    public void setdbcity(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor city_name_db = MyDatabase.rawQuery("Select cityName from allusers where email = ?", new String[]{email});
        if (city_name_db.moveToFirst()){
            this.usrv.setUser_city(city_name_db.getString(0));
        }else{
            this.usrv.setUser_city("Hibás adat");
        }
    }

    public void setdbstreetname(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor street_name_db = MyDatabase.rawQuery("Select streetName from allusers where email = ?", new String[]{email});
        if (street_name_db.moveToFirst()){
            this.usrv.setUser_street(street_name_db.getString(0));
        }else{
            this.usrv.setUser_street("Hibás adat");
        }
    }

    public void setdbstreetnumber(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor street_number_db = MyDatabase.rawQuery("Select streetNumbers from allusers where email = ?", new String[]{email});
        if (street_number_db.moveToFirst()){
            this.usrv.setUser_street_number(street_number_db.getString(0));
        }else{
            this.usrv.setUser_street_number("Hibás adat");
        }
    }

    public void setdbusername(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor user_name_db = MyDatabase.rawQuery("Select userName from allusers where email = ?", new String[]{email});
        if (user_name_db.moveToFirst()){
            this.usrv.setUser_name(user_name_db.getString(0));
        }else{
            this.usrv.setUser_name("Hibás adat");
        }
    }

    public void setdbphonenumber(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor phone_number_db = MyDatabase.rawQuery("Select phoneNumber from allusers where email = ?", new String[]{email});
        if (phone_number_db.moveToFirst()){
            this.usrv.setPhone_number(phone_number_db.getString(0));
        }else{
            this.usrv.setPhone_number("Hibás adat");
        }
    }

    public void setdbbirthday(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        /* Lekérdezések e-mail cím alapján */
        Cursor birthday_db = MyDatabase.rawQuery("Select birthday from allusers where email = ?", new String[]{email});
        if (birthday_db.moveToFirst()){
            this.usrv.setBirthday(birthday_db.getString(0));
        }else{
            this.usrv.setBirthday("Hibás adat");
        }
    }

}
