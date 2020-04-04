package com.fatalerrorapps.arena_fighter_android;

import android.app.Application;

import java.util.Random;

public class Player extends Application {

    //Global Variables
    static String playerName = "Aaren";
    static int playerCash = 15;
    static int playerFans = 0;
    static int playerStrength = 1;
    static int playerEndurance = 5;
    static int playerFatigue = 1;
    static int totalFights = 0;
    static int fightsWon = 0;
    static int fightsLost = 0;

    public static int statTraining(int statToTrain){
        //1=strength, 2=endurance, 3=fatigue

        int growth = 0;

        Random rand = new Random();
        int timesToTrain = 0;
        if(playerFatigue > 1){
            timesToTrain += rand.nextInt(playerFatigue);
        } else {
            timesToTrain += rand.nextInt(2);
        }

        for(int i = 0; i < timesToTrain; i++){
            growth += rand.nextInt(5);
        }//end for

        if (statToTrain == 1){
            playerStrength += growth;
        } else if(statToTrain == 2) {
            playerEndurance += growth;
        } else {
            playerFatigue += growth;
        }

        return growth;

    }//End statToTrain

}//End Player
