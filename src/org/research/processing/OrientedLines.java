package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class OrientedLines extends PApplet{

    final float STEP = 25;
    final float LENGTH = STEP*2f;

    public static void main(String args[]) {
        PApplet.main(new String[] {"org.research.processing.OrientedLines"});
    }

    public void settings(){
        size (1280,720, P2D);
    }

    public void setup(){
//        size (1280,720);
        stroke (255);
        strokeWeight(2);
        noFill();
    }

    float distance (float x1, float y1, float x2, float y2){
        float relativeX = x1 - x2;
        float relativeY = y1 - y2;
        return sqrt(relativeX * relativeX + relativeY * relativeY);
    }

    public void draw(){
        background(32);
        for (int y=0; y<width; y+= STEP) {
            for (int x = 0; x < width; x += STEP) {
                float distance = distance(x,y,mouseX,mouseY);
                float scale = (distance==0)?0:LENGTH/distance;
                float relativeX = (mouseX-x)*scale;
                float relativeY = (mouseY-y)*scale;
                line (x, y, x-relativeX, y-relativeY);
            }
        }

    }
}
