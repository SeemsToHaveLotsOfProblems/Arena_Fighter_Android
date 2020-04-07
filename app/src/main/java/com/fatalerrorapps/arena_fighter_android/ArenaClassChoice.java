package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArenaClassChoice extends AppCompatActivity {

    int betAmount = 0;

    @Override
    public void onBackPressed(){
        //Keep blank
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_class_choice);

        //Finding Buttons
        final Button beginnerClass = findViewById(R.id.classChoiceBeginnerButton);
        final Button regularClass = findViewById(R.id.classChoiceRegularButton);
        final Button advancedClass = findViewById(R.id.classChoiceAdvancedButton);
        final Button expertClass = findViewById(R.id.classChoiceExpertButton);
        final Button championClass = findViewById(R.id.classChoiceChampionButton);
        final Button backToWaitingRoom = findViewById(R.id.classChoiceBackToWaitingRoomButton);
        final Button continueButton = findViewById(R.id.classChoiceContinueButton);
        //Finding TextViews
        TextView playerCash = findViewById(R.id.classChoiceCashTextView);
        playerCash.setText(String.valueOf(Player.playerCash));
        final TextView badBet = findViewById(R.id.classChoiceBadBetTextTextView);
        //Finding ImageViews
        final ImageView badBetWindow = findViewById(R.id.classChoiceBadBetWindowImageView);
        //Finding EditText
        final EditText playerBet = findViewById(R.id.classChoiceBetAmountEditText);

        //Button Functions
        beginnerClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(betCheck(playerBet, beginnerClass, regularClass, advancedClass,
                        expertClass, championClass, backToWaitingRoom, continueButton,
                        badBet, badBetWindow)) {
                    Intent fight = new Intent(ArenaClassChoice.this, Arena.class);
                    fight.putExtra("fighterClass", 1);
                    fight.putExtra("betAmount", betAmount);
                    ArenaClassChoice.this.startActivity(fight);
                }
            }
        });//End beginnerClass

        regularClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(betCheck(playerBet, beginnerClass, regularClass, advancedClass,
                        expertClass, championClass, backToWaitingRoom, continueButton,
                        badBet, badBetWindow)) {
                    Intent fight = new Intent(ArenaClassChoice.this, Arena.class);
                    fight.putExtra("fighterClass", 2);
                    fight.putExtra("betAmount", betAmount);
                    ArenaClassChoice.this.startActivity(fight);
                }
            }
        });//End regularClassButton

        advancedClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(betCheck(playerBet, beginnerClass, regularClass, advancedClass,
                        expertClass, championClass, backToWaitingRoom, continueButton,
                        badBet, badBetWindow)) {
                    Intent fight = new Intent(ArenaClassChoice.this, Arena.class);
                    fight.putExtra("fighterClass", 3);
                    fight.putExtra("betAmount", betAmount);
                    ArenaClassChoice.this.startActivity(fight);
                }
            }
        });//End advancedClassButton

        expertClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(betCheck(playerBet, beginnerClass, regularClass, advancedClass,
                        expertClass, championClass, backToWaitingRoom, continueButton,
                        badBet, badBetWindow)) {
                    Intent fight = new Intent(ArenaClassChoice.this, Arena.class);
                    fight.putExtra("fighterClass", 4);
                    fight.putExtra("betAmount", betAmount);
                    ArenaClassChoice.this.startActivity(fight);
                }
            }
        });//End expertClassButton

        championClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(betCheck(playerBet, beginnerClass, regularClass, advancedClass,
                        expertClass, championClass, backToWaitingRoom, continueButton,
                        badBet, badBetWindow)) {
                    Intent fight = new Intent(ArenaClassChoice.this, Arena.class);
                    fight.putExtra("fighterClass", 5);
                    fight.putExtra("betAmount", betAmount);
                    ArenaClassChoice.this.startActivity(fight);
                }
            }
        });//End ChampionClassButton

        backToWaitingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaveArena = new Intent(ArenaClassChoice.this, WaitingRoom.class);
                ArenaClassChoice.this.startActivity(leaveArena);
            }
        });//End backToWaitingRoomButton

    }//End onCreate


    private boolean betCheck(EditText amountBet, final Button bgnClass, final Button rgClass,
                             final Button adClass, final Button exClass,final Button cmpClass,
                             final Button goBack, final Button contButton, final TextView badBet,
                             final ImageView badBetWindow){
        boolean canBet = false;
        int cashAvailable = Player.playerCash;

        //Retrieving EditText Data
        String editTextData = amountBet.getText().toString();
        int playerBet;
        try{
            playerBet = Integer.parseInt(editTextData);
        }catch(NumberFormatException nfe) {
            playerBet = 0;
        }

        //Checking if bet can be made
        if(cashAvailable >= playerBet || playerBet == 0){
            Player.playerCash -= playerBet;
            betAmount = playerBet;
            canBet = true;
        }else{
            //Hide buttons here and show text
            bgnClass.setVisibility(View.INVISIBLE);
            rgClass.setVisibility(View.INVISIBLE);
            adClass.setVisibility(View.INVISIBLE);
            adClass.setVisibility(View.INVISIBLE);
            exClass.setVisibility(View.INVISIBLE);
            cmpClass.setVisibility(View.INVISIBLE);
            goBack.setVisibility(View.INVISIBLE);
            badBetWindow.setVisibility(View.VISIBLE);
            badBet.setVisibility(View.VISIBLE);
            contButton.setVisibility(View.VISIBLE);

            contButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bgnClass.setVisibility(View.VISIBLE);
                    rgClass.setVisibility(View.VISIBLE);
                    adClass.setVisibility(View.VISIBLE);
                    adClass.setVisibility(View.VISIBLE);
                    exClass.setVisibility(View.VISIBLE);
                    cmpClass.setVisibility(View.VISIBLE);
                    goBack.setVisibility(View.VISIBLE);
                    badBetWindow.setVisibility(View.INVISIBLE);
                    badBet.setVisibility(View.INVISIBLE);
                    contButton.setVisibility(View.INVISIBLE);
                }
            });//End continueButton
        }
        return canBet;
    }
}//End ArenaClassChoice
