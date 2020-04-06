package com.fatalerrorapps.arena_fighter_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ArenaClassChoice extends AppCompatActivity {

    @Override
    public void onBackPressed(){
        //Keep blank
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_class_choice);

        //Finding Buttons
        Button beginnerClass = findViewById(R.id.classChoiceBeginnerButton);
        Button regularClass = findViewById(R.id.classChoiceRegularButton);
        Button advancedClass = findViewById(R.id.classChoiceAdvancedButton);
        Button expertClass = findViewById(R.id.classChoiceExpertButton);
        Button championClass = findViewById(R.id.classChoiceChampionButton);

        //Button Functions
        //Add bets above here
        beginnerClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fight = new Intent(ArenaClassChoice.this, Arena.class);
                fight.putExtra("fighterClass", 1);
                ArenaClassChoice.this.startActivity(fight);
            }
        });//End beginnerClass

    }//End onCreate
}//End ArenaClassChoice
