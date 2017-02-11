package com.example.hemant.dbdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {





    private EditText edid;
    private EditText edFname;
    private EditText edLname;
    private EditText edadd;
    private EditText edsalary;
    Helperd myhelper;
    private Button btninsert,btndelete,btnupdate,btnload;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        myhelper=new Helperd(MainActivity.this);
    }
    private void init(){
        edid=(EditText)findViewById(R.id.et1);
        edFname=(EditText)findViewById(R.id.et2);
        edLname=(EditText)findViewById(R.id.et3);
        edadd=(EditText)findViewById(R.id.et4);
        edsalary=(EditText)findViewById(R.id.et5);

        btninsert=(Button)findViewById(R.id.btinsert);
        btndelete=(Button)findViewById(R.id.btdelete);
        btnupdate=(Button)findViewById(R.id.btupdate);
        btnload=(Button)findViewById(R.id.btload);
        tv=(TextView)findViewById(R.id.textView);

        btninsert.setOnClickListener(dbbuttonlistener);
        btnupdate.setOnClickListener(dbbuttonlistener);
        btndelete.setOnClickListener(dbbuttonlistener);
        btnload.setOnClickListener(dbbuttonlistener);

    }
    private View.OnClickListener dbbuttonlistener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btinsert:

                    long result=myhelper.insert(Integer.parseInt(getValue(edid)),getValue(edFname),getValue(edLname)
                    ,Double.valueOf(getValue(edsalary)),getValue(edadd));
                    if(result==-1)
                    {
                        Toast.makeText(MainActivity.this,"some Insertion error",Toast.LENGTH_LONG).show();
                    }
                    else
                    {Toast.makeText(MainActivity.this,"Row has been inserted at ID:"+result,Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btdelete:
                    long result1=myhelper.delete(Integer.parseInt(getValue(edid)));
                    if(result1==-1)
                    {
                        Toast.makeText(MainActivity.this,"some Deletion error",Toast.LENGTH_LONG).show();
                    }
                    else
                    {Toast.makeText(MainActivity.this,"Row has been Deleted at ID:"+result1,Toast.LENGTH_LONG).show();
                    }
                    break;



                case R.id.btupdate:
                    long result2=myhelper.update(Integer.parseInt(getValue(edid)),getValue(edFname),getValue(edLname)
                            ,Double.valueOf(getValue(edsalary)),getValue(edadd));
                    if(result2==-1)
                    {
                        Toast.makeText(MainActivity.this,"some Updation error",Toast.LENGTH_LONG).show();
                    }
                    else
                    {Toast.makeText(MainActivity.this,"Row has been updated at ID:"+result2,Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.btload:
                    StringBuffer finaldata=new StringBuffer();
                    Cursor cursor=myhelper.getAllRecords();


                    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
                        finaldata.append(cursor.getInt(cursor.getColumnIndex(myhelper.ID)));
                        finaldata.append(" - ");
                        finaldata.append(cursor.getString(cursor.getColumnIndex(myhelper.Fname)));
                        finaldata.append(" - ");
                        finaldata.append(cursor.getString(cursor.getColumnIndex(myhelper.Lname)));
                        finaldata.append(" - ");
                        finaldata.append(cursor.getString(cursor.getColumnIndex(myhelper.Address)));
                        finaldata.append(" - ");
                        finaldata.append(cursor.getDouble(cursor.getColumnIndex(myhelper.Salary)));
                        finaldata.append(" \n ");
                    }
                    tv.setText(finaldata);
                        break;


            }
        }
    };
    @Override
    protected void onStart(){
        super.onStart();
        myhelper.openDb();
    }
    @Override
    protected void onStop(){
        super.onStop();
        myhelper.closeDb();
    }
    private String getValue(EditText editText){return editText.getText().toString().trim();}



    }

