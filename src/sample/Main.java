package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.entities.*;
import sample.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private final String pathToMap = "level1.txt";
    public Entity bomberman;
    Entity bomb;
    public char[][] tiles;
    public int prevX = -2, prevY = -2;
    public static void main(String[] args) {
        Application.launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long c) {
                render();
                update();
            }
        };
        timer.start();

        createMap();


        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int X = bomberman.x;
                int Y = bomberman.y;
                switch (event.getCode()) {
                    case SPACE:
                        bomb = new Bomb(X, Y);
                        entities.add(bomb);
                        break;
                    case UP:
                        if (((Bomber) bomberman).isValidMove(0, -1, tiles)) {
                            bomberman.swap(0, -1, tiles);
                            bomberman.setPosition(0, -1, (prevX == 0 && prevY == -1) ?
                                    Sprite.player_up_1.getFxImage() : Sprite.player_up_2.getFxImage());
                            prevX = 0;
                            prevY = -1;
                        }
                        break;
                    case DOWN:
                        if (((Bomber) bomberman).isValidMove(0, 1, tiles)) {
                            bomberman.swap(0, 1, tiles);
                            bomberman.setPosition(0, 1, (prevX == 0 && prevY == 1) ?
                                    Sprite.player_down_1.getFxImage() : Sprite.player_down_2.getFxImage());
                            prevX = 0;
                            prevY = 1;
                        }
                        break;
                    case LEFT:
                        if (((Bomber) bomberman).isValidMove(-1, 0, tiles)) {
                            bomberman.swap(-1, 0, tiles);
                            bomberman.setPosition(-1, 0, (prevX == -1 && prevY == 0) ?
                                    Sprite.player_left_1.getFxImage() : Sprite.player_left_2.getFxImage());
                            prevX = -1;
                            prevY = 0;
                        }

                        break;
                    case RIGHT:
                        if (((Bomber) bomberman).isValidMove(1, 0, tiles)) {
                            bomberman.swap(1, 0, tiles);
                            bomberman.setPosition(1, 0, (prevX == 1 && prevY == 0) ?
                                    Sprite.player_right_1.getFxImage() : Sprite.player_right_2.getFxImage());
                            prevX = 1;
                            prevY = 0;
                        }
                        break;

                }
                /*System.out.println();
                for(int i=0;i<13;i++) {
                    for (int j = 0; j < 31; j++)
                        System.out.print(tiles[i][j] + " ");
                    System.out.println();
                }
                System.out.println();*/
            }

        });

    }

    public void createMap() {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(pathToMap)));
            int level = scanner.nextInt();
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            tiles = new char[row][column];

            //static object
            for (int i = 0; i < row; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < column; j++) {
                    Entity object;
                    tiles[i][j] = line.charAt(j);
                    System.out.print(line.charAt(j));
                    switch (line.charAt(j)) {
                        case '#':
                            object = new Wall(j, i);
                            stillObjects.add(object);
                            break;
                        case '*':
                            object = new Brick(j, i);
                            stillObjects.add(object);
                            break;
                        case 'x':
                            //object = new Portal(j, i);
                            //stillObjects.add(object);
                            object= new Brick(j,i);
                            stillObjects.add(object);
                            break;

                        case 'b':
                            //object = new Bomb_Up(j, i);
                            //stillObjects.add(object);
                            object= new Brick(j,i);
                            stillObjects.add(object);
                            break;
                        case 'f':
                            //object = new Flame(j, i);
                            //stillObjects.add(object);
                            object= new Brick(j,i);
                            stillObjects.add(object);
                            break;
                        case 's':
                            //object = new Speed(j, i);
                            //stillObjects.add(object);
                            object= new Brick(j,i);
                            stillObjects.add(object);
                            break;
                        default:
                            object = new Grass(j, i);
                            stillObjects.add(object);
                            break;
                    }
                }
                System.out.println();
            }

            //dynamic object
            for (int i = 0; i < row; i++)
                for (int j = 0; j < column; j++) {
                    Entity object;
                    switch (tiles[i][j]) {
                        case '1':
                            object = new Balloon(j, i, Sprite.balloom_right1.getFxImage());
                            entities.add(object);
                            break;
                        case '2':
                            object = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                            entities.add(object);
                            break;
                        case 'p':
                            bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                            entities.add(bomberman);
                            break;

                    }
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }


}