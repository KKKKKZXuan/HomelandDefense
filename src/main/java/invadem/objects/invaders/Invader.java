package invadem.objects.invaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Invader {

    private int x;
    private int y;
    private int width;
    private int height;
    private PImage regImg1;
    private PImage regImg2;
    private PImage armImg1;
    private PImage armImg2;
    private PImage powImg1;
    private PImage powImg2;
    private PImage now_img;
    private int time = 0;
    private int life;
    private String type;

    private boolean touchBottom = false;

    public Invader(PImage regImg1, PImage regImg2, PImage armImg1, PImage armImg2, PImage powImg1, PImage powImg2, String type, int x, int y, int width, int height) {
        this.regImg1 = regImg1;
        this.regImg2 = regImg2;
        this.armImg1 = armImg1;
        this.armImg2 = armImg2;
        this.powImg1 = powImg1;
        this.powImg2 = powImg2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;

        switch (this.type) {
            case "regular":
                now_img = regImg1;
                life = 1;
                break;
            case "armoured":
                now_img = armImg1;
                life = 3;
                break;
            case "power":
                now_img = powImg1;
                life = 1;
                break;
        }

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

    private void xRightTick() {
        this.x += 1;
    }

    private void xLeftTick() {
        this.x -= 1;
    }

    private void yTick() {
        this.y += 1;
    }
    
    private void IsTouchBottom() {
        touchBottom = true;
    }

    public String type() {
        return type;
    }

    public void getShot() {
        life -= 1;
    }

    public boolean isDead() {
        return life <= 0;
    }

    public void draw(PApplet app) {
        app.image(now_img, x, y, width, height);

        if (y < 400) {
            if (time % 2 == 0) {
                if (time < 120) {
                    xRightTick();
                    switch (type) {
                        case "regular":
                            now_img = regImg1;
                            break;
                        case "armoured":
                            now_img = armImg1;
                            break;
                        case "power":
                            now_img = powImg1;
                            break;
                    }
                } else if (time < 152) {
                    yTick();
                    switch (type) {
                        case "regular":
                            now_img = regImg2;
                            break;
                        case "armoured":
                            now_img = armImg2;
                            break;
                        case "power":
                            now_img = powImg2;
                            break;
                    }
                } else if (time < 272) {
                    xLeftTick();
                    switch (type) {
                        case "regular":
                            now_img = regImg1;
                            break;
                        case "armoured":
                            now_img = armImg1;
                            break;
                        case "power":
                            now_img = powImg1;
                            break;
                    }
                } else if (time < 304) {
                    yTick();
                    switch (type) {
                        case "regular":
                            now_img = regImg2;
                            break;
                        case "armoured":
                            now_img = armImg2;
                            break;
                        case "power":
                            now_img = powImg2;
                            break;
                    }
                } else {
                    time = 0;
                }
            }
        }
        else {
            IsTouchBottom();
        }


        time += 1;

    }


}
