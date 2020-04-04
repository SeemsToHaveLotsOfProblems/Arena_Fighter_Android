package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WaitingRoom extends AppCompatActivity {

    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        textViewSetters();

    }//End onCreate

    private void textViewSetters(){
        //Player Name
        TextView playerName = findViewById(R.id.nameTextView);
        playerName.setText(Player.playerName);
        //Player Cash
        TextView playerCash = findViewById(R.id.cashTextView);
        playerCash.setText(String.valueOf(Player.playerCash));
        //Player Fans
        TextView playerFans = findViewById(R.id.fanTextView);
        playerFans.setText(String.valueOf(Player.playerFans));
        //Player Strength
        TextView playerStrength = findViewById(R.id.strengthTextView);
        playerStrength.setText(String.valueOf(Player.playerStrength));
        //Player Endurance
        TextView playerEndurance = findViewById(R.id.enduranceTextView);
        playerEndurance.setText(String.valueOf(Player.playerEndurance));
        //Player Fatigue
        TextView playerFatigue = findViewById(R.id.fatigueTextview);
        playerFatigue.setText(String.valueOf(Player.playerFatigue));
        //Total Fights
        TextView totalFights = findViewById(R.id.totalFightsTextView);
        totalFights.setText(String.valueOf(Player.totalFights));
        //Fights Won
        TextView fightsWon = findViewById(R.id.fightsWonTextView);
        fightsWon.setText(String.valueOf(Player.fightsWon));
        //Fights Lost
        TextView fightsLost = findViewById(R.id.fightsLostTextView);
        fightsLost.setText(String.valueOf(Player.fightsLost));
    }//End textViewSetters

}//End WaitingRoom