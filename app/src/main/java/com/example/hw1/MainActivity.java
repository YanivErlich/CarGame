package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final int DELAY = 1000;
   private MaterialButton[] main_BTN_options;
   private ShapeableImageView[] main_IMG_hearts;

   private ShapeableImageView[][] main_IMG_rocks;

   private ShapeableImageView car1;
   private ShapeableImageView car2;
   private ShapeableImageView car3;

     long startTime = 0;
   private CarActivity mainCar;

    private CountDownTimer timer;

    private RockActivity mainRocks;
    private int counter = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        mainRocks = new RockActivity(main_IMG_rocks);
        mainCar = new CarActivity(car1,car2,car3);
        setVisibility();
        setButtonsClickListeners();
        startGame();




    }

    private void startGame() {
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                handler.postDelayed(this, 700);
                mainRocks.changeRockPlaceView();
                crashCheck();

            }
        }, DELAY);

        runOnUiThread(new Runnable() {
            public void run() {
                handler.postDelayed(this,1700);
                mainRocks.startFallingRock();
            }
        });
    }

    private void crashCheck() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if(counter == 4)
            counter = 1;

        if (main_IMG_rocks[5][0].getVisibility() == View.VISIBLE && car1.getVisibility() == View.VISIBLE) {
            lifeManager(counter);
            toastVibrator(getApplicationContext(),v,counter);
            counter++;

        }
        if (main_IMG_rocks[5][1].getVisibility() == View.VISIBLE && car2.getVisibility() == View.VISIBLE) {

            lifeManager(counter);
            toastVibrator(getApplicationContext(),v,counter);
            counter++;


        }
        if (main_IMG_rocks[5][2].getVisibility() == View.VISIBLE && car3.getVisibility() == View.VISIBLE) {

            lifeManager(counter);
            toastVibrator(getApplicationContext(),v,counter);
            counter++;

        }


    }


    private void lifeManager(int life){
        main_IMG_hearts[main_IMG_hearts.length - life].setVisibility(View.INVISIBLE);
        if(life == 3){
            for (int i = 1; i <= 3 ; i++) {
                main_IMG_hearts[main_IMG_hearts.length - i].setVisibility(View.VISIBLE);
            }
        }
    }



    private void setButtonsClickListeners() {

        main_BTN_options[0].setOnClickListener(v->mainCar.moveLeft());
        main_BTN_options[1].setOnClickListener(v->mainCar.moveRight());

    }


    private void setVisibility(){
        mainRocks.setRocksInvis() ;
        mainCar.setCarInvis();;
    }

    private void findViews() {
        main_BTN_options = new MaterialButton[]{
                findViewById(R.id.Main_button_left),
                findViewById(R.id.Main_button_right)};
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)};
        car1 = findViewById(R.id.main_car1);// initiate cars
        car2 = findViewById(R.id.main_car2);// initiate cars
        car3 = findViewById(R.id.main_car3);// initiate cars

        setRocksView() ;

    }


    private void setRocksView() {

        main_IMG_rocks = new ShapeableImageView[7][3];
        int num = 1;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                String numRock = "rock" + num;
                num++;
                int resID = getResources().getIdentifier(numRock, "id", getPackageName());
                main_IMG_rocks[i][j] = ((ShapeableImageView) findViewById(resID));
            }
        }

    }

    private void toastVibrator(Context context,Vibrator v,int life){

        if(life == 3)
            life = 0;
        Toast.makeText(context,"Crash!!! You have " + (main_IMG_hearts.length - life) +" life Left" ,Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}

