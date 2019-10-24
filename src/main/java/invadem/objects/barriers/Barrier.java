package invadem.objects.barriers;

import processing.core.PApplet;
import processing.core.PImage;

public class Barrier {

    private int x;
    private int y;
    private int width;
    private int height;

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

    public void draw(PApplet app) {
        app.image(now_img, x, y, width, height);
    }
}

