package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        int betAmount = extra.getIntExtra("betAmount", 0);

        //Name & Health
        TextView playerName = findViewById(R.id.arenaPlayerNameTextView);
        final TextView playerHealth = findViewById(R.id.arenaPlayerHealthTextView);
        final TextView opponentName = findViewById(R.id.arenaOpponentNameTextView);
        final TextView opponentHealth = findViewById(R.id.arenaOpponentHealthTextView);
        //Text
        final ImageView textBackground = findViewById(R.id.arenaTextBackgroundImageView);
        final TextView text = findViewById(R.id.arenaTextTextView);
        //Buttons
        final Button attackButton = findViewById(R.id.arenaAttackButton);
        final Button defendButton = findViewById(R.id.arenaDefendButton);
        final Button continueButton = findViewById(R.id.arenaContinueButton);

        //Setting names
        playerName.setText(Player.playerName);
        //Graphics are added in the opponentNaming function
        opponentName.setText(opponentNaming());

        //Creating opponent stats
        final int opponentStrength = opponentStats(opponentFighterClass);
        final int opponentEndurance = opponentStats(opponentFighterClass);
        final int opponentFatigue = opponentStats(opponentFighterClass);

        //Setting health
        final int[] playerHealthVal = {healthGen()};
        playerHealth.setText(String.valueOf(playerHealthVal[0]));
        final int[] opponentHealthVal = {healthGen(opponentEndurance, opponentFatigue)};
        opponentHealth.setText(String.valueOf(opponentHealthVal[0]));

        //Button Functions
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] damageDone = {attack(opponentStrength, opponentEndurance, opponentFatigue, true)};
                //call window to show damage dealt
                attackButton.setVisibility(View.INVISIBLE);
                defendButton.setVisibility(View.INVISIBLE);
                textBackground.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                final String[] textToDisplay = {Player.playerName + ", attacked and \ndealt " +
                        damageDone[0] + " points of damage!"};
                text.setText(textToDisplay[0]);
                continueButton.setVisibility(View.VISIBLE);
                opponentHealthVal[0] -= damageDone[0];
                opponentHealth.setText(String.valueOf(opponentHealthVal[0]));

                continueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //opponent turn
                        damageDone[0] = attack(opponentStrength, opponentEndurance, opponentFatigue, false);
                        //call window to show opponent damage dealt
                        textToDisplay[0] = opponentName + ", attacked and \ndealt " +
                                damageDone[0] + " points of damage!";
                        text.setText(String.valueOf(textToDisplay));
                        playerHealthVal[0] -= damageDone[0];
                        playerHealth.setText(String.valueOf(playerHealthVal[0]));

                        continueButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                attackButton.setVisibility(View.VISIBLE);
                                defendButton.setVisibility(View.VISIBLE);
                                textBackground.setVisibility(View.INVISIBLE);
                                text.setVisibility(View.INVISIBLE);
                                continueButton.setVisibility(View.INVISIBLE);
                            }
                        });//End continueButton2
                    }
                });//End continueButton
            }
        });//End attackButton

        defendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });//End defendButton

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


    private int attack(int opponentStrength, int opponentEndurance, int opponentFatigue,
                       boolean playerTurn){
        int damageDone;
        Random rand = new Random();
        if(playerTurn) {
            //Player raw damage calculation
            int playerAttack;
            if (rand.nextInt(Player.playerFatigue) == 0) {
                playerAttack = 0;
            } else {
                playerAttack = Player.playerStrength * rand.nextInt(Player.playerLuck);
            }

            //Opponent raw defence calculation
            int opponentDefend;
            if (rand.nextInt(opponentFatigue) == 0) {
                //Critical failure to defend
                opponentDefend = 0;
            } else {
                opponentDefend = (opponentEndurance * rand.nextInt(opponentStrength)) / 2;
            }

            //Finding final damage
            damageDone = playerAttack - opponentDefend;
            if (damageDone < 0) {
                damageDone = 0;
            }
        }else{
            //Opponent raw damage calculation
            int opponentAttack;
            if (rand.nextInt(opponentFatigue) == 0) {
                opponentAttack = 0;
            } else {
                opponentAttack = (opponentStrength * rand.nextInt(opponentFatigue)) / 2;
            }

            //Player raw defence calculation
            int playerDefend;
            if (rand.nextInt(Player.playerFatigue) == 0) {
                //Critical failure to defend
                playerDefend = 0;
            } else {
                playerDefend = (Player.playerEndurance * rand.nextInt(Player.playerLuck));
            }

            //Finding final damage
            damageDone = opponentAttack - playerDefend;
            if (damageDone < 0) {
                damageDone = 0;
            }
        }

        return damageDone;
    }

}//End Arena
