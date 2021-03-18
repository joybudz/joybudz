package com.zuoyou.joybudzdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zuoyou.joybudzaar.AppContext;

public class MainActivity extends AppCompatActivity implements AppContext.IResultCallback {
    private Button mBtClick;
    private Button mBtClick1;
    private Button mBtClick2;
    private Button mBtClick3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating an API object
        AppContext joybudz = AppContext.getInstance();
        joybudz.initBle(this,this);

        //Connection and initialization of earphone
        mBtClick = (Button) findViewById(R.id.btnTest);
        mBtClick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                joybudz.initJoybudz();
                if(!AppContext.bConnectBle){
                    Toast.makeText(MainActivity.this, "Just a moment, the headphones are connected...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Successfully initialized！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Reading Euler's angle
        mBtClick1 = (Button) findViewById(R.id.btnTest1);
        mBtClick1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, AppContext.strEulerData, Toast.LENGTH_SHORT).show();
            }
        });
        //Reading Quaternion
        mBtClick2 = (Button) findViewById(R.id.btnTest2);
        mBtClick2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, AppContext.strQuatData, Toast.LENGTH_SHORT).show();
            }
        });
        //Read headset reminder settings
        mBtClick3 = (Button) findViewById(R.id.btnTest3);
        mBtClick3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                joybudz.getTRData();
                Toast.makeText(MainActivity.this, AppContext.strTRData, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sendEuler(String sEuler) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("=====SP_Client----", sEuler);
//                UnityPlayer.UnitySendMessage("AndroidManager", "GetEuler", sEuler);
//                Cocos2dxJavascriptJavaBridge.evalString("window.GetEuler(\""+sEuler+"\");");
            }
        });
    }

    @Override
    public void sendQuat(String sQuat) {
        Log.e("=====SP_Client----", sQuat);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("=====SP_Client----", sQuat);
//                UnityPlayer.UnitySendMessage("AndroidManager", "GetQuaternion", sQuat);
//                Cocos2dxJavascriptJavaBridge.evalString("window.GetQuaternion(\""+sQuat+"\");");
            }
        });
    }
}