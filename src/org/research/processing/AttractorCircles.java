package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class AttractorCircles extends PApplet{

    float maxDistanceSquared;
    final float STEP = 50;
    final float MIN_SIZE = 10;
    final float MAX_SIZE = STEP*2;

    public static void main(String args[]) {
        PApplet.main(new String[] {"org.research.processing.AttractorCircles"});
    }

    public void settings(){
        size (1280,720, P2D);
    }

    public void setup(){
//        size (1280,720);
        stroke (255);
        strokeWeight(1);
        noFill();
        maxDistanceSquared=distanceSquared(0,0,width,height);
    }

    float distanceSquared (float x1, float y1, float x2, float y2){
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }

    public void draw(){
        background(32);
        for (int y=0; y<width; y+= STEP) {
            for (int x = 0; x < width; x += STEP) {
                float size = map(distanceSquared(x,y,mouseX, mouseY), 0,maxDistanceSquared,MIN_SIZE, MAX_SIZE);
                ellipse (x,y,size, size);
            }
        }

    }
}
