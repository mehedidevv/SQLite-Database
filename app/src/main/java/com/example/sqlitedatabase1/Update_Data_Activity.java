package com.example.sqlitedatabase1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Data_Activity extends AppCompatActivity {

    EditText nameET,ageET,searchET;
    String ID;
    SQLHelper sqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        nameET=findViewById(R.id.nameET);
        ageET=findViewById(R.id.ageET);
        searchET=findViewById(R.id.searchET);
        sqlHelper=new SQLHelper(this);
    }


    //Search Id For Update Data
    public void search(View view) {

         ID=searchET.getText().toString();

        Cursor cursor=sqlHelper.searchData(Integer.parseInt(ID));

        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndexOrThrow(sqlHelper.COL_NAME));
            String age=cursor.getString(cursor.getColumnIndexOrThrow(sqlHelper.COL_AGE));

            nameET.setText(name);
            ageET.setText(age);
            // Toast.makeText(this, "Name:" +name+ "Age:" +age, Toast.LENGTH_SHORT).show();
        }

    }

    //Update Data
    public void update(View view) {
        String name=nameET.getText().toString();
        String age=ageET.getText().toString();

        if (name.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Please Serach any id First:", Toast.LENGTH_SHORT).show();
        }else {
          boolean check=  sqlHelper.updateData(Integer.parseInt(ID),name,age);

          if (check){
              Toast.makeText(this, "Update Succesfully", Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
          }
        }
    }


    //Delete Data
    public void delete(View view) {

        String name=nameET.getText().toString();
        String age=ageET.getText().toString();

        if (name.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "Please Serach any id First:", Toast.LENGTH_SHORT).show();
        }else {
            int check= sqlHelper.deleteData(Integer.parseInt(ID));
            if (check>0){
                Toast.makeText(this, "Deleted Succecfully", Toast.LENGTH_SHORT).show();
                nameET.setText(" ");
                ageET.setText(" ");
            }else {
                Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}