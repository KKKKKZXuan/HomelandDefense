package invadem;

import java.util.List;
import java.util.ArrayList;

import invadem.objects.invaders.Invader;
import processing.core.PApplet;

public class App extends PApplet {

    List<Invader> invaders;

    public App() {
        //Set up your objects

        invaders = new ArrayList<>();

    }

    public void setup() {
        frameRate(60);
        int num_x = 250;
        int num_y = 30;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j ++) {
                invaders.add(new Invader(
                            loadImage("invader1.png"),
                            loadImage("invader2.png"),
                            loadImage("empty.png"),
                            num_x, num_y, 16, 16
                            ));
                num_x += 20;
            }
            num_y += 20;
            num_x = 250;
        }
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

    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
