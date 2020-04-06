package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Arena extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        //Getting extra data
        Intent extra = getIntent();
        int opponentFighterClass = extra.getIntExtra("fighterClass", 1);

        //Name & Health
        TextView playerName = findViewById(R.id.arenaPlayerNameTextView);
        TextView playerHealth = findViewById(R.id.arenaPlayerHealthTextView);
        TextView opponentName = findViewById(R.id.arenaOpponentNameTextView);
        TextView opponentHealth = findViewById(R.id.arenaOpponentHealthTextView);
        //Text
        ImageView textBackground = findViewById(R.id.arenaTextBackgroundImageView);
        TextView text = findViewById(R.id.arenaTextTextView);
        //Buttons
        Button attackButton = findViewById(R.id.arenaAttackButton);
        Button defendButton = findViewById(R.id.arenaDefendButton);
        Button continueButton = findViewById(R.id.arenaContinueButton);

        //Setting names
        playerName.setText(Player.playerName);
        //Graphics are added in the opponentNaming function
        opponentName.setText(opponentNaming());

        //Creating opponent stats
        int opponentStrength = opponentStats(opponentFighterClass);
        int opponentEndurance = opponentStats(opponentFighterClass);
        int opponentFatigue = opponentStats(opponentFighterClass);


        //Setting health
        int playerHealthVal = healthGen();
        playerHealth.setText(String.valueOf(playerHealthVal));
        int opponentHealthVal = healthGen(opponentEndurance, opponentFatigue);
        opponentHealth.setText(String.valueOf(opponentHealthVal));



    }//End onCreate


    private String opponentNaming(){
        Random rand = new Random();
        String name = "";

        if(rand.nextInt(2) == 1){
            //Male Names
            //Remember to add random female graphic
            String[] maleNameList = {"Bob", "Wimp", "Dick"};
            name = maleNameList[rand.nextInt(maleNameList.length)];
        } else {
            //Female Names
            //Remember to add random female graphic
            String[] femaleNameList = {"Sandra", "Rita", "Fredrika"};
            name = femaleNameList[rand.nextInt(femaleNameList.length)];
        }//End if/else

        return name;
    }//End opponentNaming


    private int healthGen(){
        Random rand = new Random();
        int health = 0;
        int fatEnd = Player.playerEndurance + Player.playerFatigue;

        for(int i = fatEnd; i > 0; i -= rand.nextInt(fatEnd)){
            health += rand.nextInt(fatEnd);
        }

        return health;
    }//End healthGen
    private int healthGen(int opponentEndurance, int opponentFatigue){
        Random rand = new Random();
        int health = 0;
        int fatEnd = opponentEndurance + opponentFatigue;

        for(int i = fatEnd; i > 0; i -= rand.nextInt(fatEnd)){
            health += rand.nextInt(fatEnd);
        }

        return health;
    }//End healthGenOverload


    private int opponentStats(int fighterClass){
        int timesToTrain = 0;
        Random rand = new Random();
        int statVal = 0;

        if(fighterClass == 1){
            timesToTrain = 1;
            timesToTrain += rand.nextInt(3);
        }else if(fighterClass == 2){
            timesToTrain = 2;
            timesToTrain += rand.nextInt(6);
        }else if(fighterClass == 3){
            timesToTrain = 4;
            timesToTrain += rand.nextInt(12);
        }else if(fighterClass == 4){
            timesToTrain = 8;
            timesToTrain += rand.nextInt(24);
        }else{
            timesToTrain = 16;
            timesToTrain += rand.nextInt(48);
        }//End if/else

        for(int i = timesToTrain; i > 0; i--){
            statVal += rand.nextInt(5);
        }//End forLoop

        return statVal;
    }//End opponentStats

}//End Arena
