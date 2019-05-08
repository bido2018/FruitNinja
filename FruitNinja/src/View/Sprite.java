package View;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Sprite extends Node {
    Sprite sprite;
    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;


    public void display(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(5));
        translateTransition.setToY(800);
        translateTransition.setNode(sprite);
        translateTransition.play();
    }

    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    public Sprite() {
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }

    public Sprite(Image imagee) {
        this.image = imagee;
        this.positionX = 0;
        this.positionY = 0;
        this.width = imagee.getWidth();
        this.height = imagee.getHeight();
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }



    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }
}