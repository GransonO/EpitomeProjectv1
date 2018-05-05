package africa.apeiron.batafind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import africa.apeiron.batafind.CATEGORIES.CategoryActivity;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        Thread T = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent X = new Intent(OpeningActivity.this,CategoryActivity.class);
                    startActivity(X);
                }
            }
        };
        T.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}