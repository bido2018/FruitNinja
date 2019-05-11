package View;

import javafx.geometry.Rectangle2D;

public class FruitFactory {
	  private Rectangle2D bounds;
    public Rectangle2D getBounds() {
        return bounds;
    
    }

    public void setBounds(Rectangle2D bounds) {
        this.bounds = bounds;
    }

  
    public FruitInterfaceFactory getFruit(String fruit)
    {
        if(fruit.equals("apple"))
            return new Apple(bounds);
        else if(fruit.equals("grape"))
            return new Grape(bounds);
        else
            return new Strawberry(bounds);
    }
}
