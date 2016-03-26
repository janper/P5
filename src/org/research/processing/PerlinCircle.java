package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class PerlinCircle extends PApplet{
    private static final int STEPS = 60;
    private static final float NOISE_SCALE = 0.004f;
    boolean show = false;

    public static void main(String args[]) {
        PApplet.main(new String[] {"org.research.processing.PerlinCircle"});
    }

    public void settings(){
        size (1280,720, P2D);
//        displayDensity(2);
    }

    public void setup(){
//        size (1280,720);
        strokeWeight(2);
        noFill();
        background(32);
    }

    void drawPerlinCurve (float x, float y, float phase, float step, int count, float initAngle, int myColour){
        pushStyle();
        stroke(myColour);
        beginShape();
        for (int i=0; i<count; i++){
            curveVertex(x,y);
            float angle = 2*PI*noise(x* NOISE_SCALE,y* NOISE_SCALE, phase* NOISE_SCALE)+initAngle;
            x += cos(angle)*step;
            y += sin(angle)*step;
        }
        endShape();
        popStyle();
    }

    public void draw(){
        pushStyle();
        fill (32,16);
        rect(0,0,width,height);
        popStyle();

        float phase = frameCount / 2f;
        int step = 20;
        int count = (int)(width*1.5/step);

        if (show){
            show=false;
            println("phase = "+phase);
            println("STEP = "+step);
            println("count = "+count);
            println("NOISE_SCALE = "+ NOISE_SCALE);
        }

        for (int i = 0; i < STEPS; i++) {
            float circlePhase = i / (float) STEPS;
            float angle = 2 * PI * circlePhase;
            int myColour;

            if (circlePhase<1/3f){
                myColour = lerpColor(color(204,51,0), color(102,153,51), circlePhase/(1/3f));
            } else {
                if (circlePhase < 2/3f) {
                    myColour = lerpColor( color(102, 153, 51), color(51,102,153), (circlePhase-1/3f)/(1/3f));
                } else {
                    myColour = lerpColor(color(51,102,153),color(204,51,0), (circlePhase-2/3f)/(1/3f));
                }
            }
            float x = cos(angle)*100;
            float y = sin(angle)*100;
            drawPerlinCurve(width/2+x, height/2+y, phase, step, count, angle, myColour);
        }
    }

    public void mousePressed(){
        show = true;
        save ("perlin_circle_"+frameCount+".png");
        println ("shot saved");
    }
}
