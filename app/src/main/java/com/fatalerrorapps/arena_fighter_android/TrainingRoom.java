package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TrainingRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_room);
        //THIS NEEDS IT'S OWN GRAPHIC!!!!!!!

        //Setting stats
        textViewSetters();

        /*
         * trainingRoomTextTextView & trainingRoomTextBackgroundImageView
         * are invisible to start.
         * Use: YourTextView.setVisiblity(View.VISIBLE)
         * to make them visible and add text
         */

    }//End onCreate

    private void textViewSetters(){
        //Player Name
        TextView playerName = findViewById(R.id.trainingRoomNameTextView);
        playerName.setText(Player.playerName);
        //Player Cash
        TextView playerCash = findViewById(R.id.trainingRoomCashTextView);
        playerCash.setText(String.valueOf(Player.playerCash));
        //Player Strength
        TextView playerStrength = findViewById(R.id.trainingRoomStrengthTextView);
        playerStrength.setText(String.valueOf(Player.playerStrength));
        //Player Endurance
        TextView playerEndurance = findViewById(R.id.trainingRoomEnduranceTextView);
        playerEndurance.setText(String.valueOf(Player.playerEndurance));
        //Player Fatigue
        TextView playerFatigue = findViewById(R.id.trainingRoomFatigueTextView);
        playerFatigue.setText(String.valueOf(Player.playerFatigue));

    }//End textViewSetters
}//End TrainingRoom
