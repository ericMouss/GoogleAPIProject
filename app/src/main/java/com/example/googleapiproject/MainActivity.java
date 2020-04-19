package com.example.googleapiproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt, bt2;
    private int i;
    private FirstFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.bt);
        bt2 = (Button) findViewById(R.id.bt2);

        bt.setOnClickListener(this);
        bt2.setOnClickListener(this);

        fragment2 = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        i = 0;
    }

    @Override
    public void onClick(View v) {
        if (v == bt) {
            FirstFragment blankFragment = new FirstFragment();
            i++;
            blankFragment.setMonText("Mon Fragment numéro : " + i);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl, blankFragment).addToBackStack(null).commit();
        } else if (v == bt2) {
            fragment2.setMonText("HelloWorld !!");
            fragment2.rafraichirEcran();
        }
    }
}
