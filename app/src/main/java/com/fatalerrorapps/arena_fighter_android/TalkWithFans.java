package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

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
                //finding random text.
                String textToDisplay;
                textToDisplay = fanConvo();

                //Setting text
                text.setText(textToDisplay);
                //Showing text
                backToWaitingRoomButton.setVisibility(View.INVISIBLE);
                talkToFan.setVisibility(View.INVISIBLE);
                contBtn.setVisibility(View.VISIBLE);
                textBackground.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
            }
        });//End talkToFan

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToWaitingRoomButton.setVisibility(View.VISIBLE);
                talkToFan.setVisibility(View.VISIBLE);
                contBtn.setVisibility(View.INVISIBLE);
                textBackground.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
            }
        });//End contBtn
    }//End onCreate


    private String fanConvo(){
        //Call random fan dialogue and show on screen
        String fanTalk;
        Random rand = new Random();
        String[] noFans = {"You have no fans!"};
        String[] lowFans = {
                "Oh, you're the new guy! \nGood luck out there!",
                "I don't think I've seen \nany of your fights,\nbut good luck!",
                "You're crazy for fighting out there!"
        };
        String[] midFans = {
                "I'm a huge fan of yours!",
                "Wow, I love when I get to see you fight!\nI can't believe I met you!",
                "I always bet on you when you fight!\nPlease don't lose, I can't afford it..."
        };
        String[] hiFans = {
                "Wow, I'm so excited to meet you!\nYou're my favorite fighter!",
                "You'll be the best of \nthe best soon!",
                "I never thought you'd \nmake it so far, \ncongratulations!"
        };

        if(Player.playerFans == 0){
            fanTalk = noFans[0];
        }else if(Player.playerFans < 30){
            fanTalk = lowFans[rand.nextInt(lowFans.length)];
        }else if(Player.playerFans < 60){
            fanTalk = midFans[rand.nextInt(midFans.length)];
        }else{
            fanTalk = hiFans[rand.nextInt(hiFans.length)];
        }

        return fanTalk;
    }//End fanConvo
}//End TalkWithFans
