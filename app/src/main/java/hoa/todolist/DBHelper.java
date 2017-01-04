package hoa.todolist;
/*********************************************
 * Course: COMP3074
 * Name: Hoa Nguyen
 * Student ID: 100959069
 * ********************************************/

/**
 * Created by hoa on 2016-11-11.
 */
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String LIST_TABLE_NAME = "list";
    public static final String LIST_COLUMN_ID = "id";
    public static final String LIST_COLUMN_TITILE = "title";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create list table with id column and title column
        String query = "CREATE TABLE " + LIST_TABLE_NAME + "(" + LIST_COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LIST_COLUMN_TITILE
                + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop list table if it exists
        db.execSQL("DROP TABLE IF EXISTS list");
        onCreate(db);

    }
    //insert title into list table
    public boolean insertList(String title) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIST_COLUMN_TITILE, title);
        db.insert(LIST_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select*from list where id=" + id + "", null);
        return res;

    }
    //delete title in list table
    public Integer deleteList(String title) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(LIST_TABLE_NAME, "title = ? ", new String[]{title});
    }
    //get all titles from list table
    public ArrayList<String> getAllList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select*from list", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            arrayList.add(res.getString(res.getColumnIndex(LIST_COLUMN_TITILE)));
            res.moveToNext();
        }
        return arrayList;
    }
}
