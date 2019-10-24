package invadem;

import java.util.List;
import java.util.ArrayList;

import invadem.objects.barriers.Barrier;
import invadem.objects.hints.Hint;
import invadem.objects.invaders.Invader;
import invadem.objects.projectiles.Projectile;
import invadem.objects.tanks.Tank;
import processing.core.PApplet;
import processing.event.KeyEvent;


public class App extends PApplet {

    private List<Invader> invaders;
    private List<Barrier> barriers;
    private List<Tank> tanks;
    private List<Projectile> projectiles;
    private List<Hint> hints;

    private boolean isAlive = true;
    private boolean isDead = false;

    public App() {
        //Set up your objects

        invaders = new ArrayList<Invader>();
        barriers = new ArrayList<Barrier>();
        tanks = new ArrayList<Tank>();

        projectiles = new ArrayList<Projectile>();

    }

    public void setup() {
        frameRate(60);

        int inv_x = 180;
        int inv_y = 80;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j ++) {
                invaders.add(new Invader(
                            loadImage("invader1.png"),
                            loadImage("invader2.png"),
                            loadImage("empty.png"),
                            inv_x, inv_y, 16, 16
                            ));
                inv_x += 30;
            }
            inv_y += 30;
            inv_x = 180;
        }

        int bar_x = 180;
        int bar_y = 400;
        for (int k = 0; k < 3; k++) {
            barriers.add(new Barrier(
                    loadImage("barrier_left1.png"),
                    loadImage("barrier_left2.png"),
                    loadImage("barrier_left3.png"),
                    loadImage("empty.png"),
                    bar_x, bar_y, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_right1.png"),
                    loadImage("barrier_right2.png"),
                    loadImage("barrier_right3.png"),
                    loadImage("empty.png"),
                    bar_x + 20, bar_y, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_top1.png"),
                    loadImage("barrier_top2.png"),
                    loadImage("barrier_top3.png"),
                    loadImage("empty.png"),
                    bar_x + 10, bar_y, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x, bar_y + 10, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x + 20, bar_y + 10, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x, bar_y + 20, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x + 20, bar_y + 20, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("empty.png"),
                    loadImage("empty.png"),
                    loadImage("empty.png"),
                    loadImage("empty.png"),
                    bar_x + 10, bar_y + 10, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("empty.png"),
                    loadImage("empty.png"),
                    loadImage("empty.png"),
                    loadImage("empty.png"),
                    bar_x + 10, bar_y + 20, 10, 10
            ));
            bar_x += 120;

        }

        tanks.add (new Tank(
                loadImage("tank1.png"),
                300, 450, 22, 14
        ));



    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        //Main Game Loop

        background(0);

        if (isAlive) {
            for (Invader inv : invaders) {
                inv.draw(this);
            }
            for (Barrier bar : barriers) {
                bar.draw(this);
            }
            for (Tank tank : tanks) {
                tank.draw(this);
            }
            for (Projectile ile : projectiles) {
                ile.draw(this);
            }
        }
        else {
            if (isDead) {
                gameOver();
            }
        }



    }

    private void shot() {
        projectiles.add(new Projectile(
                loadImage("projectile.png"),
                tanks.get(0).getX() + 10, 445, 1, 3
        ));
    }

    private void gameOver() {
        hints.add(new Hint(
                loadImage("gameover.png"),
                269, 240, 112, 16
        ));
    }

    public void keyPressed() {

        System.out.println(keyCode);
        if (keyCode == 39) {
            System.out.println("right");
            tanks.get(0).rightTick();
        }
        if (keyCode == 37) {
            System.out.println("left");
            tanks.get(0).leftTick();
        }
        if (keyCode == 32) {
            System.out.println("shot");
            shot();
        }
        if (keyCode == 192) {
            System.out.println("gameover");
            if (isAlive) {
                isAlive = false;
                isDead = true;
            }
            else {
                isAlive = true;
                isDead = false;
            }
        }


    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
