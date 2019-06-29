package app.mrquan.notificationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("数据", "onResume: ");
        Intent intent = getIntent();
        long intExtra = intent.getLongExtra("time",-1);
        Toast.makeText(getApplicationContext(),"参数传递："+intExtra,Toast.LENGTH_LONG).show();
    }
}