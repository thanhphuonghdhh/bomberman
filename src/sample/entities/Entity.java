package sample.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    public int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    public int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit;
        this.y = yUnit;
        this.img = img;
    }

    public void setPosition(int xBase, int yBase, Image img) {
        this.x = (x + xBase);
        this.y = (y + yBase);
        this.img = img;
    }

    public void swap(int xBase,int yBase,char[][] tiles){
        char tmp;
        //System.out.println(tiles[0][0]+" "+y+" "+x+" "+tiles[y][x]+" "+tiles[y+yBase][x+xBase]+"?");
        tmp=tiles[y][x];
        tiles[y][x]=tiles[y+yBase][x+xBase];
        tiles[y+yBase][x+xBase]=tmp;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public abstract void update();
}