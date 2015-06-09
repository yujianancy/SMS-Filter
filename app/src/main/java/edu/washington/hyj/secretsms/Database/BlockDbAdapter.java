package edu.washington.hyj.secretsms.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yizhouhuang on 5/30/15.
 */
public class BlockDbAdapter {

    private Context context;

    public static final String DB_NAME="secretsms1.db";
    public static final int VERSION=1;
    public static final String LIST_BLOCKED_SMS_TAB="t_sms";
    public static final String BLACK_LIST_TAB="t_black";;

    public static final String ID="_id";
    public static final String NAME="t_name";
    public static final String NUMBER="t_number";
    public static final String CTIME="t_ctime";
    public static final String CONTENT="t_content";

    public BlockDbAdapter(Context context){
        this.context=context;
    }

    class BlockDbHelper extends SQLiteOpenHelper {


        public BlockDbHelper() {
            super(context, DB_NAME, null, VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            String createTabSmsSql="create table t_sms(_id integer primary key autoincrement,t_name text,t_number text,t_ctime text,t_content text)";
            String creatTabBlackSql="create table t_black(_id integer primary key autoincrement,t_name text,t_number text)";
            db.execSQL(creatTabBlackSql);
            db.execSQL(createTabSmsSql);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }
    }
    public SQLiteDatabase openDb(){
        BlockDbHelper helper=new BlockDbHelper();
        return helper.getWritableDatabase();
    }
    public Cursor findAllBlackList(){
        SQLiteDatabase db=openDb();
        return db.query(BLACK_LIST_TAB, null, null, null, null, null, null);
    }
    public void deleteBlack(String id){
        SQLiteDatabase db=openDb();
        db.delete(BLACK_LIST_TAB, ID+"=?",new String[] {id});
        db.close();
    }
    public Cursor findAllBlockedSms(){

        SQLiteDatabase db = openDb();
        return db.query(LIST_BLOCKED_SMS_TAB,null, null,null, null, null, null);

    }
    public String findNameByNumber(String number){
        SQLiteDatabase db=openDb();

        Cursor c=db.query(BLACK_LIST_TAB, null, NUMBER + "=?", new String[]{number}, null, null, null);
        if(null!=c&&c.getCount()>0){
            c.moveToFirst();
            String name=c.getString(c.getColumnIndex(NAME));
            return name;
        }

        return null;
    }

    public void addBlockedSms(String blackName,String number,String ctime,String content){
        SQLiteDatabase db=openDb();
        ContentValues values=new ContentValues();
        values.put(NAME, blackName);
        values.put(NUMBER, number);
        values.put(CTIME, ctime);
        values.put(CONTENT, content);
        db.insert(LIST_BLOCKED_SMS_TAB, null, values);
        db.close();
    }
    public void deleteSms(String id){
        SQLiteDatabase db=openDb();
        db.delete(LIST_BLOCKED_SMS_TAB, ID+"=?",new String[] {id});
        db.close();
    }
    public void addBlackList(String name, String number) {
        // TODO Auto-generated method stub
        SQLiteDatabase db=openDb();
        ContentValues values=new ContentValues();
        values.put(NAME, name);
        values.put(NUMBER, number);
        db.insert(BLACK_LIST_TAB, null, values);
        db.close();
    }
}
