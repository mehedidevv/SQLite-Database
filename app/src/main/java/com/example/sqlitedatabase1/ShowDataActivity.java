package com.example.sqlitedatabase1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {
    //Initiate
    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User>userList;
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        //Connection
        sqlHelper=new SQLHelper(this);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList=new ArrayList<>();
        adapter=new UserAdapter(userList);
        recyclerView.setAdapter(adapter);

        //ShowData Get korte hobe
        Cursor cursor=sqlHelper.showData();

        while (cursor.moveToNext()){
           int ID=cursor.getInt(cursor.getColumnIndexOrThrow(SQLHelper.COL_ID));
           String name=cursor.getString(cursor.getColumnIndexOrThrow(SQLHelper.COL_NAME));
           String age=cursor.getString(cursor.getColumnIndexOrThrow(SQLHelper.COL_AGE));

           userList.add(new User(ID,name,age));
           adapter.notifyDataSetChanged();
        }
    }
}