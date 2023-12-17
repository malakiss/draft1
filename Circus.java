package circus;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Circus implements World {

    private List<GameObject> constants;  //bar
    private List<GameObject> movings ;  // shapes
    private List<GameObject> controls;  //clowns
    private int controlSpeed = 20;
    private int speed = 40;
    private String status;
    private int width = 800;
    private int height = 700;
    private DifficultyStrategy d;
    private CircusBuilder cb;
    private int lives = 4;
    private Stack leftStack;
    private Stack rightStack;
///variables for testing
    ShapeObject p;
    ShapeObject b;
    ShapeObject pl;
    ShapeObject bl;
    ShapeFactory factory;
    Clown clown = new Clown(300,height-200);
////// till here
    public Circus(Difficulty diff) {

        cb = new DefaultBuilder(diff,width,height);
        factory = ShapeFactory.getFactoryInstance();
    }

    public void constructCircus() {

        this.clown=cb.buildClown();
        this.constants = cb.buildConstants();
        this.controls = cb.buildControl();
        this.movings = cb.buildMoving();
        d = cb.buildStrategy();

    }

    public void setStrategy(DifficultyStrategy s) {
        this.d = s;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constants;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movings;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controls;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean refresh() {
        for (GameObject moving : movings) {

            ShapeObject s = (ShapeObject) moving;
            s.movedown();
        }

        return true;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getControlSpeed() {
        return controlSpeed;
    }

    public void setdifficulty() {

    }

    public static void main(String[] args) {
        Circus circus = new Circus(Difficulty.EASY);
        circus.constructCircus();
        GameEngine.start("game", circus);
    }
}
