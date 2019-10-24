package invadem.objects.invaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Invader {

    private int x;
    private int y;
    private int width;
    private int height;
    private PImage img1;
    private PImage img2;
    private PImage eptImg;
    private PImage now_img;
    private int time = 0;

    public Invader(PImage img1, PImage img2, PImage eptImg, int x, int y, int width, int height) {
        this.img1 = img1;
        this.img2 = img2;
        this.eptImg = eptImg;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        now_img = img1;
    }

    private void xRightTick() {
        this.x += 1;
    }

    private void xLeftTick() {
        this.x -= 1;
    }

    private void yTick() {
        this.y += 1;
    }

    public void draw(PApplet app) {
        app.image(now_img, x, y, width, height);

        if (y < 400) {
            if (time % 2 == 0) {
                if (time < 120) {
                    xRightTick();
                    now_img = img1;
                } else if (time < 152) {
                    yTick();
                    now_img = img2;
                } else if (time < 272) {
                    xLeftTick();
                    now_img = img1;
                } else if (time < 304) {
                    yTick();
                    now_img = img2;
                } else {
                    time = 0;
                }
            }
        }
        else {
            now_img = eptImg;
        }


        time += 1;

    }


}
