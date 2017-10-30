package com.example.wxy.demo3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper =new MyDatabaseHelper(this,"BookStore.db",null,5) ;
        Button creatDatabase=(Button )findViewById(R.id.creat_database) ;
        Button addData=(Button)  findViewById(R.id.add_data ) ;
        Button updateData=(Button)  findViewById(R.id.update_data) ;
        Button deleteData=(Button)  findViewById(R.id.delete_data) ;
        Button queryData=(Button)  findViewById(R.id.query_data ) ;
        creatDatabase .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        addData .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=myDatabaseHelper .getWritableDatabase() ;
                ContentValues values =new ContentValues() ;
                values .put("name","21天学会java") ;
                values .put("pages",600) ;
                values .put("price",20.99) ;
                db.insert("Book",null ,values ) ;
                values .put("name","我为什么这样美") ;
                values .put("pages",1000) ;
                values .put("price",79.99) ;
                db.insert("Book",null ,values ) ;
               // Log.d("MainActivity","添加成功！");

            }
        }) ;
        updateData .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=myDatabaseHelper .getWritableDatabase() ;
                ContentValues values =new ContentValues() ;
                values.put("price",22.99);
                db.update("Book",values ,"name=?",new String[]{"21天学会java"}) ;
                Log.d("MainActivity","修改成功！");
            }
        }) ;
        deleteData .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=myDatabaseHelper .getWritableDatabase() ;
                db.delete("Book","pages>?",new String[] {"700"}) ;
                Log.d("MainActivity","删除成功！");

            }
        }) ;
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=myDatabaseHelper .getWritableDatabase() ;
                Cursor cursor =db.query("Book",null,null,null,null,null,null);
                if(cursor .moveToFirst() ){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int id=cursor.getInt(cursor.getColumnIndex("id"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages  = cursor.getInt(cursor.getColumnIndex("name"));
                        Log.d("MainActivity","book name is "+name);
                        Log.d("MainActivity","book author is "+author);
                        Log.d("MainActivity","book price is "+price);
                        Log.d("MainActivity","book pages is "+pages );
                        Log.d("MainActivity","book id is "+id );
                    }while(cursor .moveToNext() );
                    cursor .close();
                }
            }
        }) ;
    }
}
