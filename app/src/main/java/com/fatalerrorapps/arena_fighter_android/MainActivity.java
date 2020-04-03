package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //Creating the buttons
    Button joinArenaButton = findViewById(R.id.joinArenaButton);
    Button settingButton = findViewById(R.id.settingsButton);

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == joinArenaButton){
                //Send user to name input
                Intent waitingRoomIntent = new Intent(MainActivity.this, WaitingRoom.class);
                MainActivity.this.startActivity(waitingRoomIntent);

            }//End joinArenaButton

            if(v == settingButton){
                //send user to settings menu

            }//end settingsButton

        }//End onClick
    };//End On click listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//End onCreate
}//End Class
