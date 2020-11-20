package sample.entities;

import javafx.scene.image.Image;
import sample.graphics.Sprite;

public class Portal extends Entity {
    public Portal(int x, int y) {
        super(x, y, Sprite.portal.getFxImage());
    }

    @Override
    public void update() {

    }
}
