package com.example.mypc.homelayoutgenerator;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Main2Activity extends AppCompatActivity {
    DataBaseHelper mydb;
    Button b2,b3,showall,export;
    TextView t;
    Cursor cursor;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b2 = findViewById(R.id.button2);
        showall = findViewById(R.id.showall);
        b3 = findViewById(R.id.button3);
        export = findViewById(R.id.button4);
        t = findViewById(R.id.textView3);
        mydb = new DataBaseHelper(this);
        final Cursor res = mydb.getAllData();
        cursor=mydb.getExcelData ();
        final int[] arr=new int[res.getColumnCount()];


        export.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                        // Check whether this app has write external storage permission or not.
                        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        // If do not grant write external storage permission.
                        if(writeExternalStoragePermission!= PackageManager.PERMISSION_GRANTED)
                        {
                            // Request user to grant write external storage permission.

                            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                        else
                        {
                            File sd = Environment.getExternalStorageDirectory ();
                            String csvFile = "myData.xls";
                            t.setText (sd.toString ());
                            File directory = new File(sd.getAbsolutePath());
                            //create directory if not exist
//                            if (!directory.isDirectory()) {
//                                directory.mkdirs();
//                            }
                            try {

                                //file path
                                File file = new File (directory, csvFile);
                                WorkbookSettings wbSettings = new WorkbookSettings();
                                wbSettings.setLocale(new Locale ("en", "EN"));
                                WritableWorkbook workbook;
                                workbook = Workbook.createWorkbook(file, wbSettings);
                                //Excel sheet name. 0 represents first sheet
                                WritableSheet sheet = workbook.createSheet("userList", 0);

                                // column and row
                                sheet.addCell(new Label(0, 0, "RoomLength"));
                                sheet.addCell(new Label (1, 0, "RoomWidth"));
                                sheet.addCell(new Label (2, 0, "BedLength"));
                                sheet.addCell(new Label (3, 0,"BedWidth"));
                                sheet.addCell(new Label (4, 0, "CupboardLength"));
                                sheet.addCell(new Label (5, 0, "CupboardWidth"));
                                sheet.addCell(new Label (6, 0, "AppliancesLength"));
                                sheet.addCell(new Label (7, 0, "AppliancesWidth"));
                                if (cursor.moveToFirst ()) {
                                    do {
                                        System.out.print (cursor.getColumnCount ()+" "+cursor.getColumnIndex ("LEN_ROOM"));
                                        String LenRoom = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (0)));
                                        String WidRoom = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (1)));
                                        String LenBed = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (2)));
                                        String WidBed = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (3)));
                                        String LenCup = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (4)));
                                        String WidCup = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (5)));
                                        String LenApp = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (6)));
                                        String WidApp = cursor.getString(cursor.getColumnIndex(cursor.getColumnName (7)));
                                        int i = cursor.getPosition() + 1;
                                        sheet.addCell(new Label(0, i, LenRoom));
                                        sheet.addCell(new Label(1, i, WidRoom));
                                        sheet.addCell(new Label(2, i, LenBed));
                                        sheet.addCell(new Label(3, i, WidBed));
                                        sheet.addCell(new Label(4, i, LenCup));
                                        sheet.addCell(new Label(5, i, WidCup));
                                        sheet.addCell(new Label(6, i, LenApp));
                                        sheet.addCell(new Label(7, i, WidApp));
                                    } while (cursor.moveToNext());
                                }

                                //closing cursor

                                workbook.write();
                                workbook.close();
                                Toast.makeText(getApplication(),"Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();

                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }


        }});







        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllData();
                if(res.getCount() == 0)
                {
                    // show msg
                    showMessage("Error","Nothing Found");
                    return;
                }

                StringBuilder buffer = new StringBuilder();
               while(res.moveToNext())
                {
                    buffer.append("Length of Room : ").append(res.getFloat(0)).append("\n");
                    buffer.append("Width of Room : ").append(res.getFloat(1)).append("\n");
                    buffer.append("Length of Bed : ").append(res.getFloat(2)).append("\n");
                    buffer.append("Width of Bed : ").append(res.getFloat(3)).append("\n");
                    buffer.append("Length of CupBoard : ").append(res.getFloat(4)).append("\n");
                    buffer.append("Width of CupBoard : ").append(res.getFloat(5)).append("\n");
                    buffer.append("Length of Appliance : ").append(res.getFloat(6)).append("\n");
                    buffer.append("Width of Appliance : ").append(res.getFloat(7)).append("\n");


                }

                // show all data
                showMessage("Data", buffer.toString());

            }

        });
        viewAll ();
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (res.getCount() > 0) {
                    res.moveToLast();
                    arr[0]= (int) res.getFloat(0);
                    arr[1]=(int)res.getFloat(1);
                    arr[2]=(int)res.getFloat(2);
                    arr[3]=(int)res.getFloat(3);
                    arr[4]=(int)res.getFloat(4);
                    arr[5]=(int)res.getFloat(5);
                    arr[6]=(int)res.getFloat(6);
                    arr[7]=(int)res.getFloat(7);
                    for (int j = 0; j < res.getColumnCount(); j++) {
                        System.out.println(arr[j]);
                    }
                }
                Intent i = new Intent(getApplicationContext(), Main3Activity.class);
                i.putExtra("Array1",arr );
                startActivity(i);
            }
        });
    }
    public void viewAll()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getAllData();
                if(res.getCount() == 0)
                {
                    // show msg
                    showMessage("Error","Nothing Found");
                    return;
                }

                StringBuilder buffer = new StringBuilder();
               /* while(res.moveToNext())
                {
                    buffer.append("Length of Room : " + res.getFloat (0) + "\n");
                    buffer.append("Width of Room : " + res.getFloat (1) + "\n");
                    buffer.append("Length of Bed : " + res.getFloat (2) + "\n");
                    buffer.append("Width of Bed : " + res.getFloat (3) + "\n");
                    buffer.append("Length of CupBoard : " + res.getFloat (4) + "\n");
                    buffer.append("Width of CupBoard : " + res.getFloat (5) + "\n");
                    buffer.append("Length of Appliance : " + res.getFloat (6) + "\n");
                    buffer.append("Width of Appliance : " + res.getFloat (7) + "\n");


                }*/
                res.moveToLast ();
                buffer.append("Length of Room : ").append(res.getFloat(0)).append("\n");
                buffer.append("Width of Room : ").append(res.getFloat(1)).append("\n");
                buffer.append("Length of Bed : ").append(res.getFloat(2)).append("\n");
                buffer.append("Width of Bed : ").append(res.getFloat(3)).append("\n");
                buffer.append("Length of CupBoard : ").append(res.getFloat(4)).append("\n");
                buffer.append("Width of CupBoard : ").append(res.getFloat(5)).append("\n");
                buffer.append("Length of Appliance : ").append(res.getFloat(6)).append("\n");
                buffer.append("Width of Appliance : ").append(res.getFloat(7)).append("\n");

                // show all data
                showMessage("Data", buffer.toString());
            }
        });

    }

    public void showMessage(String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton ("OK", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
