package com.tac.tacapplication;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.app.Activity;
import android.widget.Button;
import android.view.MenuItem;


public class Map extends Activity {

    private DrawerLayout mDrawerLayout;
    private Drawer myDrawer = new Drawer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        WebView map = (WebView) findViewById(R.id.religion);
        map.getSettings().setBuiltInZoomControls(true);
        map.loadUrl("file:///android_res/drawable/religion.png");
        map.scrollTo(0, 0);
    }
}