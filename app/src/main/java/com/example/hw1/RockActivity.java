package com.example.hw1;


import android.view.View;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;

public class RockActivity {


    private final ShapeableImageView[][] main_IMG_rocks;

    public RockActivity(ShapeableImageView[][] main_IMG_rocks) {
            this.main_IMG_rocks = main_IMG_rocks;
    }

    public void setRocksInvis() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                this.main_IMG_rocks[i][j].setVisibility(View.INVISIBLE);
            }
        }
    }


    public void startFallingRock() {
        Random rand = new Random();
        int randRock = rand.nextInt(3);//0-2
        main_IMG_rocks[0][randRock].setVisibility(View.VISIBLE);
    }


    public void changeRockPlaceView() {
        boolean  check[][] = new boolean[7][3];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 6)
                    main_IMG_rocks[i][j].setVisibility(View.INVISIBLE);
                if (main_IMG_rocks[i][j].getVisibility() == View.VISIBLE && !check[i][j] && i <= 5  ) {
                    main_IMG_rocks[i][j].setVisibility(View.INVISIBLE);
                    main_IMG_rocks[i+1][j].setVisibility(View.VISIBLE);
                    check[i+1][j] = true;
                }
            }

        }
    }



}
