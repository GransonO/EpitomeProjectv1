package africa.apeiron.batafind.REGISTRATION;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appus.splash.Splash;

import africa.apeiron.batafind.R;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Splash.Builder splash = new Splash.Builder(SignUp.this, getSupportActionBar());
        splash.setBackgroundImage(getResources().getDrawable(R.drawable.leather_shoes));
        splash.setSplashImage(getResources().getDrawable(R.drawable.home_logo));
        splash.perform();

       // splash.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
       // splash.setSplashImageColor(getResources().getColor(R.color.blue));
                //.setPivotXOffset(getResources().getInteger(R.integer.my_x_pivot))
                //.setPivotYOffset(getResources().getInteger(R.integer.my_y_pivot))
                //splash.setAnimationType(Splash.AnimationType.TYPE_2);

    }
}
