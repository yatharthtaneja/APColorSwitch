package sample;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Star extends Powerups {
    public Star(double y,boolean darktheme){
        Image img;
        if(!darktheme)
            img=new Image("sample/Assets/star_black.gif");
        else
            img=new Image("sample/Assets/star.gif");

        ImagePattern imagepattern=new ImagePattern(img);
        Circle circle=new Circle();
        circle.setFill(imagepattern);
        circle.setRadius(15);
        circle.setCenterY(y);circle.setCenterX(225);
        setObject(circle);
    }
    @Override
    public void Collide() {
        AudioClip sound = new AudioClip(this.getClass().getResource("Star.wav").toString());
        sound.play();
    }
}
