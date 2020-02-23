package com.example.mypc.homelayoutgenerator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class Main3Activity extends AppCompatActivity {
    Rectangle Rec;
    TextView t1,t2,t3,t4,t6,t7,t8;
    public int[] RoomArray;
    int height;
    int PhoneHeight;
    int width;
    int Phonewidth;
    HorizontalScrollView s;
    EditText editText;
    Button scale,changeValues;
    int scalingFactor=30;
    LinearLayout l;
    SeekBar sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //System.out.print (b+" "+c+" "+a);
        s = findViewById(R.id.scrollView);
        l = findViewById(R.id.linearlayout);
        scale = findViewById(R.id.button5);
        editText = findViewById(R.id.scale);
        sb = findViewById(R.id.seekBar);
        t1 = findViewById(R.id.textView15);
        t2 = findViewById(R.id.textView14);
        t3 = findViewById(R.id.textView11);
        t4 = findViewById(R.id.textView13);

        t6 = findViewById(R.id.textView2);
        t7 = findViewById(R.id.textView9);
        t8 = findViewById(R.id.textView10);
        t6.setText (MainActivity.n1);
        t7.setText (MainActivity.n2);
        t8.setText (MainActivity.n3);

        changeValues = findViewById(R.id.button6);
        changeValues.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (getApplicationContext (),MainActivity.class);
                startActivity (intent);
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Phonewidth=width = size.x;
        PhoneHeight= height = size.y;
        //System.out.print(height+" "+width);
        Intent intent = getIntent();
        RoomArray = intent.getIntArrayExtra("Array1");
        t1.setText ((RoomArray[0] + "*" + RoomArray[1]));
        t2.setText ((RoomArray[2] + "*" + RoomArray[3]));
        t3.setText ((RoomArray[4] + "*" + RoomArray[5]));
        t4.setText ((RoomArray[6] + "*" + RoomArray[7]));
        int RoomArraySize = RoomArray.length;
        /*for (int i = 0; i < RoomArraySize; i++) {
            System.out.println(RoomArray[i]);
        }*/
        seekBarActivity();
        scaleButton();
        Rectangle Rec1 = new Rectangle(getApplicationContext ());
        l.addView(Rec1);


    }

    private void scaleButton() {
        scale.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty (editText.getText ()))
                {
                    editText.setError (null);
                }
                else
                {
                    scalingFactor=Integer.parseInt (String.valueOf (editText.getText ()));
                    Rectangle Rec = new Rectangle(getApplicationContext ());
                    l.removeAllViews ();
                    l.addView(Rec);
                }

            }
        });
    }

    private void seekBarActivity() {
        sb.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText (getApplicationContext (),String.valueOf (progress),Toast.LENGTH_SHORT).show ();
                if(sb.getProgress ()!=0)
                {
                    scalingFactor=sb.getProgress ();
                    Rectangle Rec = new Rectangle(getApplicationContext ());
                    l.removeAllViews ();
                    l.addView(Rec);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText (getApplicationContext (),String.valueOf (sb.getProgress ()),Toast.LENGTH_SHORT).show ();
                if(sb.getProgress ()!=0)
                {
                    scalingFactor=sb.getProgress ();
                    Rectangle Rec = new Rectangle(getApplicationContext ());
                    l.removeAllViews ();
                    l.addView(Rec);
                }

            }
        });
    }

    public class Rectangle extends View {
        public String b=MainActivity.bedPosition;
        public String c=MainActivity.CupboardPosition;
        public String a=MainActivity.AppliancePosition;
        public String r1=MainActivity.n1;
        public String r2=MainActivity.n2;
        public String r3=MainActivity.n3;
        Paint paint = new Paint();
        int[] list;
        int left, top, bottom, right;
        int[] Room_Pixel;
        int[] Bed_Pixel;
        int[] Cup_pixel;
        int[] App_pixel;
        int Room_len = RoomArray[0], Room_wid = RoomArray[1];
        int Bed_len = RoomArray[2], Bed_wid = RoomArray[3];
        int cup_len = RoomArray[4], cup_wid = RoomArray[5];
        int app_len = RoomArray[6], app_wid = RoomArray[7];


        public Rectangle(Context context) {
            super(context);


        }

        @Override
        public void onDraw(Canvas canvas) {


            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(3);
            Room_Pixel = setR(Room_len, Room_wid);
            for (int i = 0; i < Room_Pixel.length; i++) {
                Room_Pixel[i] = Room_Pixel[i] * scalingFactor;
            }
            canvas.drawRect(Room_Pixel[0], Room_Pixel[1], Room_Pixel[2], Room_Pixel[3], paint);



            paint.setStrokeWidth(3);
            paint.setColor(Color.CYAN);
            Bed_Pixel = setBed(Bed_len, Bed_wid);
            for (int i = 0; i < Bed_Pixel.length; i++) {
                Bed_Pixel[i] = Bed_Pixel[i] * scalingFactor;
            }
            canvas.drawRect(Bed_Pixel[0], Bed_Pixel[1], Bed_Pixel[2], Bed_Pixel[3], paint);


            paint.setColor(Color.YELLOW);
            Cup_pixel = setCup(cup_len, cup_wid);
            for (int i = 0; i < Cup_pixel.length; i++) {
                Cup_pixel[i] = Cup_pixel[i] * scalingFactor;
            }
            canvas.drawRect(Cup_pixel[0], Cup_pixel[1], Cup_pixel[2], Cup_pixel[3], paint);




            paint.setColor(Color.GREEN);
            App_pixel = setApp(app_len, app_wid);
            for (int i = 0; i < App_pixel.length; i++) {
                App_pixel[i] = App_pixel[i] * scalingFactor;
            }
            canvas.drawRect(App_pixel[0], App_pixel[1], App_pixel[2], App_pixel[3], paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("Room",Room_Pixel[1],Room_Pixel[3]+50,paint);
            canvas.drawText(""+Room_len+"*"+Room_wid,Room_Pixel[1],Room_Pixel[3]+100,paint);

            paint.setTextSize(50);
            paint.setColor(Color.BLACK);
            if(Bed_len!=0&&Bed_wid!=0)
            {
                canvas.drawText(r1+" "+Bed_len+"*"+Bed_wid,Bed_Pixel[0],Bed_Pixel[1]+50,paint);
            }

            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            if(cup_len!=0&&cup_wid!=0)
            {
                canvas.drawText(r2,Cup_pixel[0],Cup_pixel[1]+40,paint);
                canvas.drawText(" "+cup_len+"*"+cup_wid,Cup_pixel[0],Cup_pixel[1]+90,paint);
            }

            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            if(app_len!=0&&app_wid!=0)
            {
                canvas.drawText(r3,App_pixel[0],App_pixel[1]+40,paint);
                canvas.drawText(""+app_len+"*"+app_wid,App_pixel[0]+50,App_pixel[1]+90,paint);
            }

        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            //DisplayMetrics dm =getWindowManager()
            int height = Phonewidth; // should be calculated based on the content
            int width = PhoneHeight; // should be calculated based on the content
            //System.out.print(PhoneHeight+" "+Phonewidth);
            setMeasuredDimension(width, height);
        }

        public int[] setR(int len, int wid) {
            left = 0;
            top = 0;
            right = len;
            bottom = wid;
            //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

            return new int[]{left, top, right, bottom};
        }


        public int[] setBed(float len, float wid) {

            switch (b) {
                case "topleft":
                    left = 0;
                    top = 0;
                    right = (int) len;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "topcenter":
                    left = Room_len / 4;
                    top = 0;
                    right = (int) len + Room_len / 4;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "topright":
                    left = Room_len - (int) len;
                    top = 0;
                    right = Room_len;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "leftcenter":
                    left = 0;
                    top = Room_wid / 4;
                    right = (int) len;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "center":
                    left = Room_len / 4;
                    top = Room_wid / 4;
                    right = (int) len + Room_len / 4;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "rightcenter":
                    left = Room_len - (int) len;
                    top = Room_wid / 4;
                    right = Room_len;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomleft":
                    left = 0;
                    top = Room_wid - (int) wid;
                    right = (int) len;
                    bottom = Room_wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomcenter":
                    left = Room_len / 4;
                    top = Room_wid - (int) wid;
                    right = Room_len / 4 + (int) len;
                    bottom = Room_wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomright":
                    left = Room_len;
                    top = Room_wid;
                    right = Room_len - (int) len;
                    bottom = Room_wid - (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                default:
                    return new int[]{left, top, right, bottom};
            }




            /*left = 0;
            top = 0;
            right = (int) len;
            bottom = (int) wid;
            //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

            return new int[]{left, top, right, bottom};*/

        }

        public int[] setCup(float len, float wid) {
            switch (c) {
                case "topleft":
                    left = 0;
                    top = 0;
                    right = (int) len;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "topcenter":
                    left = Room_len / 4;
                    top = 0;
                    right = (int) len + Room_len / 4;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "topright":
                    left = Room_len - (int) len;
                    top = 0;
                    right = Room_len;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "leftcenter":
                    left = 0;
                    top = Room_wid / 4;
                    right = (int) len;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "center":
                    left = Room_len / 4;
                    top = Room_wid / 4;
                    right = (int) len + Room_len / 4;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "rightcenter":
                    left = Room_len - (int) len;
                    top = Room_wid / 4;
                    right = Room_len;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomleft":
                    left = 0;
                    top = Room_wid - (int) wid;
                    right = (int) len;
                    bottom = Room_wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomcenter":
                    left = Room_len / 4;
                    top = Room_wid - (int) wid;
                    right = Room_len / 4 + (int) len;
                    bottom = Room_wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomright":
                    left = Room_len;
                    top = Room_wid;
                    right = Room_len - (int) len;
                    bottom = Room_wid - (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                default:
                    return new int[]{left, top, right, bottom};
            }

            /*left = (int) ( Room_len-wid);
            top = (int)0;
            right = (int) Room_len;
            bottom = (int) ((int)len);
            //System.out.println(" "+left+" "+top+" "+right+" "+bottom);
            return new int[]{left, top, right, bottom};*/
        }

        public int[] setApp(float len, float wid) {
            switch (a) {
                case "topleft":
                    left = 0;
                    top = 0;
                    right = (int) len;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "topcenter":
                    left = Room_len / 4;
                    top = 0;
                    right = (int) len + Room_len / 4;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "topright":
                    left = Room_len - (int) len;
                    top = 0;
                    right = Room_len;
                    bottom = (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "leftcenter":
                    left = 0;
                    top = Room_wid / 4;
                    right = (int) len;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "center":
                    left = Room_len / 4;
                    top = Room_wid / 4;
                    right = (int) len + Room_len / 4;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "rightcenter":
                    left = Room_len - (int) len;
                    top = Room_wid / 4;
                    right = Room_len;
                    bottom = (int) wid + Room_wid / 4;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomleft":
                    left = 0;
                    top = Room_wid - (int) wid;
                    right = (int) len;
                    bottom = Room_wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomcenter":
                    left = Room_len / 4;
                    top = Room_wid - (int) wid;
                    right = Room_len / 4 + (int) len;
                    bottom = Room_wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                case "bottomright":
                    left = Room_len;
                    top = Room_wid;
                    right = Room_len - (int) len;
                    bottom = Room_wid - (int) wid;
                    //System.out.println(" "+left+" "+top+" "+right+" "+bottom);

                    return new int[]{left, top, right, bottom};
                default:
                    return new int[]{left, top, right, bottom};
            }

           /* left = (int) Room_len - (int) len;
            top = (int) Room_wid - (int) wid;
            right = (int) Room_len;
            bottom = (int) Room_wid;
           // System.out.println(" "+left+" "+top+" "+right+" "+bottom);

            return new int[]{left, top, right, bottom};*/
        }

    }
}
