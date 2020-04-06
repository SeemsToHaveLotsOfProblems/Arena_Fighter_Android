package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrainingRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_room);
        //THIS NEEDS IT'S OWN GRAPHIC!!!!!!!

        //Setting stats
        textViewSetters();

        //Finding buttons
        Button trainStrengthButton = findViewById(R.id.trainStrengthButton);
        Button trainEnduranceButton = findViewById(R.id.trainEnduranceButton);
        Button trainFatigueButton = findViewById(R.id.trainFatigueButton);
        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);

        //Button functions
        trainStrengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldCheck(1);
            }
        });//End trainStrengthButton

        trainEnduranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldCheck(2);
            }
        });//End enduranceButton

        trainFatigueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldCheck(3);
            }
        });//End fatigueButton

        /*
         * trainingRoomTextTextView & trainingRoomTextBackgroundImageView
         * are invisible to start.
         * Use: YourTextView.setVisiblity(View.VISIBLE)
         * to make them visible and add text
         */

    }//End onCreate


    private void goldCheck(int statToTrain){
        //check for cash and toss over to statTraining if player had enough
    }//End goldCheck


    private void statTraining(int statToTrain){

    }//End statTraining


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
