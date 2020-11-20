package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Speed extends Entity {
    public Speed(int x, int y) {
        super(x, y, Sprite.powerup_speed.getFxImage());
    }

    @Override
    public void update() {

    }
}
