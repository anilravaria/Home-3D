package com.example.mypc.homelayoutgenerator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import static android.text.TextUtils.*;


public class MainActivity extends AppCompatActivity {
    Button b1,reset;
    Spinner s1,s2,s3,s4;
    EditText et,et2,et3,et4,et5,et6,et7,et8;
    TextView t1,t2,t3,t16;
    DataBaseHelper myDB;
    EditText name1,name2,name3;

    float len_room, wid_room, len_bed,wid_bed,len_cupboard, wid_cupboard,len_app,wid_app;
    public static String bedPosition, CupboardPosition,AppliancePosition,n1,n2,n3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB =new DataBaseHelper(this);
        b1= (Button)findViewById(R.id.button);
        reset= (Button)findViewById(R.id.reset);
        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner2);
        s3=(Spinner)findViewById(R.id.spinner3);
        s4=(Spinner)findViewById(R.id.spinner4);
        et=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        et5=(EditText)findViewById(R.id.editText5);
        et6=(EditText)findViewById(R.id.editText6);
        et7=(EditText)findViewById(R.id.editText7);
        et8=(EditText)findViewById(R.id.editText8);
        t1=(TextView)findViewById(R.id.textView6);
        /*t2=(TextView)findViewById(R.id.textView7);
        t3=(TextView)findViewById(R.id.textView8);*/
        t16=(TextView)findViewById (R.id.textView16);
        name1=(EditText)findViewById (R.id.editText9);
        name2=(EditText)findViewById (R.id.editText10);
        name3=(EditText)findViewById (R.id.editText11);
        name1.setVisibility (View.GONE);
        name2.setVisibility (View.GONE);
        name3.setVisibility (View.GONE);
        t1.setVisibility(View.GONE);
        /*t2.setVisibility(View.GONE);
        t3.setVisibility (View.GONE);*/
        t16.setVisibility (View.GONE);
        et3.setVisibility(View.GONE);
        et4.setVisibility(View.GONE);
        et5.setVisibility(View.GONE);
        et6.setVisibility(View.GONE);
        et7.setVisibility(View.GONE);
        et8.setVisibility(View.GONE);
        s2.setVisibility (View.GONE);
        s3.setVisibility (View.GONE);
        s4.setVisibility (View.GONE);
        ArrayAdapter<CharSequence> a1 = ArrayAdapter.createFromResource(this,R.array.align, R.layout.support_simple_spinner_dropdown_item);
        a1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s2.setAdapter(a1);
        s3.setAdapter (a1);
        s4.setAdapter (a1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.NumberofApplicances, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        ResetButton();
        generateLayout();
        numberOfItems();
    }



    private void numberOfItems() {
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItemPosition()==0)
                {
                    s2.setVisibility (View.GONE);
                    s3.setVisibility (View.GONE);
                    s4.setVisibility (View.GONE);
                    t1.setVisibility(View.GONE);
                    /*t2.setVisibility(View.GONE);
                    t3.setVisibility (View.GONE);*/
                    t16.setVisibility (View.GONE);
                    et3.setVisibility(View.GONE);
                    et4.setVisibility(View.GONE);
                    et5.setVisibility(View.GONE);
                    et6.setVisibility(View.GONE);
                    et7.setVisibility(View.GONE);
                    et8.setVisibility(View.GONE);
                    name1.setVisibility (View.GONE);
                    name2.setVisibility (View.GONE);
                    name3.setVisibility (View.GONE);

                }
                if(parent.getSelectedItemPosition()==1)
                {
                    s2.setVisibility (View.VISIBLE);
                    t16.setVisibility (View.VISIBLE);
                    s3.setVisibility (View.GONE);
                    s4.setVisibility (View.GONE);
                    t1.setVisibility(View.VISIBLE);
                    /*t2.setVisibility(View.GONE);
                    t3.setVisibility (View.GONE);*/
                    et3.setVisibility(View.VISIBLE);
                    et4.setVisibility(View.VISIBLE);
                    et5.setVisibility(View.GONE);
                    et6.setVisibility(View.GONE);
                    et7.setVisibility(View.GONE);
                    et8.setVisibility(View.GONE);
                    name1.setVisibility (View.VISIBLE);
                    name2.setVisibility (View.GONE);
                    name3.setVisibility (View.GONE);

                }
                if(parent.getSelectedItemPosition()==2)
                {
                    t1.setVisibility(View.VISIBLE);
                    /*t2.setVisibility(View.VISIBLE);
                    t3.setVisibility (View.GONE);*/
                    t16.setVisibility (View.VISIBLE);
                    et3.setVisibility(View.VISIBLE);
                    et4.setVisibility(View.VISIBLE);
                    et5.setVisibility(View.VISIBLE);
                    et6.setVisibility(View.VISIBLE);
                    et7.setVisibility(View.GONE);
                    et8.setVisibility(View.GONE);
                    s2.setVisibility (View.VISIBLE);
                    s3.setVisibility (View.VISIBLE);
                    s4.setVisibility (View.GONE);
                    name1.setVisibility (View.VISIBLE);
                    name2.setVisibility (View.VISIBLE);
                    name3.setVisibility (View.GONE);

                }
                if(parent.getSelectedItemPosition()==3)
                {
                    t1.setVisibility(View.VISIBLE);
                    t16.setVisibility (View.VISIBLE);
                    /*t2.setVisibility(View.VISIBLE);
                    t3.setVisibility (View.VISIBLE);*/
                    et3.setVisibility(View.VISIBLE);
                    et4.setVisibility(View.VISIBLE);
                    et5.setVisibility(View.VISIBLE);
                    et6.setVisibility(View.VISIBLE);
                    et7.setVisibility(View.VISIBLE);
                    et8.setVisibility(View.VISIBLE);
                    s2.setVisibility (View.VISIBLE);
                    s3.setVisibility (View.VISIBLE);
                    s4.setVisibility (View.VISIBLE);
                    name1.setVisibility (View.VISIBLE);
                    name2.setVisibility (View.VISIBLE);
                    name3.setVisibility (View.VISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void generateLayout() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1= String.valueOf (name1.getText ());
                n2= String.valueOf (name2.getText ());
                n3= String.valueOf (name3.getText ());
                bedPosition=s2.getSelectedItem ().toString ();
                CupboardPosition=s3.getSelectedItem ().toString ();
                AppliancePosition=s4.getSelectedItem ().toString ();

                if(s1.getSelectedItemPosition ()==0)
                {
                    if(!isEmpty (et.getText ())&&!isEmpty (et.getText ()))
                    {
                        len_room = Float.parseFloat (et.getText ().toString ());
                        wid_room = Float.parseFloat (et2.getText ().toString ());
                        len_bed = 0;
                        wid_bed = 0;
                        len_cupboard=0;
                        wid_cupboard=0;
                        len_app=0;
                        wid_app=0;
                        Toast.makeText (getApplicationContext (), "" + len_room + wid_room + len_bed + wid_bed + len_cupboard + wid_cupboard + len_app + wid_app, Toast.LENGTH_SHORT).show ();
                        boolean result = myDB.insertData (len_room, wid_room, len_bed, wid_bed, len_cupboard, wid_cupboard, len_app, wid_app);
                        if (result == true) {
                            Toast.makeText (getApplicationContext (), "Data Inserted Successfully", Toast.LENGTH_SHORT).show ();
                        } else {
                            Toast.makeText (getApplicationContext (), "Data Insertion failed", Toast.LENGTH_SHORT).show ();

                        }


                        Intent i = new Intent (getApplicationContext (), Main2Activity.class);
                        startActivity (i);
                    }
                    else
                    {
                        et.setError ("Field is required");
                        et2.setError ("Field is required");
                    }
                }
                if (s1.getSelectedItemPosition ()==1) {
                    et5.setText ("");
                    et6.setText ("");
                    et7.setText ("");
                    et8.setText ("");
                    if (!isEmpty (et.getText ()) && !isEmpty (et2.getText ()) && !isEmpty (et3.getText ()) && !isEmpty (et4.getText ())) {

                        len_room = Float.parseFloat (et.getText ().toString ());
                        wid_room = Float.parseFloat (et2.getText ().toString ());
                        len_bed = Float.parseFloat (et3.getText ().toString ());
                        wid_bed = Float.parseFloat (et4.getText ().toString ());
                        len_cupboard=0;
                        wid_cupboard=0;
                        len_app=0;
                        wid_app=0;
                        Toast.makeText (getApplicationContext (), "" + len_room + wid_room + len_bed + wid_bed + len_cupboard + wid_cupboard + len_app + wid_app, Toast.LENGTH_SHORT).show ();
                        boolean result = myDB.insertData (len_room, wid_room, len_bed, wid_bed, len_cupboard, wid_cupboard, len_app, wid_app);
                        if (result == true) {
                            Toast.makeText (getApplicationContext (), "Data Inserted Successfully", Toast.LENGTH_SHORT).show ();
                        } else {
                            Toast.makeText (getApplicationContext (), "Data Insertion failed", Toast.LENGTH_SHORT).show ();
                        }


                        Intent i = new Intent (getApplicationContext (), Main2Activity.class);
                        startActivity (i);
                    } else {
                        if(isEmpty (et.getText ()))
                        {
                            et.setError ("Field is required");
                        }
                        if(isEmpty (et2.getText ()))
                        {
                            et2.setError ("Field is required");
                        }
                        if(isEmpty (et3.getText ()))
                        {
                            et3.setError ("Field is required");
                        }
                        if(isEmpty (et4.getText ()))
                        {
                            et4.setError ("Field is required");
                        }
                    }
                }
                if (s1.getSelectedItemPosition ()==2) {
                    et7.setText ("");
                    et8.setText ("");
                    if (!isEmpty (et.getText ()) && !isEmpty (et2.getText ()) && !isEmpty (et3.getText ()) && !isEmpty (et4.getText ()) && !isEmpty (et5.getText ()) && !isEmpty (et6.getText ())|| !isEmpty (et7.getText ())||!isEmpty (et8.getText ())) {

                        len_room = Float.parseFloat (et.getText ().toString ());
                        wid_room = Float.parseFloat (et2.getText ().toString ());
                        len_bed = Float.parseFloat (et3.getText ().toString ());
                        wid_bed = Float.parseFloat (et4.getText ().toString ());
                        len_cupboard = Float.parseFloat (et5.getText ().toString ());
                        wid_cupboard = Float.parseFloat (et6.getText ().toString ());
                        len_app=0;
                        wid_app=0;
                        Toast.makeText (getApplicationContext (), "" + len_room + wid_room + len_bed + wid_bed + len_cupboard + wid_cupboard + len_app + wid_app, Toast.LENGTH_SHORT).show ();
                        boolean result = myDB.insertData (len_room, wid_room, len_bed, wid_bed, len_cupboard, wid_cupboard, len_app, wid_app);
                        if (result == true) {
                            Toast.makeText (getApplicationContext (), "Data Inserted Successfully", Toast.LENGTH_SHORT).show ();
                        } else {
                            Toast.makeText (getApplicationContext (), "Data Insertion failed", Toast.LENGTH_SHORT).show ();

                        }
                        Intent i = new Intent (getApplicationContext (), Main2Activity.class);
                        startActivity (i);
                    } else {
                        if(isEmpty (et.getText ()))
                        {
                            et.setError ("Field is required");
                        }
                        if(isEmpty (et2.getText ()))
                        {
                            et2.setError ("Field is required");
                        }
                        if(isEmpty (et3.getText ()))
                        {
                            et3.setError ("Field is required");
                        }
                        if(isEmpty (et4.getText ()))
                        {
                            et4.setError ("Field is required");
                        }
                        if(isEmpty (et5.getText ()))
                        {
                            et5.setError ("Field is required");
                        }
                        if(isEmpty (et6.getText ()))
                        {
                            et6.setError ("Field is required");
                        }

                    }
                }
                if (s1.getSelectedItemPosition ()==3) {
                    if ((!isEmpty (et.getText ()) && !isEmpty (et2.getText ()) && !isEmpty (et3.getText ()) && !isEmpty (et4.getText ()) && !isEmpty (et5.getText ()) && !isEmpty (et6.getText ()) && !isEmpty (et7.getText ()) && !isEmpty (et8.getText ()))) {
                        len_room = Float.parseFloat (et.getText ().toString ());
                        wid_room = Float.parseFloat (et2.getText ().toString ());
                        len_bed = Float.parseFloat (et3.getText ().toString ());
                        wid_bed = Float.parseFloat (et4.getText ().toString ());
                        len_cupboard = Float.parseFloat (et5.getText ().toString ());
                        wid_cupboard = Float.parseFloat (et6.getText ().toString ());
                        len_app = Float.parseFloat (et7.getText ().toString ());
                        wid_app = Float.parseFloat (et8.getText ().toString ());
                        Toast.makeText (getApplicationContext (), "" + len_room + wid_room + len_bed + wid_bed + len_cupboard + wid_cupboard + len_app + wid_app, Toast.LENGTH_SHORT).show ();
                        boolean result = myDB.insertData (len_room, wid_room, len_bed, wid_bed, len_cupboard, wid_cupboard, len_app, wid_app);
                        if (result == true) {
                            Toast.makeText (getApplicationContext (), "Data Inserted Successfully", Toast.LENGTH_SHORT).show ();
                        } else {
                            Toast.makeText (getApplicationContext (), "Data Insertion failed", Toast.LENGTH_SHORT).show ();

                        }


                        Intent i = new Intent (getApplicationContext (), Main2Activity.class);
                        startActivity (i);

                    } else {
                        if(isEmpty (et.getText ()))
                        {
                            et.setError ("Field is required");
                        }
                        if(isEmpty (et2.getText ()))
                        {
                            et2.setError ("Field is required");
                        }
                        if(isEmpty (et3.getText ()))
                        {
                            et3.setError ("Field is required");
                        }
                        if(isEmpty (et4.getText ()))
                        {
                            et4.setError ("Field is required");
                        }
                        if(isEmpty (et5.getText ()))
                        {
                            et5.setError ("Field is required");
                        }
                        if(isEmpty (et6.getText ()))
                        {
                            et6.setError ("Field is required");
                        }
                        if(isEmpty (et7.getText ()))
                        {
                            et7.setError ("Field is required");
                        }
                        if(isEmpty (et8.getText ()))
                        {
                            et8.setError ("Field is required");
                        }
                    }
                }


                /* else {




                  /*  wid_room = Float.parseFloat(et2.getText().toString());
                    len_bed = Float.parseFloat(et3.getText().toString());
                    wid_bed = Float.parseFloat(et4.getText().toString());
                    if((et5.getVisibility() == View.GONE) && (et6.getVisibility() == View.GONE))
                    {
                        len_cupboard = 0;
                        wid_cupboard = 0;
                    }
                    else
                    {
                        len_cupboard = Float.parseFloat(et5.getText().toString());
                        wid_cupboard = Float.parseFloat(et6.getText().toString());
                    }
                    if((et7.getVisibility() == View.GONE) && (et8.getVisibility() == View.GONE))
                    {
                        len_app=0;
                        wid_app=0;
                    }
                    else
                    {
                        len_app = Float.parseFloat(et7.getText().toString());
                        wid_app = Float.parseFloat(et8.getText().toString());
                    }

*/

                /*len_room = 20;
                wid_room = 30;//Float.parseFloat(et2.getText().toString());
                len_bed =9;// Float.parseFloat(et3.getText().toString());
                wid_bed =6;// Float.parseFloat(et4.getText().toString());
                len_cupboard = 6;// Float.parseFloat(et5.getText().toString());
                wid_cupboard =2;// Float.parseFloat(et6.getText().toString());
                len_app =5;// Float.parseFloat(et7.getText().toString());
                wid_app =5;// Float.parseFloat(et8.getText().toString());*/


            }
        });
    }

    private void ResetButton() {
        reset.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                et.setText ("");
                et2.setText ("");
                et3.setText ("");
                et4.setText ("");
                et5.setText ("");
                et6.setText ("");
                et7.setText ("");
                et8.setText ("");
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder (this);
        builder.setMessage ("Exit App")
                .setTitle ("Exit")
                .setPositiveButton ("Ok", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish ();
                    }
                })
                .setNegativeButton ("Cancel", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create ();
        builder.show ();


    }
}
