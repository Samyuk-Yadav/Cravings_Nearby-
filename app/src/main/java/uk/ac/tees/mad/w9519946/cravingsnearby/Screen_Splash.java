package uk.ac.tees.mad.w9519946.cravingsnearby;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Screen_Splash extends AppCompatActivity {


    private static int SPLASH_SCREEN = 6900;

    //Animations
    Animation blast_animation;
    Animation botum_animation;
    Animation elside_animation;
    Animation erside_animation;

    //Variables
    View logo_anim;
    TextView title;
    TextView subheading;
    TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);
        getSupportActionBar().hide();


        Animation botum_animation = AnimationUtils.loadAnimation(this, R.anim.botum_animation);
        Animation blast_animation = AnimationUtils.loadAnimation(this, R.anim.blast_animation);
        Animation elside_animation = AnimationUtils.loadAnimation(this, R.anim.elside_animation);
        Animation erside_animation = AnimationUtils.loadAnimation(this, R.anim.erside_animation);

        logo_anim = findViewById(R.id.Logo_anim);
        title = findViewById(R.id.Title);
        subheading = findViewById(R.id.subheading);
        author = findViewById(R.id.Author);

        logo_anim.setAnimation(botum_animation);
        title.setAnimation(blast_animation);
        subheading.setAnimation(elside_animation);
        author.setAnimation(erside_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(Screen_Splash.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        },SPLASH_SCREEN);

    }
}
