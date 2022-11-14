package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Choice_Activity extends AppCompatActivity {

    //Variables
    Button Register;
    Button Login;
    Button How_We_work_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice);
        getSupportActionBar().hide();

        //Hooks
        Register = findViewById(R.id.register_Btnc);
        Login = findViewById(R.id.login_Btnc);
        How_We_work_Button = findViewById(R.id.how_we_work_btnc);

        //Login Activity
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice_Activity.this, Screen_Login.class);
                startActivity(i);
            }
        });

        //Register Activity
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice_Activity.this, Screen_Register_1.class);
                startActivity(i);
            }
        });



    }

 /*   public void callingLoginScreen(View v){
        Intent i = new Intent(getApplicationContext(), Screen_Login.class);

        Pair[] pair = new Pair[1];

        pair[0] = new Pair<View,String>(findViewById(R.id.login_Btnc),"login_transition");

        ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation(Choice_Activity.this, pair);
        startActivity(i);
    }
*/


}