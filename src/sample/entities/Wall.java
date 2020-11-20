package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y, Sprite.wall.getFxImage());
    }

    @Override
    public void update() {

    }
}