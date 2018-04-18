package com.teme.hieu.temecontrol;

import android.app.Notification;
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
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Main extends AppCompatActivity {
    Button exid;
    ImageView turnLeft,turnRight,Phanh,xe,btnRun,btnLed,btnAttack;
    Animation animLeft;
    Animation animRight;
    String address = null;
    BluetoothAdapter myBlue = null;
    BluetoothSocket mySocket = null;
    private boolean isBTconnected = false;
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

        xe = findViewById(R.id.car);
        turnLeft = findViewById(R.id.btnLeft);
        turnRight = findViewById(R.id.btnRight);
        btnRun = findViewById(R.id.btnRun);
        btnLed = findViewById(R.id.btnLed);
        btnAttack = findViewById(R.id.btnAttack);
        Phanh = findViewById(R.id.btnPhanh);

        animLeft = AnimationUtils.loadAnimation(Main.this,R.anim.roteeleft);
        animRight = AnimationUtils.loadAnimation(Main.this, R.anim.rotee);
        exid = findViewById(R.id.exix);
        exid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });

        turnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    try{
                    xe.startAnimation(animLeft);
                    mySocket.getOutputStream().write("TF".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}

                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    try{
                        mySocket.getOutputStream().write("END".getBytes());}
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
                        mySocket.getOutputStream().write("TR".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}

                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    try{
                        mySocket.getOutputStream().write("END".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                }
                return false;
            }
        });
        btnRun.setOnTouchListener(new View.OnTouchListener() {
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
                        mySocket.getOutputStream().write("STOP".getBytes());}
                    catch (IOException e)
                    { Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_SHORT).show();}
                }
                return false;
            }
        });


    }
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
