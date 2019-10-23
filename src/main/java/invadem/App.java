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

        invaders.add(new Invader(loadImage("invader1.png"), 30, 30,16, 16));
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
