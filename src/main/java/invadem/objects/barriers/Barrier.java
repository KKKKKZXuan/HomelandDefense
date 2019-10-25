package invadem.objects.barriers;

import processing.core.PApplet;
import processing.core.PImage;

public class Barrier {

    private int x;
    private int y;
    private int width;
    private int height;
    private int sustain = 3;

    private PImage goodImg;
    private PImage damImg;
    private PImage brokenImg;
    private PImage eptImg;

    private PImage now_img;

    public Barrier(PImage goodImg, PImage damImg, PImage brokenImg, PImage eptImg, int  x, int y, int width, int height) {

        this.goodImg = goodImg;
        this.damImg = damImg;
        this.brokenImg = brokenImg;
        this.eptImg = eptImg;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        now_img = goodImg;

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void reduceSustain() {
        sustain -= 1;
    }

    public int getSustain() {
        if (sustain < 0) {
            return 0;
        }
        return sustain;
    }

    public void draw(PApplet app) {

        if (getSustain() == 3){
            now_img = goodImg;
        }
        else if (getSustain() == 2){
            now_img = damImg;
        }
        else if (getSustain() == 1){
            now_img = brokenImg;
        }
        else if (getSustain() == 0){
            now_img = eptImg;
        }

        app.image(now_img, x, y, width, height);

    }
}

