package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JoinArena extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_arena);

        //Setting the buttons
        Button joinArenaButton = findViewById(R.id.joinArenaButton);
        Button settingsButton = findViewById(R.id.settingsButton);

        //Button functions
        joinArenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enterName = new Intent(JoinArena.this, NameEntry.class);
                JoinArena.this.startActivity(enterName);
            }//End onClick
        });//End joinArenaButton

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call the settings intent

            }//End onClick
        });//End settingsButton

    }//End onCreate
}//End JoinArena Class
