package invadem;

import invadem.objects.barriers.Barrier;
import invadem.objects.hints.Hint;
import invadem.objects.invaders.Invader;
import invadem.objects.projectiles.InvProjectile;
import invadem.objects.projectiles.TankProjectile;
import invadem.objects.tanks.Tank;
import processing.core.PApplet;
import processing.core.PFont;
//import processing.sound.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class App extends PApplet {

    private List<Invader> invaders;
    private List<Barrier> barriers;
    private List<Tank> tanks;
    private List<TankProjectile> tankProjectiles;
    private List<InvProjectile> invProjectiles;
    private List<Hint> hints;
    private List<String> sentence;

    private Random rand;
    private int time;
    private int xTrp;
    private int yTrp;
    private int level;
    private int invShotTime;
    private int numSec;
    private int status;
    private int score;
    private int highestScore;
    private int playerMode;

    private boolean onceInRelease;
    private boolean isWarning;
    private boolean leftChose;
    private boolean rightChose;

    public App() {
        //Set up your objects

        invaders = new ArrayList<>();
        barriers = new ArrayList<>();
        tanks = new ArrayList<>();
        tankProjectiles = new ArrayList<>();
        invProjectiles = new ArrayList<>();
        hints = new ArrayList<>();
        sentence = new ArrayList<>();
        rand = new Random();

        time = 0;
        xTrp = 0;
        yTrp = 0;
        level = 0;
        invShotTime = 300;
        numSec = 0;
        status = 3;
        score = 0;
        highestScore = 10000;

        onceInRelease = true;
        isWarning = false;
        leftChose = false;
        rightChose = false;

        sentence.add("YOU CAN DO IT!");
        sentence.add("Never give up!");
        sentence.add("Try your best!");
        sentence.add("You are the best one!");

    }

    public void setup() {

        System.out.println("Game is setting up");

        frameRate(60);

        PFont myFont = createFont("PressStart2P-Regular.ttf", 10);
        textFont(myFont);

        time = 0;
        invaders.clear();
        barriers.clear();
        tanks.clear();
        tankProjectiles.clear();
        invProjectiles.clear();
        hints.clear();
        level += 1;

        if (invShotTime > 60) {
            invShotTime = (6 - level) * 60;
        }
        else {
            invShotTime = 60;
        }

//        SoundFile musicfile = new SoundFile(this, "sample.mp3");
//        musicfile.play();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////// Set Invader //////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////// Set Barrier //////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////// Set Tanks ///////////////////////////////////////////////////////

        if (playerMode == 1) {
            tanks.add(new Tank(
                    loadImage("tank1.png"),
                    305, 450, 22, 16
            ));
        }
        else if (playerMode == 2) {
            tanks.add(new Tank(
                    loadImage("tank1.png"),
                    425, 450, 22, 16
            ));
            tanks.add(new Tank(
                    loadImage("tank1.png"),
                    185, 450, 22, 16
            ));
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

        if (status != 3) {
            text("Score: " + score, 10, 20);
            text("Highest Score: " + highestScore, 430, 20);
        }

        if (isWarning) {
            if (time % 60 <= 30) {
                fill(255, 0, 0);
                text("WARNING: INVADERS ARE ARRIVING!", 165, 130);
            }
            if (time % 120 > 60) {
                fill(255, 0, 0);
                text("Score: " + score, 10, 20);
                text("Highest Score: " + highestScore, 430, 20);
            }
        }

        if (status == 0) {

            fill(255, 255, 255);

            if (playerMode == 1) {
                if (tanks.get(0).getSustain() > 2) {
                    fill(0, 255, 0);
                    text("LIFE " + tanks.get(0).getSustain(), 330, 20);
                }
                else if (tanks.get(0).getSustain() == 2) {
                    fill(255, 255, 0);
                    text("LIFE " + tanks.get(0).getSustain(), 330, 20);
                }
                else {
                    fill(255, 0, 0);
                    if (time % 30 < 15) {
                        text("LIFE " + tanks.get(0).getSustain(), 330, 20);
                    }
                }
                fill(255, 255, 255);
                text("Now level: " + level,160,20);
            }
            else if (playerMode == 2) {
                if (tanks.get(1).getSustain() > 2) {
                    fill(0, 255, 0);
                    text("P1L " + tanks.get(1).getSustain(), 150, 20);
                }
                else if (tanks.get(1).getSustain() == 2) {
                    fill(255, 255, 0);
                    text("P1L " + tanks.get(1).getSustain(), 150, 20);
                }
                else {
                    fill(255, 0, 0);
                    if (time % 30 < 15) {
                        text("P1L " + tanks.get(1).getSustain(), 150, 20);
                    }
                }

                if (tanks.get(0).getSustain() > 2) {
                    fill(0, 255, 0);
                    text("P2L " + tanks.get(0).getSustain(), 360, 20);
                }
                else if (tanks.get(0).getSustain() == 2) {
                    fill(255, 255, 0);
                    text("P1L " + tanks.get(0).getSustain(), 360, 20);
                }
                else {
                    fill(255, 0, 0);
                    if (time % 30 < 15) {
                        text("P2L " + tanks.get(0).getSustain(), 360, 20);
                    }
                }

                fill(255, 255, 255);
                text("Now level: " + level,220,20);
            }


            if (sentence.size() > 0) {
                if (xTrp == 649 || xTrp == -150) {
                    numSec = rand.nextInt(sentence.size());
//                    if ()
                }
                text(sentence.get(numSec),xTrp,yTrp);
            }

            boolean haveInvShot = true;

            if (invaders.size() == 0) {
                status = 2;
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
                    status = 1;
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
                                status = 1;
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

        else if (status == 1) {
//            System.exit(0);
            isWarning = false;
            gameOver();
            for (Hint hint : hints) {
                hint.draw(this);
            }
            score = 0;
            invShotTime = 300;
            if (time % 60 < 30) {
                text("Press 'ENTER' to restart", 208, 400);
            }
        }
        else if (status == 2) {
            isWarning = false;
            nextLevel();
            for (Hint hint : hints) {
                hint.draw(this);
            }
            if (time > 0) {
                time = -120;
            }
            if (time == -1) {
                setup();
                status = 0;
            }

        }
        else if (status == 3) {

            fill(255, 0, 0);
            textSize(25);
            text("Invadem", 225, 150);

            fill(255, 255, 255);
            textSize(10);
            if (time % 120 < 60) {
                text("Have a choose", 250, 260);
            }

            if ((mouseX > 140 && mouseX < 260 && mouseY > 320 && mouseY < 350 )|| keyCode == 37) {
                fill(0, 255, 0);
                leftChose = true;
                rightChose = false;
                if (mousePressed) {
                    playerMode = 1;
                    level = 0;
                    leftChose = false;
                    setup();
                    status = 0;
                }
            }
            else {
                fill(255, 255, 255);
            }

            if (leftChose &&( key == '\n')) {
                playerMode = 1;
                level = 0;
                leftChose = false;
                setup();
                status = 0;
            }

            text("One Player", 150, 330);
            fill(255, 255, 255);



            if ((mouseX > 360 && mouseX < 480 && mouseY > 320 && mouseY < 350 )|| keyCode == 39) {
                fill(0, 255, 0);
                rightChose = true;
                leftChose = false;
                if (mousePressed) {
                    playerMode = 2;
                    level = 0;
                    rightChose = false;
                    setup();
                    status = 0;
                }
            }
            else {
                fill(255, 255, 255);
            }

            if (rightChose &&( key == '\n')) {

                playerMode = 2;
                level = 0;
                rightChose = false;
                setup();
                status = 0;
            }

            text("Two Players", 380, 330);
            fill(255, 255, 255);

        }

        time += 1;



        if (keyPressed) {

            if (keyCode == 39) {
//                System.out.println("right");
                if (status == 0) {
                    if (tanks.get(0).getX() < 580) {
                        tanks.get(0).rightTick();
                    }
                }
            } else if (keyCode == 37) {
//                System.out.println("left");
                if (status == 0) {
                    if (tanks.get(0).getX() > 60) {
                        tanks.get(0).leftTick();
                    }
                }
            } else if (keyCode == 40) {
//                System.out.println("shot");
                if (status == 0) {
                    if (onceInRelease) {
                        tankShot(0);
                        onceInRelease = false;
                    }
                }
            }else if (key == 'd') {
//                System.out.println("right");
                if (tanks.size() > 1) {
                    if (status == 0) {
                        if (tanks.get(1).getX() < 580) {
                            tanks.get(1).rightTick();
                        }
                    }
                }
            } else if (key == 'a') {
//                System.out.println("left");
                if (tanks.size() > 1) {
                    if (status == 0) {
                        if (tanks.get(1).getX() > 60) {
                            tanks.get(1).leftTick();
                        }
                    }
                }
            } else if (key == 's') {
//                System.out.println("shot");
                if (tanks.size() > 1) {
                    if (status == 0) {
                        if (onceInRelease) {
                            tankShot(1);
                            onceInRelease = false;
                        }
                    }
                }
            } else if (key == 'f') {
                if (onceInRelease) {
                    invaders.clear();
                    onceInRelease = false;
                }
            } else if (key == 'o') {
                if (onceInRelease) {
                    status = 1;
                    onceInRelease = false;
                }
            } else if (key == '\n') {
                if (onceInRelease) {
                    if (status != 0) {
                        setup();
                        status = 0;
                    }
                    onceInRelease = false;
                }
            }
            System.out.println("keyCode in pressed: " + keyCode);
//            System.out.println("key in pressed \"" + key + "\"");
        }

    }

    protected boolean check_collection(TankProjectile a, Invader b) {
        return (a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY());
    }

    protected boolean check_collection(InvProjectile a, Tank b) {
        return (a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY());
    }

    protected boolean check_collection(InvProjectile a, Invader b) {

        return false;

    }

    protected boolean check_collection(TankProjectile a, Barrier b) {
        return (a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY());
    }

    protected boolean check_collection(InvProjectile a, Barrier b) {
        return (a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY());
    }

    protected void setStatus(int i) {
        status = i;
    }

    protected int getStatus() {
        return status;
    }

    protected List<TankProjectile> getTankProjectiles() {
        return tankProjectiles;
    }

    protected void tankShot(int numTank) {

        if (status == 0) {
            tankProjectiles.add(new TankProjectile(
                    loadImage("projectile.png"),
                    tanks.get(numTank).getX() + 10, 445, 1, 3
            ));
        }
    }

    private void invShot() {

        if (status == 0) {
            int randomInv = rand.nextInt(invaders.size());

            invProjectiles.add(new InvProjectile(
                    loadImage("projectile.png"),
                    invaders.get(randomInv).getX() + 7, invaders.get(randomInv).getY() + 7, 1, 3
            ));
        }
    }

    private void invPowerShot() {

        if (status == 0) {
            int randomInv = rand.nextInt(invaders.size());

            invProjectiles.add(new InvProjectile(
                    loadImage("projectile_lg.png"),
                    invaders.get(randomInv).getX() + 7, invaders.get(randomInv).getY() + 7, 2, 5
            ));
        }
    }

    private void gameOver() {

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
