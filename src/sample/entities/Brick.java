package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Brick extends Entity{
    public Brick(int x, int y) {
        super( x, y, Sprite.brick.getFxImage());
    }

    @Override
    public void update() {

    }


}
