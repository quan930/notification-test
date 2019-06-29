package app.mrquan.notificationtest;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            Toast.makeText(getApplicationContext(),"aaa",Toast.LENGTH_SHORT).show();
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    public void test(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        long timeMillis = System.currentTimeMillis();
        intent.putExtra("time",timeMillis);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Log.d("数据传递", "test: "+intent.getLongExtra("time",-1));
        //第二个参数设为消息类型notificationId 否则activity数据无法传递
        PendingIntent pendingIntent = PendingIntent.getActivity(this,(int) timeMillis,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(this, "chat")
                    .setContentTitle("收到一条聊天消息")
                    .setContentText("今天中午吃什么？"+timeMillis)
                    .setWhen(timeMillis)
                    .setSmallIcon(R.drawable.ttt)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.fff))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .build();
            manager.notify((int) timeMillis, notification);
        }else {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle("标题")
                    .setContentText("hello world"+timeMillis)
                    .setTicker("通知")
                    .setWhen(timeMillis)
                    .setSmallIcon(R.drawable.ttt)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.fff))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
//                    .setSound(Uri.parse("android.resource://" + getPackageName() + "/"+R.raw.llll))
//                    .setVibrate(new long[]{0,1000,0,1000})
//                    .setLights(Color.GREEN,1000,1000)
                    .build();
            manager.notify((int) timeMillis,notification);
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
