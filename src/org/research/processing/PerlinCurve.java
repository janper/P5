package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class PerlinCurve extends PApplet{
    float noiseScale = 0.02f;
    boolean show = false;

    public static void main(String args[]) {
        PApplet.main(new String[] {"org.research.processing.PerlinCurve"});
    }

    public void settings(){
        size (1280,720, P2D);
        displayDensity(2);
    }

    public void setup(){
//        size (1280,720);
        strokeWeight(2);
        noFill();
        background(32);
    }

    void drawPerlinCurve (float x, float y, float phase, float step, int count, int color){
        pushStyle();
        stroke(color);
        beginShape();
        for (int i=0; i<count; i++){
            curveVertex(x,y);
            float angle = 2*PI*noise(x* noiseScale,y* noiseScale, phase* noiseScale);
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
//        int STEP = (int)map(mouseX, 0, width, 10, 50);
        int step = 20;
        int count = (int)(width*1.5/step);
//        NOISE_SCALE = map (frameCount, 0, 60*100, 0.01f, 0.0001f);
//        NOISE_SCALE = 10f/frameCount;
        noiseScale = 0.004f;
        if (show){
            show=false;
            println("phase = "+phase);
            println("STEP = "+step);
            println("count = "+count);
            println("NOISE_SCALE = "+noiseScale);
        }

        for (int y = 0; y < height; y+=10) {
            int color = lerpColor(color(204,51,0), color(102,153,51), y / (float) height);
            drawPerlinCurve(width+50, y, phase, step, count, color);
        }
    }

    public void mousePressed(){
        show = true;
        save ("perlin_curves_"+frameCount+".png");
        println ("shot saved");
    }
}
