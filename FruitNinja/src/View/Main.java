package View;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    int nums = 0;
    int score =0;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        Pane group = new Pane();
        Scene scene = new Scene(group, 600, 350);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
        Image imagecur = new Image("file:///C:/Users/HP/eclipse-workspace/FruitNinja/src/Assets/blade.png");
    	scene.setCursor(new ImageCursor(imagecur));
    	
    	Fruit [] fruits = new Fruit[15];
    	 Image myimg = new Image ("Assets/ic_launcher.png");
           
         Image appleimg = new Image ("file:///C:/Users/HP/eclipse-workspace/FruitNinja/src/Assets/apple.png");
   
         
         Image grapeimg = new Image ("file:///C:/Users/HP/eclipse-workspace/FruitNinja/src/Assets/grape.png");
        Label scorelbl = new Label();
         scorelbl.setText("your score is  :  "+score) ; 
         group.getChildren().add(0, scorelbl);
         Line l = new Line(100, 300, 500, 300);
         l.setStroke(Color.AQUA);
         int i=0;
    	for( i=0;i<fruits.length;i++) {
        		fruits[i]=new Fruit();
        		fruits[i].setImage(myimg);
        		i++;
        		fruits[i]=new Fruit();
        		fruits[i].setImage(appleimg);
        		i++;
        		fruits[i]=new Fruit();
        		fruits[i].setImage(grapeimg);
        		}
    	
    	
    	for( i=0;i<fruits.length;i++) {
    		fruits[i].move();            
    	}
        Circle cursor = new Circle(1,1,1,Color.GREEN);
        group.getChildren().addAll(l,cursor);
    	for(i=0;i<fruits.length;i++) {
    		System.out.println(i);
        	group.getChildren().add(fruits[i].circle);

    	}
    


        scene.setOnMouseDragged( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                cursor.setCenterX(event.getSceneX());
                cursor.setCenterY(event.getSceneY());
                for(int i=0;i<fruits.length;i++) {
                if (cursor.getBoundsInParent().intersects(fruits[i].circle.getBoundsInParent())) {
                    group.getChildren().remove(fruits[i].circle);
                    fruits[i].circle.setDisable(true);
                    score++;
                    scorelbl.setText("your score is  :  "+score) ; 

                }
                }
              
            }
        });

    
        }

    public static void main(String[] args) {
        launch(args);
    }

}