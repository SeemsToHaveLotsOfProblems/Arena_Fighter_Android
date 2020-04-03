package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class logo extends AppCompatActivity {

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivityIntent = new Intent(logo.this, JoinArena.class);
            logo.this.startActivity(mainActivityIntent);
        }//EndOnClick
    };//End OnClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        //Test code
        Intent mainActivityIntent = new Intent(logo.this, JoinArena.class);
        logo.this.startActivity(mainActivityIntent);
    }
}
