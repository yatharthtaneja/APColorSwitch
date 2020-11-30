package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Star extends Powerups {
    public Star(double y){
        Image img=new Image("sample/Assets/star.png");
        ImagePattern imagepattern=new ImagePattern(img);
        Circle circle=new Circle();
        circle.setFill(imagepattern);
        circle.setRadius(15);
        circle.setCenterY(y);circle.setCenterX(225);
        setObject(circle);
    }
    @Override
    public void Collide() {

    }
}
