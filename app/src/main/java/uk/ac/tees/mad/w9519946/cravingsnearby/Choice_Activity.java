package uk.ac.tees.mad.w9519946.cravingsnearby;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Choice_Activity extends AppCompatActivity {

    //Variables
    Button Customer_Register;
    Button Customer_Login;
    Button login_Btnc_Restaurant1;
    Button register_Btnc_restaurant1;
    Button How_We_work_Button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choice);
        getSupportActionBar().hide();

        //Hooks
        Customer_Register = findViewById(R.id.register_Btnc);
        Customer_Login = findViewById(R.id.login_Btnc);
        login_Btnc_Restaurant1 = findViewById(R.id.login_Btnc_Restaurant);
        register_Btnc_restaurant1 = findViewById(R.id.register_Btnc_restaurant);
        How_We_work_Button = findViewById(R.id.how_we_work_btnc);

        //Customer Login Activity
        Customer_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice_Activity.this, Screen_Login.class);
                startActivity(i);
            }
        });

        //Customer Register Activity
        Customer_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Choice_Activity.this, Screen_Register_1.class);
                startActivity(i);
            }
        });

        //Restaurant Login
        login_Btnc_Restaurant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Choice_Activity.this, Restaurant_Login.class));
            }
        });

        //Restaurant Register
        register_Btnc_restaurant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Choice_Activity.this, Restaurant_Register.class));
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