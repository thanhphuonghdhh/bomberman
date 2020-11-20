package sample.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sample.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
    public boolean isValidMove(int xBase, int yBase,char tiles[][]){
        //System.out.println(x+" "+y);
        return ((x+xBase)>0 && (y+yBase)>0 && tiles[y+yBase][x+xBase]==' ');
    }


}