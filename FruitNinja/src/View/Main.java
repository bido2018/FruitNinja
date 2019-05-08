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
        Circle c = new Circle(500, 300, 16, Color.AQUA);
        Circle c1 = new Circle(100, 300, 16, Color.AQUA);

        Circle cursor = new Circle(1,1,1,Color.GREEN);


        scene.setOnMouseDragged( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                cursor.setCenterX(event.getSceneX());
                cursor.setCenterY(event.getSceneY());


                if (cursor.getBoundsInParent().intersects(c.getBoundsInParent())) {
                    System.out.println("Hi");
                    group.getChildren().remove(c);

                }
                if (cursor.getBoundsInParent().intersects(c1.getBoundsInParent())) {
                    System.out.println("Hi");
                    group.getChildren().remove(c1);

                }
            }
        });

        Line l = new Line(100, 300, 500, 300);
        l.setStroke(Color.AQUA);
        group.getChildren().addAll(c1,c, l,cursor);
        final Timeline timeline = new Timeline();
       // timeline.setCycleCount(Timeline.INDEFINITE);
        c1.setFill(new ImagePattern(new Image("Assets/ic_launcher.png")));
        timeline.setAutoReverse(false);
        Image image = new Image("Assets/ic_launcher.png");
        c.setFill(new ImagePattern(image));
        KeyValue xKV = new KeyValue(c.centerXProperty(), 100);
        KeyValue yKV = new KeyValue(c.centerYProperty(), 100, new Interpolator() {
            @Override
            protected double curve(double t) {
                System.out.println("circle centerx " + c.getCenterX());
                System.out.println("circle center y " + c.getCenterY());
               // System.out.println("line endy"  + l.getEndY());
              //  System.out.println("line end x " + l.getEndX());
                if(c.getCenterY() >= 290){
                    nums++;
                    System.out.println(nums);
                    if(c.getCenterX() < 200) {
                        group.getChildren().remove(c);
                    }
                }
                return -4 * (t - .5) * (t - .5) + 1;

            }
        });
        KeyValue xKV1 = new KeyValue(c1.centerXProperty(), 500);
        KeyValue yKV2 = new KeyValue(c1.centerYProperty(), 200, new Interpolator() {
            @Override
            protected double curve(double t) {
                // System.out.println("line endy"  + l.getEndY());
                //  System.out.println("line end x " + l.getEndX());
                if(c1.getCenterY() >= 290){
                    nums++;
                    System.out.println(nums);
                    if(c1.getCenterX() > 290) {
                        group.getChildren().remove(c1);
                    }
                }
                return -4 * (t - .5) * (t - .5) + 1;

            }
        });
        KeyFrame xKF = new KeyFrame(Duration.millis(20000), xKV);
        KeyFrame yKF = new KeyFrame(Duration.millis(20000), yKV);
        timeline.getKeyFrames().addAll(xKF, yKF);
        KeyFrame xKF1 = new KeyFrame(Duration.millis(20000), xKV1);
        KeyFrame yKF1 = new KeyFrame(Duration.millis(20000), yKV2);
        timeline.getKeyFrames().addAll(xKF1, yKF1);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}