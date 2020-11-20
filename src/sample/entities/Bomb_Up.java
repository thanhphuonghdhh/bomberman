package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Bomb_Up extends Entity {
    int Count = 0;
    public Bomb_Up(int x, int y) {
        super(x, y, Sprite.powerup_bombs.getFxImage());
    }

    @Override
    public void update() {


    }
}

