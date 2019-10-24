package invadem;

import java.util.List;
import java.util.ArrayList;

import invadem.objects.barriers.Barrier;
import invadem.objects.invaders.Invader;
import invadem.objects.tanks.Tank;
import processing.core.PApplet;

public class App extends PApplet {

    List<Invader> invaders;
    List<Barrier> barriers;
    List<Tank> tanks;

    public App() {
        //Set up your objects

        invaders = new ArrayList<Invader>();
        barriers = new ArrayList<Barrier>();
        tanks = new ArrayList<Tank>();

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

        tanks.add(new Tank(
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
        for(Invader inv : invaders) {
            inv.draw(this);
        }
        for(Barrier bar : barriers) {
            bar.draw(this);
        }
        for(Tank tank : tanks) {
            tank.draw(this);
        }


    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
