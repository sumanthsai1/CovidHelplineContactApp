package com.example.ex;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL=1;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE=1;
    Button dial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dial = findViewById(R.id.dial);
        if(checkPermission(Manifest.permission.CALL_PHONE)){
            dial.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},MAKE_CALL_PERMISSION_REQUEST_CODE);
        }

    }

    public void onDialButton(View v) {
        String mobileNumber = "+911123978046";
        if(checkPermission(Manifest.permission.CALL_PHONE)){

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL); // Action for what intent called for
            intent.setData(Uri.parse("tel: " + mobileNumber)); // Data with intent respective action on intent
            startActivity(intent);}
        else{
            Toast.makeText(MainActivity.this,"permission denied",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkPermission(String permission){
        return ContextCompat.checkSelfPermission(this,permission) == PERMISSION_GRANTED;
    }
    public void OnRequestPermissionResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        switch (requestCode)
        {
            case MAKE_CALL_PERMISSION_REQUEST_CODE:
                if (grantResults.length>0 && (grantResults[0]== PERMISSION_GRANTED)){
                    dial.setEnabled(true);
                }
        }
    }
}




