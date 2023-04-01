package com.example.hw1;
import com.google.android.material.imageview.ShapeableImageView;

import android.view.View;



public class CarActivity   {

    private ShapeableImageView car1;
    private ShapeableImageView car2;
    private ShapeableImageView car3;

    public  CarActivity(ShapeableImageView car1,ShapeableImageView car2,ShapeableImageView car3){
        this.car1 = car1;
        this.car2 = car2;
        this.car3 = car3;
        car1.setVisibility(View.INVISIBLE);// make left and right car invisible
        car3.setVisibility(View.INVISIBLE);// make left and right car invisible
    }




    public void moveLeft() {
        if (car2.getVisibility() == View.VISIBLE) {
            car1.setVisibility((View.VISIBLE));
            car2.setVisibility((View.INVISIBLE));
        } else if (car3.getVisibility() == View.VISIBLE) {
            car2.setVisibility((View.VISIBLE));
            car3.setVisibility((View.INVISIBLE));
        }

    }

    public void moveRight() {
        if (car2.getVisibility() == View.VISIBLE) {
            car3.setVisibility((View.VISIBLE));
            car2.setVisibility((View.INVISIBLE));
        } else if (car1.getVisibility() == View.VISIBLE) {
            car2.setVisibility((View.VISIBLE));
            car1.setVisibility((View.INVISIBLE));
        }
    }

}

