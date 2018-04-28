package com.teme.hieu.temecontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Main extends AppCompatActivity {
    Button exid;
    TextView txtvSo;
    ImageView turnLeft,turnRight,xe,btnRunUp,btnPhanh,btnBurst,btnOne,btnTwo,btnNang,btnLED;
    Animation animLeft;
    Animation animRight;
    String address = null;
    BluetoothAdapter myBlue = null;
    BluetoothSocket mySocket = null;
    private boolean isBTconnected = false;
    public boolean nang = false;
    public boolean led = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent nhan = getIntent();
        address = nhan.getStringExtra(Second.EXTRA_ADDRESS);
        connectBT();
        //Anh xa
        btnLED = findViewById(R.id.btnLED);
        txtvSo = findViewById(R.id.txtvSo);
        xe = findViewById(R.id.car);
        turnLeft = findViewById(R.id.btnLeft);
        turnRight = findViewById(R.id.btnRight);
        btnRunUp = findViewById(R.id.btnRunUp);
        btnPhanh = findViewById(R.id.btnPhanh);
        btnBurst = findViewById(R.id.btnBurst);
        btnPhanh = findViewById(R.id.btnPhanh);
        btnNang = findViewById(R.id.btnNang);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);

        animLeft = AnimationUtils.loadAnimation(Main.this,R.anim.roteeleft);
        animRight = AnimationUtils.loadAnimation(Main.this, R.anim.rotee);
        exid = findViewById(R.id.exix);
        exid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),First.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
        btnNang.setOnClickListener(myClick);
        btnOne.setOnClickListener(myClick);
        btnTwo.setOnClickListener(myClick);
        btnLED.setOnClickListener(myClick);
        turnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    try{
                    xe.startAnimation(animLeft);
                    mySocket.getOutputStream().write("LEFT".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                     return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    try{
                        mySocket.getOutputStream().write("ENDL".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                }
                return false;
            }
        });
        turnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    try{
                        xe.startAnimation(animRight);
                        mySocket.getOutputStream().write("RIGHT".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                     return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    try{
                        mySocket.getOutputStream().write("ENDR".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                }
                return false;
            }
        });
        btnRunUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    try{
                        mySocket.getOutputStream().write("RUN".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    try{
                        mySocket.getOutputStream().write("ENDU".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                }
                return false;
            }
        });
         btnPhanh.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if (event.getAction() == MotionEvent.ACTION_DOWN)
                 {
                     try{
                         mySocket.getOutputStream().write("PHANH".getBytes());}
                     catch (IOException e)
                     { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                     return true;
                 }
                 if (event.getAction() == MotionEvent.ACTION_UP)
                 {
                     try{
                         mySocket.getOutputStream().write("ENDP".getBytes());}
                     catch (IOException e)
                     { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                 }
                 return false;
             }
         });
         btnBurst.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 if (event.getAction() == MotionEvent.ACTION_DOWN)
                 {
                     try{
                         mySocket.getOutputStream().write("BURST".getBytes());}
                     catch (IOException e)
                     { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                     return true;
                 }
                 if (event.getAction() == MotionEvent.ACTION_UP)
                 {
                     try{
                         mySocket.getOutputStream().write("ENDB".getBytes());}
                     catch (IOException e)
                     { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                 }
                 return false;
             }
         });
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnLED:
                {
                    if(!led){
                        try{
                            mySocket.getOutputStream().write("LEDO".getBytes());
                            led = true;
                            btnNang.setImageResource(R.drawable.ledoff);
                        }
                        catch (IOException e)
                        { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    }
                    else
                    {
                        try{
                            mySocket.getOutputStream().write("LEDF".getBytes());
                            led = false;
                            btnNang.setImageResource(R.drawable.ledon);
                        }
                        catch (IOException e)
                        { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    }
                    break;
                }

                case R.id.btnNang:
                {
                    if(!nang){
                        try{
                            mySocket.getOutputStream().write("NANG".getBytes());
                            nang = true;
                            btnNang.setImageResource(R.drawable.upped);
                        }
                        catch (IOException e)
                        { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    }
                    else
                    {
                        try{
                            mySocket.getOutputStream().write("HA".getBytes());
                            nang = false;
                            btnNang.setImageResource(R.drawable.nang);
                        }
                        catch (IOException e)
                        { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    }
                    break;
                }
                case R.id.btnOne:
                {
                    try{
                        mySocket.getOutputStream().write("ONE".getBytes());
                        txtvSo.setText(R.string.one);
                    }
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    break;
                }
                case R.id.btnTwo:
                {
                    try{
                        mySocket.getOutputStream().write("TWO".getBytes());
                        txtvSo.setText(R.string.two);
                    }
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                    break;
                }
            }
        }
    };
    public void connectBT ()
    {
        boolean Connected = true;
        try {
            if (mySocket == null || !isBTconnected) {
                myBlue = BluetoothAdapter.getDefaultAdapter();
                BluetoothDevice takeControl = myBlue.getRemoteDevice(address);
                mySocket = takeControl.createInsecureRfcommSocketToServiceRecord(myUUID);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                mySocket.connect();
            }
        } catch (IOException e) {
            Connected = false;
        }
        if (!Connected) {
            msg("Kết nối thất bại, hãy thử lại.");
            finish();
        } else {
            msg("Đã kết nối");
            isBTconnected = true;
        }
    }
    public void msg(String a)
    {
        Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
    }
}
