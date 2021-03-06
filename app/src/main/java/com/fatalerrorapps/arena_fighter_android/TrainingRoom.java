package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TrainingRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_room);
        //THIS NEEDS IT'S OWN GRAPHIC!!!!!!!

        //Setting stats
        textViewSetters();

        //Finding buttons
        final Button trainStrengthButton = findViewById(R.id.trainStrengthButton);
        final Button trainEnduranceButton = findViewById(R.id.trainEnduranceButton);
        final Button trainFatigueButton = findViewById(R.id.trainFatigueButton);
        final Button backToWaitingRoomButton = findViewById(R.id.backToWaitingRoomButton);
        final Button yesButton = findViewById(R.id.yesButton);
        final Button noButton = findViewById(R.id.noButton);
        final Button continueButton = findViewById(R.id.trainingRoomContinueButton);
        final ImageView textBackground = findViewById(R.id.trainingRoomTextBackgroundImageView);
        final TextView text = findViewById(R.id.trainingRoomTextTextView);

        //Button functions
        trainStrengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldCheck(1, trainStrengthButton,trainEnduranceButton,trainFatigueButton,
                        yesButton,noButton,text,textBackground,backToWaitingRoomButton,
                        continueButton);
            }
        });//End trainStrengthButton

        trainEnduranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldCheck(2, trainStrengthButton,trainEnduranceButton,trainFatigueButton,
                        yesButton,noButton,text,textBackground,backToWaitingRoomButton,
                        continueButton);
            }
        });//End enduranceButton

        trainFatigueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goldCheck(3, trainStrengthButton,trainEnduranceButton,trainFatigueButton,
                        yesButton,noButton,text,textBackground,backToWaitingRoomButton,
                        continueButton);
            }
        });//End fatigueButton

        backToWaitingRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToWaitingRoomIntent = new Intent(TrainingRoom.this, WaitingRoom.class);
                TrainingRoom.this.startActivity(backToWaitingRoomIntent);
            }
        });//End backToWaitingRoomButton
    }//End onCreate


    private void goldCheck(final int statToTrain, final Button trainStrengthButton, final Button trainEnduranceButton,
                           final Button trainFatigueButton, final Button yesButton, final Button noButton,
                           final TextView text, final ImageView textBackground, final Button backToWaitingRoomButton,
                           final Button continueButton){
        //check for cash and toss over to statTraining if player had enough
        Random rand = new Random();
        int valOfStat = 0;
        String nameOfStat = "";
        if(statToTrain == 1){
            valOfStat = Player.playerStrength;
            nameOfStat = "strength";
        }else if(statToTrain == 2){
            valOfStat = Player.playerEndurance;
            nameOfStat = "endurance";
        }else{
            valOfStat = Player.playerFatigue;
            nameOfStat = "fatigue";
        }
        int costToTrain = valOfStat * rand.nextInt(Player.playerLuck);
        //Setting a minimum cost
        if(costToTrain < valOfStat){
            costToTrain += valOfStat;
        }

        //Setting the text to show.
        String textToShow = "Spend $" + costToTrain + ", to train " + nameOfStat + "?";
        text.setText(textToShow);


        //Hiding and Showing stuff
        trainStrengthButton.setVisibility(View.INVISIBLE);
        trainEnduranceButton.setVisibility(View.INVISIBLE);
        trainFatigueButton.setVisibility(View.INVISIBLE);
        textBackground.setVisibility(View.VISIBLE);
        backToWaitingRoomButton.setVisibility(View.INVISIBLE);
        text.setVisibility(View.VISIBLE);
        yesButton.setVisibility(View.VISIBLE);
        noButton.setVisibility(View.VISIBLE);

        //Button functions
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainStrengthButton.setVisibility(View.VISIBLE);
                trainEnduranceButton.setVisibility(View.VISIBLE);
                trainFatigueButton.setVisibility(View.VISIBLE);
                textBackground.setVisibility(View.INVISIBLE);
                backToWaitingRoomButton.setVisibility(View.VISIBLE);
                text.setVisibility(View.INVISIBLE);
                yesButton.setVisibility(View.INVISIBLE);
                noButton.setVisibility(View.INVISIBLE);
            }
        });//End noButton

        final int finalCostToTrain = costToTrain;
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Player.playerCash >= finalCostToTrain){
                    Player.playerCash -= finalCostToTrain;

                    //Updating the cash value
                    TextView playerCash = findViewById(R.id.trainingRoomCashTextView);
                    playerCash.setText(String.valueOf(Player.playerCash));

                    statTraining(statToTrain, trainStrengthButton,trainEnduranceButton,trainFatigueButton,
                            yesButton,noButton,text,textBackground,backToWaitingRoomButton,
                            continueButton);
                }else{
                    text.setText(R.string.notEnoughCash);
                    yesButton.setVisibility(View.INVISIBLE);
                    noButton.setVisibility(View.INVISIBLE);
                    continueButton.setVisibility(View.VISIBLE);

                    continueButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            trainStrengthButton.setVisibility(View.VISIBLE);
                            trainEnduranceButton.setVisibility(View.VISIBLE);
                            trainFatigueButton.setVisibility(View.VISIBLE);
                            textBackground.setVisibility(View.INVISIBLE);
                            backToWaitingRoomButton.setVisibility(View.VISIBLE);
                            text.setVisibility(View.INVISIBLE);
                            continueButton.setVisibility(View.INVISIBLE);
                        }
                    });//End continueButton
                }//End if/else
            }//End onClick
        });//End yesButton


    }//End goldCheck


    private void statTraining(int statToTrain, final Button trainStrengthButton, final Button trainEnduranceButton,
                              final Button trainFatigueButton, final Button yesButton, final Button noButton,
                              final TextView text, final ImageView textBackground, final Button backToWaitingRoomButton,
                              final Button continueButton){
        //Train stat with cool math crap and show results

        Random rand = new Random();
        int timesToTrain = 1;
        if(rand.nextInt(Player.playerLuck) == Player.playerLuck){
            timesToTrain += 1;
        }
        int statGains = 0;
        String statName = "";

        for(int i = 1; i <= timesToTrain; i++){
            //Do crap
            if(statToTrain == 1){
                statGains += rand.nextInt(5);
                Player.playerStrength += statGains;
                statName = "Strength";

            }else if(statToTrain == 2){
                statGains += rand.nextInt(5);
                Player.playerEndurance += statGains;
                statName = "Endurance";
            }else{
                statGains += rand.nextInt(5);
                Player.playerFatigue += statGains;
                statName = "Fatigue";
            }//End if/else
        }//End for loop

        //Refreshing the stat window
        TextView strength = findViewById(R.id.trainingRoomStrengthTextView);
        strength.setText(String.valueOf(Player.playerStrength));
        TextView endurance = findViewById(R.id.trainingRoomEnduranceTextView);
        endurance.setText(String.valueOf(Player.playerEndurance));
        TextView fatigue = findViewById(R.id.trainingRoomFatigueTextView);
        fatigue.setText(String.valueOf(Player.playerFatigue));

        //Setting the text value
        String trainingResults = "";
        if(statGains == 0){
            trainingResults = "Your training failed!";
        }else{
            trainingResults = statName + " grew by " + statGains + " point!";
        }

        text.setText(trainingResults);
        yesButton.setVisibility(View.INVISIBLE);
        noButton.setVisibility(View.INVISIBLE);
        continueButton.setVisibility(View.VISIBLE);

        //Button function
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainStrengthButton.setVisibility(View.VISIBLE);
                trainEnduranceButton.setVisibility(View.VISIBLE);
                trainFatigueButton.setVisibility(View.VISIBLE);
                textBackground.setVisibility(View.INVISIBLE);
                backToWaitingRoomButton.setVisibility(View.VISIBLE);
                text.setVisibility(View.INVISIBLE);
                yesButton.setVisibility(View.INVISIBLE);
                noButton.setVisibility(View.INVISIBLE);
                continueButton.setVisibility(View.INVISIBLE);
            }
        });//End continueButton

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
