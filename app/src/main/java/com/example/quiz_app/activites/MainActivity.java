package com.example.quiz_app.activites;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quiz_app.R;

public class MainActivity extends AppCompatActivity {
    ActionBarDrawerToggle drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup_Views();
    }
    void setup_Views(){
        setUpDrawlayout();
    }
    public void setUpDrawlayout(){
        setSupportActionBar(findViewById(R.id.appBar));
        drawer= new ActionBarDrawerToggle(MainActivity.this,findViewById(R.id.mainDrawer),R.string.app_name,R.string.app_name);
        drawer.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer.onOptionsItemSelected(item)) {
            return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}