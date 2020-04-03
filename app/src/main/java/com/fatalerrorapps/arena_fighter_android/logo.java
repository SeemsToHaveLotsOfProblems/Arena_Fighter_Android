package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class logo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        ImageView logoGif = findViewById(R.id.logoGif);
        logoGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joinArenaIntent = new Intent(logo.this, JoinArena.class);
                logo.this.startActivity(joinArenaIntent);
            }
        });

    }//End onCreate
}//End logo class
