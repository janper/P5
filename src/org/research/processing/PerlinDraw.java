package org.research.processing;

import processing.core.PApplet;

/**
 * Created by Jan on 30.01.2016.
 */
public class PerlinDraw extends PApplet {
    private static final float NOISE_SCALE = 0.004f;
    private static final int STEP = 20;
    private int count;

    private static final int ARRAY_SIZE = 50;

    private int pointIndex = 0;
    private float[][] points = new float[50][];

    public static void main(String args[]) {
        PApplet.main(new String[]{"org.research.processing.PerlinDraw"});
    }

    public void settings() {
        size(1280, 720, P2D);
        displayDensity(2);
    }

    public void setup() {
//        size (1280,720);
        strokeWeight(2);
        noFill();
        background(32);
        count = (int) (width * 1.5 / STEP);
    }

    private void drawPerlinCurve(float x, float y, float phase, float step, int count, float initAngle, int myColour) {
        pushStyle();
        stroke(myColour);
        beginShape();
        for (int i = 0; i < count; i++) {
            curveVertex(x, y);
            float angle = 2 * PI * noise(x * NOISE_SCALE, y * NOISE_SCALE, phase * NOISE_SCALE) + initAngle;
            x += cos(angle) * step;
            y += sin(angle) * step;
        }
        endShape();
        popStyle();
    }

    public void draw() {
        pushStyle();
        fill(32, 64);
        rect(0, 0, width, height);
        popStyle();

        float phase = frameCount / 2f;

        if (pointIndex == 0) {
            pushStyle();
            fill(51, 102, 153);  //blue text
            textSize(50);
            text("click", 25, 25+50);
            popStyle();
            return;
        }

        int actualPoints = (pointIndex < ARRAY_SIZE) ? pointIndex : ARRAY_SIZE;
        for (int i = 0; i < actualPoints; i++) {
            int myColour = lerpColor(color(204, 51, 0), color(102, 153, 51), i / (float) actualPoints);
            drawPerlinCurve(points[i][0], points[i][1], phase, STEP, count, points[i][2], myColour);
        }

    }

    public void keyPressed() {
        if (key == 's') {
            save("perlin_curves_" + frameCount + ".png");
            println("shot saved");
        }
    }

    public void mousePressed() {
        addLine();
    }

    private void addLine() {
        int currentIndex = pointIndex % ARRAY_SIZE + 1;
        float[] newPoint = new float[3];
        newPoint[0] = mouseX;
        newPoint[1] = mouseY;
        if (pointIndex > 1) {
            float vectorX = newPoint[0] - points[(pointIndex - 2) % ARRAY_SIZE][0];
            float vectorY = newPoint[1] - points[(pointIndex - 2) % ARRAY_SIZE][1];
            newPoint[2] = PI / 2 + atan(vectorY / vectorX);
        } else {
            newPoint[2] = 0;
        }
        points[currentIndex - 1] = newPoint;
        pointIndex++;
    }
}
