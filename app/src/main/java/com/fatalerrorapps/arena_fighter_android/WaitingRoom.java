package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WaitingRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        Player player = new Player();

        //Player Name
        TextView playerName = findViewById(R.id.nameTextView);
        playerName.setText(player.playerName);
        //Player Cash
        TextView playerCash = findViewById(R.id.cashTextView);
        playerCash.setText(Player.playerCash);
        //Player Strength
        TextView playerStrength = findViewById(R.id.strengthTextView);
        playerStrength.setText(Player.playerStrength);
        //Player Endurance
        TextView playerEndurance = findViewById(R.id.enduranceTextView);
        playerEndurance.setText(Player.playerEndurance);
        //Player Fatigue
        TextView playerFatigue = findViewById(R.id.fatigueTextview);
        playerFatigue.setText(Player.playerFatigue);
        //Total Fights
        TextView totalFights = findViewById(R.id.totalFightsTextView);
        totalFights.setText(Player.totalFights);
        //Fights Won
        TextView fightsWon = findViewById(R.id.fightsWonTextView);
        fightsWon.setText(Player.fightsWon);
        //Fights Lost
        TextView fightsLost = findViewById(R.id.fightsLostTextView);
        fightsLost.setText(Player.fightsLost);

    }//End onCreate
}//End WaitingRoom