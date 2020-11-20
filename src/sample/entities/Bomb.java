package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Bomb extends Entity {
    int Count = 0;
    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb.getFxImage());
    }

    @Override
    public void update() {


    }
}

