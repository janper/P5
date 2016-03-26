package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class RotatingPoints extends PApplet {
    private final static int STEP = 50;
    float maxDistance;
    private int method = 0;
    private int currentFrame = 0;

    public static void main(String args[]) {
        PApplet.main(new String[]{"org.research.processing.RotatingPoints"});
    }

    public void settings() {
        size(1280, 720, P2D);
        displayDensity(2);
    }

    public void setup() {
//        size (1280,720);
        noFill();
        maxDistance = sqrt(width*width+height*height)/2;
    }

    private void drawCircle(float x, float y, float radius, float phase, int myColour) {
        pushStyle();
        pushMatrix();
        stroke(myColour, map(mouseX, 0, width, 0, 255));
        strokeWeight(1);
        translate(x, y);
        ellipse(0, 0, radius * 2, radius * 2);
        float angle = 2 * PI * phase;
        rotate(angle);
        stroke(255);
        strokeWeight(10);
        point(radius,0);
        popStyle();
        popMatrix();
    }

    public void draw() {
        background(32);
        for (int y = STEP/2; y <= height-STEP/2f-50; y +=STEP) {
            for (int x = STEP/2; x <= width-STEP/2f; x+=STEP) {
                float distX = abs(width/2f - x);
                float distY = abs(height/2f - y);
                float dist = sqrt(distX*distX+distY*distY);
                float phase = 0;
                switch (method){
                    case 0:
                        phase = currentFrame / 60f;
                        break;
                    case 1:
                        phase = (currentFrame + x + y) / 60f;
                        break;
                    case 2:
                        phase = (currentFrame + x * y) / 60f;
                        break;
                    case 3:
                        phase = currentFrame*(x+y)/1000f / 60f;
                        break;
                    case 4:
                        phase = currentFrame / (60f+(x + y)/10f);
                        break;
                    case 5:
                        phase = currentFrame / (60f+(x * y)/50f);
                        break;
                    case 6:
                        phase = (currentFrame+(distX+distY)/10f) / 60f;
                        break;
                    case 7:
                        phase = (currentFrame+distX*distY/50f) / 60f;
                        break;
                    case 8:
                        phase = currentFrame / (60f+distX*distY/50f);
                        break;
                    case 9:
                        phase = currentFrame / (60f+30f*dist/maxDistance);
                        break;
                }
                int topColour = lerpColor(color(204, 51, 0), color(102, 153, 51), x / (float) width);
                int bottomColour = lerpColor(color(255), color(51, 102, 153), x / (float) width);
                int myColour = lerpColor(topColour, bottomColour, y/(float)height);
                drawCircle(x, y, STEP/2f-STEP/8f, phase, myColour);
            }
        }
        currentFrame++;

        pushStyle();
        fill(51, 102, 153);  //blue text
        textSize(25);
        text("Click! Current calculation method: "+method, 25, height-25);
        popStyle();
    }

    public void mousePressed(){
        method++;
        method%=10;
        currentFrame = 0;
    }
}
