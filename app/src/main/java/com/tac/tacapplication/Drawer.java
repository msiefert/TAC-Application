package com.tac.tacapplication;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import static com.tac.tacapplication.R.layout.label;
import static com.tac.tacapplication.R.layout.nav;
import static com.tac.tacapplication.R.layout.call;


public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private PopupWindow pwindo;
    private WebView floor;
    private EditText rEdit;
    private EditText lEdit;
    private boolean first = true;

    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //new Thread(new Runnable(){
        //    public void run(){
        //        try {
        //            Socket mySocket = new Socket(serverAddress,8080);
        //            Log.e("YESSSSSSS","It has worked");
        //        }
        //        catch (IOException e) {
        //            Log.e("MEHHHHH", "It kinda worked");
        //        }
        //    }
        //}).start();

        floor = (WebView) findViewById(R.id.floor); //floor is the webview displaying the floor plan
        floor.getSettings().setBuiltInZoomControls(true);
        floor.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        floor.getSettings().setJavaScriptEnabled(true);
        floor.loadUrl(getString(R.string.ip));                        //point it to the server
        floor.setBackgroundColor(0x00000000);                         //allows floor plan to be seen






//        final View touchView = findViewById(R.id.religion); can intercept touches, obsolete
//        final TextView text = (TextView) findViewById(R.id.text);
//        touchView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                text.setText("Touch coordinates : " +
//                        valueOf((event.getX() + map.getScrollX())/map.getScale()) + "x" +
//                        valueOf((event.getY() + map.getScrollY())/map.getScale()));
//                float xCor = (event.getX() + map.getScrollX())/map.getScale();
//                float yCor = (event.getY() + map.getScrollY())/map.getScale();
//                if(xCor > 165 && yCor > 131 && xCor < 272 && yCor < 226)
//                    text.setText("Room 106");
//                else {
//                    text.setText("Undefined");
//                }
//                return false;
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final WebView menuPic = (WebView) findViewById(R.id.menuHeader);      //icon at top of menu
        menuPic.getSettings().setJavaScriptEnabled(true);
        menuPic.loadUrl(getString(R.string.ip));                    //points it to the server
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        LayoutInflater inflater = (LayoutInflater) Drawer.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        if (id == R.id.nav_clear) {                                  //If Clear is chosen from menu
            View layout = inflater.inflate(R.layout.clear,                  //Create popup for clear
                    (ViewGroup) findViewById(R.id.popup_clear));
            pwindo = new PopupWindow(layout, 900, 400, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                                                        //Create Buttons and assign their listeners
            Button cancel = (Button) layout.findViewById(R.id.clear_cancel);
            cancel.setOnClickListener(cancel_button_click_listener);
            
            Button Clear = (Button) layout.findViewById(R.id.clear_clear);
            Clear.setOnClickListener(Clear_button_click_listener);
                                                     //Tracks text entered into Clear's room field
            rEdit = (EditText) layout.findViewById(R.id.RoomToClear);


        } else if (id == R.id.nav_navigate) {                     //If navigate is chosen from menu
            View layout = inflater.inflate(nav,                         //Create popup for navigate
                    (ViewGroup) findViewById(R.id.popup_nav));
            pwindo = new PopupWindow(layout, 900, 600, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                                                                //Create Buttons and their listeners
            Button cancel = (Button) layout.findViewById(R.id.nav_cancel);
            cancel.setOnClickListener(cancel_button_click_listener);
            
            Button Go = (Button) layout.findViewById(R.id.nav_go);
            Go.setOnClickListener(Go_button_click_listener);



        } else if (id == R.id.nav_label) {                          //If label is chosen from menu
            View layout = inflater.inflate(label,                       //Create the popup for label
                    (ViewGroup) findViewById(R.id.popup_label));
            pwindo = new PopupWindow(layout, 1100, 700, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                                                            //Create Buttons and assign listeners
            Button cancel = (Button) layout.findViewById(R.id.label_cancel);
            cancel.setOnClickListener(cancel_button_click_listener);

            Button Injuries = (Button) layout.findViewById(R.id.Injuries);
            Injuries.setOnClickListener(Injuries_button_click_listener);

            Button Danger = (Button) layout.findViewById(R.id.Danger);
            Danger.setOnClickListener(Danger_button_click_listener);

            Button Civilians = (Button) layout.findViewById(R.id.Civilians);
            Civilians.setOnClickListener(Civilians_button_click_listener);
                                                    //Tracks text entered into label's room field
            lEdit = (EditText) layout.findViewById(R.id.RoomToLabel);


        } else if (id == R.id.nav_call) {                              //If Call is chosen from menu
            View layout = inflater.inflate(call,                       //Create the popup for call
                    (ViewGroup) findViewById(R.id.popup_call));
            pwindo = new PopupWindow(layout, 1100, 850, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


                                                             //Create Buttons and assign listeners
            Button cancel = (Button) layout.findViewById(R.id.call_cancel);
            cancel.setOnClickListener(cancel_button_click_listener);

            Button TAC1 = (Button) layout.findViewById(R.id.TAC1);
            TAC1.setOnClickListener(TAC1_button_click_listener);

            Button TAC2 = (Button) layout.findViewById(R.id.TAC2);
            TAC2.setOnClickListener(TAC2_button_click_listener);

            Button TAC3 = (Button) layout.findViewById(R.id.TAC3);
            TAC3.setOnClickListener(TAC3_button_click_listener);

            Button CandC = (Button) layout.findViewById(R.id.CandC);
            CandC.setOnClickListener(CandC_button_click_listener);
        } else if (id == R.id.nav_settings) {
            // TODO: 11/2/2016 add popup



        } else if (id == R.id.nav_refresh) {                //important if map ever disconnects
            floor.loadUrl(getString(R.string.ip));                   //point it to the server
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
                                                                // Button click handling methods
    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) { //closes popup upon cancel
            pwindo.dismiss();
        }
    };
    private View.OnClickListener Clear_button_click_listener = new View.OnClickListener() {     //sanitize inputs
        public void onClick(View v) {   //sends room clear message to server and dismisses popup
            floor.loadUrl("javascript: sendChangeColor(\"" + rEdit.getText().toString() + "\",\"green\")");
            pwindo.dismiss();
        }
    };
    private View.OnClickListener Go_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO: 11/5/2016 Calculate and display directions
            pwindo.dismiss();
        }
    };
    private View.OnClickListener Injuries_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {   //sends injury label message to server then dismisses popup
            floor.loadUrl("javascript: sendLabel(\"" + lEdit.getText().toString() + "\",\"Injuries\")");
            pwindo.dismiss();
        }
    };
    private View.OnClickListener Civilians_button_click_listener = new View.OnClickListener() { //sends civilian label message to server then dismisses popup
        public void onClick(View v) {
            floor.loadUrl("javascript: sendLabel(\"" + lEdit.getText().toString() + "\",\"Civilians\")");
            pwindo.dismiss();
        }
    };
    private View.OnClickListener Danger_button_click_listener = new View.OnClickListener() {    //sends danger label message to server then dismisses popup
        public void onClick(View v) {
            floor.loadUrl("javascript: sendLabel(\"" + lEdit.getText().toString() + "\",\"Danger\")");
            pwindo.dismiss();
        }
    };
    private View.OnClickListener TAC1_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO: 11/6/2016 Call TAC1
        }
    };
    private View.OnClickListener TAC2_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO: 11/6/2016 Call TAC2
        }
    };
    private View.OnClickListener TAC3_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO: 11/6/2016 Call TAC3
        }
    };
    private View.OnClickListener CandC_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO: 11/6/2016 Call CandC
        }
    };
}