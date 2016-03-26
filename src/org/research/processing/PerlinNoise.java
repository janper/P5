package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class PerlinNoise extends PApplet{
    final float NOISE_SCALE = 0.02f;
    final int SIZE = 3;

    public static void main(String args[]) {
        PApplet.main(new String[] {"org.research.processing.PerlinNoise"});
    }

    public void settings(){
        size (1280,720, P2D);
        displayDensity(2);
    }

    public void setup(){
//        size (1280,720);
        stroke (255);
        strokeWeight(SIZE);
        noFill();
//        noiseDetail(100);
    }

    public void draw(){
        background(0);
        for (int y=0; y<width; y+=SIZE) {
            for (int x = 0; x < width; x +=SIZE) {
                float value = noise(x*NOISE_SCALE,y*NOISE_SCALE, frameCount*NOISE_SCALE);
                stroke (255*value);
                point(x,y);
            }
        }
    }
}
