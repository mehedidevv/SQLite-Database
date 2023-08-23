package com.example.sqlitedatabase1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameET,ageET,searchET;
    Button insert;

    //SQLHelper Class ar variabe create korte hobe
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Function Creation for connect
        connection();

        //OnClickLister To  Insert Data
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nameET.getText().toString();
                String age=ageET.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Your name", Toast.LENGTH_SHORT).show();
                } else if (age.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
                }else {

                   long id =sqlHelper.insertData(name,age);
                    Toast.makeText(MainActivity.this, "Data is insert and id is" +id , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void connection() {
        nameET=findViewById(R.id.nameET);
        ageET=findViewById(R.id.ageET);
        insert=findViewById(R.id.insert);
        searchET=findViewById(R.id.searchET);

        //sqlhelper ar maddome amra data insert korte parbo.
        sqlHelper=new SQLHelper(this);
    }


    //ShowData
    public void showdata(View view) {
        startActivity(new Intent(MainActivity.this,ShowDataActivity.class));
    }


    //Searching id
    public void search(View view) {

        String ID=searchET.getText().toString();

        Cursor cursor=sqlHelper.searchData(Integer.parseInt(ID));

        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndexOrThrow(sqlHelper.COL_NAME));
            String age=cursor.getString(cursor.getColumnIndexOrThrow(sqlHelper.COL_AGE));

            //Allert Dialog Design
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Search Result For ID:" +ID);
            builder.setMessage("Name:"+name+"\nAge:"+age);

            //Cancel button Craete and ONClick
            builder.setCancelable(false);
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.cancel();
                }
            });

            //Alert Dialog Create and Show korte hobe
            AlertDialog alertDialog=builder.create();
            alertDialog.show();

           // Toast.makeText(this, "Name:" +name+ "Age:" +age, Toast.LENGTH_SHORT).show();

        }

    }

    //Update Data
    public void updateData(View view) {
        startActivity(new Intent(MainActivity.this,Update_Data_Activity.class));
    }
}