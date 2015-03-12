package ru.automize.videotest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startWebServer(View view) {
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WebServer.class);
        startService(intent);

        updateServiceStatus();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void refreshWebServer(View view) {
        updateServiceStatus();
    }

    private void updateServiceStatus() {
        TextView text = (TextView) findViewById(R.id.server_status);
        text.setText(isMyServiceRunning(WebServer.class) ? "Running" : "Stopped");
    }

    public void stopWebServer(View view) {
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WebServer.class);
        stopService(intent);

        updateServiceStatus();
    }

}
