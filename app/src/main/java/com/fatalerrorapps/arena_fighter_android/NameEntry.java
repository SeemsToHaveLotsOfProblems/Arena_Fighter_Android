package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_entry);

        Button submitName = findViewById(R.id.submitNameButton);

        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store name into variable and pass into WaitingRoom intent
                EditText playerChosenName = findViewById(R.id.nameEntryEditText);
                String playerName = playerChosenName.getText().toString();

                Intent callWaitingRoom = new Intent(NameEntry.this, WaitingRoom.class);
                if(playerName.isEmpty()){
                    NameEntry.this.startActivity(callWaitingRoom);
                } else {
                    callWaitingRoom.putExtra(playerName, playerName);
                    NameEntry.this.startActivity(callWaitingRoom);
                }

            }//End onClick
        });//End submitName button

    }//End onCreate
}//End NameEntry
