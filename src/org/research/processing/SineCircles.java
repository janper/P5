package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class SineCircles extends PApplet{

    final int WEIGHT = 5;
    final float CIRCLE_DIAMETER = 100;

    public static void main(String args[]) {
        PApplet.main(new String[] {"org.research.processing.SineCircles"});
    }

    public void settings(){
        size (1280,720, P2D);
        displayDensity(2);
    }

    public void setup(){
//        size (1280,720);
        stroke (255,16);
        strokeWeight(WEIGHT);
        noFill();
        background(32);
        frameRate(120);
    }

    public void draw(){
        float phase = frameCount / (float) width;
        int midHeight = height / 2;
        int x = frameCount % width ;
        float y = sin(2 * PI * (phase + floor(phase/2)/6f) * 1.5f ) * (midHeight - CIRCLE_DIAMETER) + midHeight;
        float w = sin(2 * PI * phase * 2) * CIRCLE_DIAMETER;
        float h = cos(2 * PI * phase * 4) * CIRCLE_DIAMETER;
        ellipse(x, y, w, h);

        if (phase == 4){
            save ("sine_circles.png");
            println("shot saved");
        }
    }
}
