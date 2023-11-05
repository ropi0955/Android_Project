package hu.nje.tienda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Productlist.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_products";
    private static final String COLUMN_ID = "product_id";
    private static final String COLUMN_NAME = "product_name";
    private static final String COLUMN_QUANTITY = "product_quantity";
    private static final String COLUMN_PRICE = "product_price";
    private static final String COLUMN_DESCRIPTION = "product_description";
    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook (String name, int quantity, int price, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
        {
            Toast.makeText(context, "Sikertelen", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Új termék beszúrva", Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db !=null)
        {
         cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    void updateData(String row_id, String name, String quantity, String price, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context, "Nem sikerült frissíteni", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sikeres változtatás", Toast.LENGTH_SHORT).show();
        }
    }

}

