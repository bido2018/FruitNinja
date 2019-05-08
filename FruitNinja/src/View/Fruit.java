package View;

import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Fruit {
int start_x;
int start_y;
int end_x;
int end_y;
double radius;
int speed;
Image image;
Random random = new Random();
Circle circle;
public Fruit () {
	this.start_x = 50+random.nextInt(400);
	this.start_y = 400;
	this.end_x = this.start_x+200;
	this.end_y = 25+random.nextInt(100);
	this.radius = 30;
	this.circle = new Circle(this.start_x,this.start_y,this.radius);
   this.speed= 3000+ random.nextInt(1500);
}
/**
 * @return the start_x
 */
public int getStart_x() {
	return start_x;
}
/**
 * @return the start_y
 */
public int getStart_y() {
	return start_y;
}
/**
 * @return the end_x
 */
public int getEnd_x() {
	return end_x;
}
/**
 * @return the end_y
 */
public int getEnd_y() {
	return end_y;
}
/**
 * @return the radius
 */
public double getRadius() {
	return radius;
}
/**
 * @return the image
 */
public Image getImage() {
	return image;
}
/**
 * @param start_x the start_x to set
 */
public void setStart_x(int start_x) {
	this.start_x = start_x;
}
/**
 * @param start_y the start_y to set
 */
public void setStart_y(int start_y) {
	this.start_y = start_y;
}
/**
 * @param end_x the end_x to set
 */
public void setEnd_x(int end_x) {
	this.end_x = end_x;
}
/**
 * @param end_y the end_y to set
 */
public void setEnd_y(int end_y) {
	this.end_y = end_y;
}
/**
 * @param radius the radius to set
 */
public void setRadius(int radius) {
	this.radius = radius;
}
/**
 * @param image the image to set
 */
public void setImage(Image image) {
	this.image = image;
	 this.circle.setFill(new ImagePattern (this.image));
}
public void move () {
	if(this.start_x<350) {
    final Timeline timeline = new Timeline();
     timeline.setCycleCount(Timeline.INDEFINITE);
     timeline.setAutoReverse(false);
     KeyValue xKV = new KeyValue(this.circle.centerXProperty(), this.end_x);
     KeyValue yKV = new KeyValue(this.circle.centerYProperty(), this.end_y, new Interpolator() {
         @Override
         protected double curve(double t) {
             return -4 * (t - .5) * (t - .5) + 1;

         }
     });
     
     KeyFrame xKF = new KeyFrame(Duration.millis(this.speed), xKV);
     KeyFrame yKF = new KeyFrame(Duration.millis(this.speed), yKV);
     timeline.getKeyFrames().addAll(xKF, yKF);
     timeline.play();

}

else 
{
	final Timeline timeline = new Timeline();
  timeline.setCycleCount(Timeline.INDEFINITE);
     timeline.setAutoReverse(false);
     KeyValue xKV = new KeyValue(this.circle.centerXProperty(), this.end_x-400);
     KeyValue yKV = new KeyValue(this.circle.centerYProperty(), this.end_y, new Interpolator() {
         @Override
         protected double curve(double t) {
             return -4 * (t - .5) * (t - .5) + 1;

         }
     });
     
     KeyFrame xKF = new KeyFrame(Duration.millis(this.speed), xKV);
     KeyFrame yKF = new KeyFrame(Duration.millis(this.speed), yKV);
     timeline.getKeyFrames().addAll(xKF, yKF);
     timeline.play();

}
}
}
