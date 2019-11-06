package invadem;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import invadem.objects.barriers.Barrier;
import invadem.objects.hints.Hint;
import invadem.objects.invaders.Invader;
import invadem.objects.projectiles.InvProjectile;
import invadem.objects.projectiles.TankProjectile;
import invadem.objects.tanks.Tank;
import processing.core.PApplet;
import processing.core.PFont;

public class App extends PApplet {

    private List<Invader> invaders;
    private List<Barrier> barriers;
    private List<Tank> tanks;
    private List<TankProjectile> tankProjectiles;
    private List<InvProjectile> invProjectiles;
    private List<Hint> hints;
    private List<String> sentence;

    private Random rand;
    private int time = 0;
    private int xTrp = 0;
    private int yTrp = 0;
    private int level = 0;
    private int invShotTime = 300;
    private int numSec = 0;

    private boolean isDead = false;
    private boolean isNextLevel = false;
    private boolean takeIn = true;
    private boolean onceInRelease = true;
    private boolean isWarning = false;
    private int score = 0;
    private int highestScore = 10000;


    public App() {
        //Set up your objects

        invaders = new ArrayList<Invader>();
        barriers = new ArrayList<Barrier>();
        tanks = new ArrayList<Tank>();
        tankProjectiles = new ArrayList<TankProjectile>();
        invProjectiles = new ArrayList<InvProjectile>();
        hints = new ArrayList<Hint>();
        sentence = new ArrayList<String>();
        rand = new Random();


        sentence.add("YOU CAN DO IT!");
        sentence.add("Never give up!");
        sentence.add("Try your best!");
        sentence.add("You are the best one!");

    }

    public void setup() {
        frameRate(60);

        PFont myFont = createFont("PressStart2P-Regular.ttf", 10);
        textFont(myFont);

        level += 1;

        if (invShotTime > 60) {
            invShotTime = (6 - level) * 60;
        }
        else {
            invShotTime = 60;
        }


        System.out.println("Game is setting up");

        hints.clear();

        int inv_x = 150;
        int inv_y = 40;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 10; j++) {
                invaders.add(new Invader(
                        loadImage("invader1.png"),
                        loadImage("invader2.png"),
                        loadImage("invader1_armoured.png"),
                        loadImage("invader2_armoured.png"),
                        loadImage("invader1_power.png"),
                        loadImage("invader2_power.png"),
                        "armoured",
                        inv_x, inv_y, 16, 16
                ));
                inv_x += 30;
            }
            inv_y += 30;
            inv_x -= 300;
        }

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 10; j++) {
                invaders.add(new Invader(
                        loadImage("invader1.png"),
                        loadImage("invader2.png"),
                        loadImage("invader1_armoured.png"),
                        loadImage("invader2_armoured.png"),
                        loadImage("invader1_power.png"),
                        loadImage("invader2_power.png"),
                        "power",
                        inv_x, inv_y, 16, 16
                ));
                inv_x += 30;
            }
            inv_y += 30;
            inv_x -= 300;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                invaders.add(new Invader(
                        loadImage("invader1.png"),
                        loadImage("invader2.png"),
                        loadImage("invader1_armoured.png"),
                        loadImage("invader2_armoured.png"),
                        loadImage("invader1_power.png"),
                        loadImage("invader2_power.png"),
                        "regular",
                        inv_x, inv_y, 16, 16
                ));
                inv_x += 30;
            }
            inv_y += 30;
            inv_x -= 300;
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

            bar_x += 120;

        }

        tanks.add(new Tank(
                loadImage("tank1.png"),
                305, 450, 22, 16
        ));

    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        //Main Game Loop

        background(0);
        xTrp = time % 900 - 200;
        yTrp = 38;

        fill(255, 255, 255);
        text("Score: " + Integer.toString(score), 10, 20);
        text("Highest Score: " + Integer.toString(highestScore), 430, 20);

        if (isWarning) {
            if (time % 60 <= 30) {
                fill(255, 0, 0);
                text("WARNING: INVADERS ARE ARRIVING!", 165, 130);
            }
            if (time % 120 > 60) {
                fill(255, 0, 0);
                text("Score: " + Integer.toString(score), 10, 20);
                text("Highest Score: " + Integer.toString(highestScore), 430, 20);
            }
        }

        if (!isDead && !isNextLevel) {

            fill(255, 255, 255);

            if (tanks.size() > 0) {
                text("LIFE " + Integer.toString(tanks.get(0).getSustain()), 330, 20);
            }
            if (level > 0) {
                text("Now level: " + Integer.toString(level),160,20);
            }
            if (sentence.size() > 0) {
                if (xTrp == 649 || xTrp == -150) {
                    numSec = rand.nextInt(sentence.size());
//                    if ()
                }

                text(sentence.get(numSec),xTrp,yTrp);
            }

            if (tanks.size() == 0) {
                setup();
            }

            boolean haveInvShot = true;

            if (invaders.size() == 0) {
                nextLevel();
            }

            for (Invader inv : invaders) {
                inv.draw(this);
//                System.out.println(invShotTime);
                if (time % invShotTime == 0 && time != 0) {
                    if (haveInvShot) {
                        if (inv.type().equals("power")) {
                            invPowerShot();
                        }
                        else {
                            invShot();
                        }
                        haveInvShot = false;
                    }

                }

                if (inv.getY() > 380) {
                    gameOver();
                }

                if (inv.getY() > 300) {
                    isWarning = true;
                }

            }
            for (Barrier bar : barriers) {
                bar.draw(this);
            }
            for (Tank tank : tanks) {
                tank.draw(this);
            }
            for (TankProjectile ile : tankProjectiles) {
                ile.draw(this);
            }
            for (InvProjectile ile : invProjectiles) {
                ile.draw(this);
            }

            boolean isInvDisappear = false;

            pointA:

            for (Invader inv : invaders) {
                if (!isInvDisappear) {
                    for (TankProjectile ile : tankProjectiles) {
                        if (check_collection(ile, inv)) {
                            inv.getShot();
                            if (inv.isDead()) {
                                invaders.remove(inv);
                                if (inv.type().equals("regular")) {
                                    score += 100;
                                }
                                else {
                                    score += 250;
                                }
                            }
                            tankProjectiles.remove(ile);
                            isInvDisappear = true;
                            break pointA;
                        }
                    }
                }
            }

            boolean isTankDisappear = false;

            pointB:

            for (Tank tank : tanks) {
                if (!isTankDisappear) {
                    for (InvProjectile ile : invProjectiles) {
                        if (check_collection(ile, tank)) {
                            tank.reduceSustain();
                            invProjectiles.remove(ile);

                            if (tank.getSustain() == 0) {
                                gameOver();
                            }

                            isTankDisappear = true;
                            break pointB;
                        }
                    }
                }
            }

            boolean isBarDisappearByTank = false;

            pointC:

            for (Barrier bar : barriers) {
                if (!isBarDisappearByTank) {
                    for (TankProjectile ile : tankProjectiles) {
                        if (check_collection(ile, bar)) {
                            bar.reduceSustain();
                            tankProjectiles.remove(ile);

                            if (bar.getSustain() == 0) {
                                barriers.remove(bar);
                            }

                            isBarDisappearByTank = true;
                            break pointC;
                        }
                    }
                }
            }

            boolean isBarDisappearByInv = false;

            pointD:

            for (Barrier bar : barriers) {
                if (!isBarDisappearByInv) {
                    for (InvProjectile ile : invProjectiles) {
                        if (check_collection(ile, bar)) {
                            bar.reduceSustain();
                            invProjectiles.remove(ile);

                            if (bar.getSustain() == 0) {
                                barriers.remove(bar);
                            }

                            isBarDisappearByInv = true;
                            break pointD;
                        }
                    }
                }
            }

        }
        else {

            for (Hint hint : hints) {
                hint.draw(this);
            }

            if (takeIn) {
                isWarning = false;
                invaders.clear();
                barriers.clear();
                tanks.clear();
                tankProjectiles.clear();
                invProjectiles.clear();

                time = -50;
                takeIn = false;
            }
        }

        time += 1;

        if (isDead) {
//            System.exit(0);
            score = 0;
            invShotTime = 300;
            if (time % 60 < 30) {
                text("Press 'ENTER' to restart", 208, 400);
            }
        }

        if (isNextLevel && time == -1) {
            isNextLevel = false;
            takeIn = true;
            setup();
        }

        if (keyPressed) {
            if (keyCode == 39) {
//                System.out.println("right");
                if (tanks.size() > 0) {
                    tanks.get(0).rightTick();
                }
            } else if (keyCode == 37) {
//                System.out.println("left");
                if (tanks.size() > 0) {
                    tanks.get(0).leftTick();
                }
            } else if (key == ' ') {
//                System.out.println("shot");
                if (tanks.size() > 0) {
                    if (onceInRelease) {
                        tankShot();
                        onceInRelease = false;
                    }
                }
            } else if (key == 'd') {
                if (onceInRelease) {
                    invaders.clear();
                    score += 7000;
                    onceInRelease = false;
                }
            } else if (key == '\n') {
                if (onceInRelease) {
                    if (isDead) {
                        isDead = false;
                        takeIn = true;
                    }
                    onceInRelease = false;
                }
            }
        }


    }

    private boolean check_collection(TankProjectile a, Invader b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean check_collection(InvProjectile a, Tank b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean check_collection(TankProjectile a, Barrier b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean check_collection(InvProjectile a, Barrier b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }


    private void tankShot() {

        if (!isDead) {
            tankProjectiles.add(new TankProjectile(
                    loadImage("projectile.png"),
                    tanks.get(0).getX() + 10, 445, 1, 3
            ));
        }
    }

    private void invShot() {
        int randomInv = rand.nextInt(invaders.size());

        invProjectiles.add(new InvProjectile(
                loadImage("projectile.png"),
                invaders.get(randomInv).getX() + 7, invaders.get(randomInv).getY() + 7, 1, 3
        ));
    }

    private void invPowerShot() {
        int randomInv = rand.nextInt(invaders.size());

        invProjectiles.add(new InvProjectile(
                loadImage("projectile_lg.png"),
                invaders.get(randomInv).getX() + 7, invaders.get(randomInv).getY() + 7, 2, 5
        ));
    }

    private void gameOver() {

        isDead = true;

        hints.add(new Hint(
                loadImage("gameover.png"),
                269, 240, 112, 16
        ));

        level = 0;

        if (score > highestScore) {
            highestScore = score;
        }

    }

    private void nextLevel() {

        isNextLevel = true;

        hints.add(new Hint(
                loadImage("nextlevel.png"),
                269, 240, 112, 16
        ));
    }

    public void keyReleased() {
        if (!onceInRelease) {
            onceInRelease = true;
        }
    }


    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
