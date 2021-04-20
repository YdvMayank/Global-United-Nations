package dev.rao.globalunitednations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import dev.rao.globalunitednations.walkthrough.WalkThroughActivity;

public class SplashScreenActivity extends AppCompatActivity {

    int SPLASH_TIME = 1500; //This is 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (restorePrefData()) {
            savedState();
        } else {
            //Code to start timer and take action after the timer ends
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, WalkThroughActivity.class));

                }
            }, SPLASH_TIME);
        }
    }

//    private void intentSplash(){
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Call next screen
//                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
//
//                //Attach all the elements those you want to animate in design
//                Pair[]  pairs = new Pair[2];
//                pairs[0] = new Pair<View,String>(getDrawable(R.drawable.gun_white), "logo_image");
//                pairs[1] = new Pair<View,String>(logo, "logo_text");
//                //wrap the call in API level 21 or higher
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
//                    startActivity(intent,options.toBundle());
//                }
//            }
//        }, 1500);
//    }

    private void savedState(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, SignUpActivity.class));
                overridePendingTransition(0,0);
                finish();
            }
        }, SPLASH_TIME);

    };

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend", false);
        return isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", true);
        editor.commit();
    }


    //Method to run progress bar for 5 seconds
}