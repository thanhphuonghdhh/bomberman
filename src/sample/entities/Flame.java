package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Flame extends Entity {
    public Flame(int x, int y) {
        super(x, y, Sprite.powerup_flames.getFxImage());
    }

    @Override
    public void update() {

    }
}

