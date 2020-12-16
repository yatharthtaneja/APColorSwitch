package sample;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ColourBooster extends Powerups {
    ColourBooster(double y){
        Image img=new Image("sample/Assets/colourbooster.png");
        ImagePattern imagepattern=new ImagePattern(img);
        javafx.scene.shape.Circle circle=new Circle();
        circle.setFill(imagepattern);
        circle.setRadius(15);
        circle.setCenterY(y);circle.setCenterX(225);
        setObject(circle);
        setYpos(y);
    }
    @Override
    public void Collide() {
        if(SoundOn){
            AudioClip sound = new AudioClip(this.getClass().getResource("ColourBooster.wav").toString());
            sound.play();
        }
    }
}
