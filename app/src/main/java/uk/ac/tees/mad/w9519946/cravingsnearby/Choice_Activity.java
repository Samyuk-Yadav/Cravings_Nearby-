package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

public class Choice_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice);
        getSupportActionBar().hide();



    }

    public void callingLoginScreen(View v){
        Intent i = new Intent(getApplicationContext(), Screen_Login.class);

        Pair[] pair = new Pair[1];

        pair[0] = new Pair<View,String>(findViewById(R.id.login_Btnc),"login_transition");

        ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation(Choice_Activity.this, pair);
        startActivity(i);
    }



}