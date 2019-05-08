package View;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    int nums = 0;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        Group group = new Group();
        Scene scene = new Scene(group, 600, 350);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
        Image myimg = new Image ("Assets/ic_launcher.png");
        Fruit strawberry = new Fruit ();
        strawberry.setImage(myimg);
        
        Image appleimg = new Image ("file:///C:/Users/HP/eclipse-workspace/FruitNinja/src/Assets/apple.png");
        Fruit apple = new Fruit ();
        apple.setImage(appleimg);
        
        Image grapeimg = new Image ("file:///C:/Users/HP/eclipse-workspace/FruitNinja/src/Assets/grape.png");
        Fruit grape = new Fruit ();
        grape.setImage(grapeimg);
        System.out.println("straw start is "+strawberry.getEnd_y()+"\n"+ "apple is "+ apple.getEnd_y());
        Circle cursor = new Circle(1,1,1,Color.GREEN);

        strawberry.move();
        apple.move();
        grape.move();
        
        scene.setOnMouseDragged( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                cursor.setCenterX(event.getSceneX());
                cursor.setCenterY(event.getSceneY());
                
                if (cursor.getBoundsInParent().intersects(strawberry.circle.getBoundsInParent())) {
                    System.out.println("Hi");
                    group.getChildren().remove(strawberry);
                    

                }
              
            }
        });

        Line l = new Line(100, 300, 500, 300);
        l.setStroke(Color.AQUA);
        group.getChildren().addAll(strawberry.circle,apple.circle,l,cursor,grape.circle);
        }

    public static void main(String[] args) {
        launch(args);
    }

}