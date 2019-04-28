package africa.apeiron.batafind.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.novoda.merlin.MerlinsBeard;

import africa.apeiron.batafind.home.HomeActivity;
import africa.apeiron.batafind.R;

public class SignUp extends AppCompatActivity {

    MerlinsBeard merlinsBeard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        merlinsBeard = MerlinsBeard.from(this);

        Button submit = findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (merlinsBeard.isConnected()) {
                    Intent X = new Intent(SignUp.this,HomeActivity.class);
                    startActivity(X);
                } else {
                    StyleableToast.makeText(SignUp.this, "Please check your internet connection!", Toast.LENGTH_LONG, R.style.No_Network).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
