package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TalkWithFans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_with_fans);

        //Finding buttons
        final Button backToWaitingRoomButton = findViewById(R.id.talkWithFansBackToWaitingRoomButton);
        final Button talkToFan = findViewById(R.id.talkWithFansTalkToFanButton);
        final Button contBtn = findViewById(R.id.talkWithFansContinueButton);
        //Finding TextViews
        TextView playerName = findViewById(R.id.talkWithFansNameTextView);
        final TextView fanNum = findViewById(R.id.talkWithFansFanTextView);
        final TextView text = findViewById(R.id.talkWithFansTextTextView);
        //Finding ImageViews
        final ImageView textBackground = findViewById(R.id.talkWithFandTextBackgroundImageView);

        playerName.setText(Player.playerName);
        fanNum.setText(String.valueOf(Player.playerFans));

        //Button Functions
        backToWaitingRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent waitingRoom = new Intent(TalkWithFans.this, WaitingRoom.class);
                TalkWithFans.this.startActivity(waitingRoom);
            }
        });//End backToWaitingRoom

        talkToFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call method here and pass button variables
                fanConvo(backToWaitingRoomButton, talkToFan, contBtn, text, textBackground);
            }
        });//End talkToFan
    }//End onCreate


    private void fanConvo(Button back, Button talk, Button contBtn, TextView text, ImageView textBackground){
        //Call random fan dialogue and show on screen

    }//End fanConvo
}//End TalkWithFans
