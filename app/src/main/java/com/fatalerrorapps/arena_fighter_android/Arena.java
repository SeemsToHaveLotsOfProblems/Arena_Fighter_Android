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

    //private class variables
    private boolean counterAttack;
    private boolean defended;

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
        final int opponentFighterClass = extra.getIntExtra("fighterClass", 1);
        final int betAmount = extra.getIntExtra("betAmount", 0);

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
        final Button continue2Button = findViewById(R.id.classChoiceContinueButton2);

        //Setting names
        playerName.setText(Player.playerName);
        //Graphics are added in the opponentNaming function
        final String nameOfOpponent = opponentNaming();
        opponentName.setText(nameOfOpponent);

        //Creating opponent stats
        final int opponentStrength = opponentStats(opponentFighterClass);
        final int opponentEndurance = opponentStats(opponentFighterClass);
        final int opponentFatigue = opponentStats(opponentFighterClass);

        //Setting health
        final int[] playerHealthVal = {healthGen()};
        playerHealth.setText(String.valueOf(playerHealthVal[0]));
        final int[] opponentHealthVal = {healthGen(opponentEndurance, opponentFatigue)};
        opponentHealth.setText(String.valueOf(opponentHealthVal[0]));

        //Variables
        final String[] textToDisplay = new String[1];
        final int[] damageDone = {0};

        //Button Functions
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damageDone[0] = attack(opponentStrength, opponentEndurance, true);
                //call window to show damage dealt
                attackButton.setVisibility(View.INVISIBLE);
                defendButton.setVisibility(View.INVISIBLE);
                textBackground.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                textToDisplay[0] = Player.playerName + ", attacked and \ndealt " +
                        damageDone[0] + " points of damage!";
                text.setText(textToDisplay[0]);
                continueButton.setVisibility(View.VISIBLE);
                opponentHealthVal[0] -= damageDone[0];
                opponentHealth.setText(String.valueOf(opponentHealthVal[0]));
            }
        });//End attackButton


        defendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Defend function called here
                counterAttack = defend();
                Random rand = new Random();

                if(counterAttack){
                    damageDone[0] = Player.playerEndurance + rand.nextInt(1 + Player.playerEndurance);
                    attackButton.setVisibility(View.INVISIBLE);
                    defendButton.setVisibility(View.INVISIBLE);
                    textBackground.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    textToDisplay[0] = Player.playerName + ", counter attacked and \ndealt " +
                            damageDone[0] + " points of damage!";
                    text.setText(textToDisplay[0]);
                    continueButton.setVisibility(View.VISIBLE);
                    opponentHealthVal[0] -= damageDone[0];
                    opponentHealth.setText(String.valueOf(opponentHealthVal[0]));
                }else {
                    defended = true;
                    attackButton.setVisibility(View.INVISIBLE);
                    defendButton.setVisibility(View.INVISIBLE);
                    textBackground.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    textToDisplay[0] = Player.playerName + ", defended!";
                    text.setText(textToDisplay[0]);
                    continueButton.setVisibility(View.VISIBLE);
                }//End if/else
            }
        });//End defendButton

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counterAttack) {
                    //opponent turn
                    textToDisplay[0] = nameOfOpponent + ", was staggered by the counter attack!";
                    text.setText(textToDisplay[0]);

                    continueButton.setVisibility(View.INVISIBLE);
                    continue2Button.setVisibility(View.VISIBLE);
                }else if(defended){
                    //opponent turn
                    damageDone[0] = (attack(opponentStrength, opponentEndurance, false)) / 2;
                    //call window to show opponent damage dealt
                    textToDisplay[0] = nameOfOpponent + ", attacked and \ndealt " +
                            damageDone[0] + " points of damage!";
                    text.setText(textToDisplay[0]);
                    playerHealthVal[0] -= damageDone[0];
                    playerHealth.setText(String.valueOf(playerHealthVal[0]));

                    continueButton.setVisibility(View.INVISIBLE);
                    continue2Button.setVisibility(View.VISIBLE);
                }else{
                    //opponent turn
                    damageDone[0] = attack(opponentStrength, opponentEndurance, false);
                    //call window to show opponent damage dealt
                    textToDisplay[0] = nameOfOpponent + ", attacked and \ndealt " +
                            damageDone[0] + " points of damage!";
                    text.setText(textToDisplay[0]);
                    playerHealthVal[0] -= damageDone[0];
                    playerHealth.setText(String.valueOf(playerHealthVal[0]));

                    continueButton.setVisibility(View.INVISIBLE);
                    continue2Button.setVisibility(View.VISIBLE);
                }//End if/else
            }
        });//End continueButton

        continue2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackButton.setVisibility(View.VISIBLE);
                defendButton.setVisibility(View.VISIBLE);
                textBackground.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
                continue2Button.setVisibility(View.INVISIBLE);
                victoryCheck(playerHealthVal[0], opponentHealthVal[0], opponentFighterClass, betAmount,
                        attackButton, defendButton, continueButton, continue2Button, text, textBackground);
            }
        });//End continueButton2
    }//End onCreate


    private String opponentNaming(){
        Random rand = new Random();
        String name;

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
        int timesToTrain;
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


    private int attack(int opponentStrength, int opponentEndurance,
                       boolean playerTurn){
        int damageDone;
        Random rand = new Random();
        if(playerTurn) {
            //Player raw damage calculation
            int playerAttack = Player.playerStrength + rand.nextInt(Player.playerStrength) *
                    rand.nextInt(Player.playerLuck);

            //Opponent raw defence calculation
            int opponentDefend = (opponentEndurance + rand.nextInt(1 + opponentEndurance)) / 2;

            //Finding final damage
            damageDone = playerAttack - opponentDefend;
            if (damageDone < 0) {
                damageDone = 0;
            }
        }else{
            //Opponent raw damage calculation
            int opponentAttack = (opponentStrength + rand.nextInt(1 + opponentStrength)) *
                    rand.nextInt(3);

            //Player raw defence calculation
            int playerDefend = (Player.playerEndurance + (rand.nextInt(1 + Player.playerEndurance) / 2) *
                    rand.nextInt(Player.playerLuck));

            //Finding final damage
            damageDone = opponentAttack - playerDefend;
            if (damageDone < 0) {
                damageDone = 0;
            }
        }

        return damageDone;
    }


    private boolean defend(){
        Random rand = new Random();
        boolean counterAttack = false;
        int oddsOfCounter = rand.nextInt(10);

        for(int i = rand.nextInt(1 + Player.playerLuck); i > 0; i--) {
            if (oddsOfCounter == 7) {
                counterAttack = true;
                i = 0;
            }
        }//End for loop
        return counterAttack;
    }//End defend


    private void victoryCheck(int playerHealth, int opponentHealth, int fighterClass, int playerBet,
                              Button attackButton, Button defendButton, Button continueButton,
                              Button continue2Button, TextView text, ImageView textBackground){
        boolean endFight = false;
        boolean playerWin = false;
        Random rand = new Random();
        //Find Winner
        if(playerHealth <= 0){
            endFight = true;
        }
        if(opponentHealth <= 0){
            endFight = true;
            playerWin = true;
        }

        if(endFight) {
            //Giving cash
            int cashForFighting = 10;
            for (int i = 1; i < fighterClass; i++) {
                cashForFighting *= 2;
            }
            if(playerWin) {
                int betPrize = playerBet * 2;
                cashForFighting += betPrize;
                Player.playerCash += cashForFighting;
                Player.playerFans += rand.nextInt(10);
            }else{
                Player.playerCash += cashForFighting;
            }

            String endFightMessage;

            if(playerWin){
                endFightMessage = Player.playerName + " wins!\n" + "Congratulations!\n" + "You win $" +
                cashForFighting + "!";
                Player.fightsWon += 1;
                Player.totalFights += 1;
            }else{
                endFightMessage = "You lose!\n" + "Better luck next time!\n" +
                        "You earn $" + cashForFighting + " as a consolation prize!";
                Player.fightsLost += 1;
                Player.totalFights += 1;
            }

            //Showing win/loss message
            attackButton.setVisibility(View.INVISIBLE);
            defendButton.setVisibility(View.INVISIBLE);
            continue2Button.setVisibility(View.INVISIBLE);
            continueButton.setVisibility(View.VISIBLE);
            textBackground.setVisibility(View.VISIBLE);
            text.setText(endFightMessage);
            text.setVisibility(View.VISIBLE);

            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent backToWaitingRoom = new Intent(Arena.this, WaitingRoom.class);
                    Arena.this.startActivity(backToWaitingRoom);
                }
            });//End continueButton
        }//End endFight check
    }//End victoryCheck

}//End Arena
