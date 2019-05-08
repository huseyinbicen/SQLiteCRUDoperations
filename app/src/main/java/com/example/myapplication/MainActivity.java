package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDB db;
    EditText Name, Surname, Marks, Id4Update;
    Button btn_AddData, btn_ShowData, btn_UpdateData, btn_DeleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDB(this);

         Name = (EditText)findViewById(R.id.editText_Name);
         Surname = (EditText)findViewById(R.id.editText_Surname);
         Marks = (EditText)findViewById(R.id.editText_Marks);
         Id4Update = (EditText)findViewById(R.id.editText_GetId);
         btn_AddData = (Button)findViewById(R.id.button_AddData);
         btn_ShowData = (Button)findViewById(R.id.button_ShowData);
         btn_UpdateData = (Button)findViewById(R.id.button_Update);
         btn_DeleteData = (Button)findViewById(R.id.button_DeleteData);

         AddData();
         ShowData();
         UpdateData();
         DeleteData();
    }

    public void DeleteData()
    {
        btn_DeleteData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer deteleRows = db.DeleteData(Id4Update.getText().toString());

                if(deteleRows > 0)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted !!", Toast.LENGTH_SHORT).show();
                    Id4Update.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Deleted !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void UpdateData()
    {
        btn_UpdateData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isUpdate = db.UpdateData(Id4Update.getText().toString(),Name.getText().toString(),Surname.getText().toString(),Marks.getText().toString());

                if(isUpdate)
                {
                    Toast.makeText(MainActivity.this, "Data Update !!", Toast.LENGTH_SHORT).show();
                    Name.setText("");
                    Surname.setText("");
                    Marks.setText("");
                    Id4Update.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Update !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void AddData()
    {
        btn_AddData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean durum = db.AddData(Name.getText().toString(),Surname.getText().toString(),Marks.getText().toString());

                if(durum)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted !!", Toast.LENGTH_SHORT).show();
                    Name.setText("");
                    Surname.setText("");
                    Marks.setText("");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data Not Inserted !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void ShowData()
    {
        btn_ShowData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor cs = db.ShowData();
                if(cs.getCount() == 0)
                {
                    ShowMessage("ERROR" , "Nothing found!!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cs.moveToNext())
                {
                    buffer.append("Id: " + cs.getString(0)  + "\n");
                    buffer.append("Name: " + cs.getString(1)  + "\n");
                    buffer.append("Surname: " + cs.getString(2)  + "\n");
                    buffer.append("Marks: " + cs.getString(3)  + "\n");
                }

                ShowMessage("Data" , buffer.toString());
            }
        });
    }

    public void ShowMessage(String Title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

}
