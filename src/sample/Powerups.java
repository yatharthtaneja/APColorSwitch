package sample;

import javafx.scene.shape.Shape;

import java.io.Serializable;

public abstract class Powerups implements SceneElements, Serializable {
    private static final long serialVersionUID= 4l;
    private double Xpos;
    private double Ypos;
    private Shape Object;
    @Override
    public double getXpos(){
        return Xpos;
    }
    @Override
    public void setXpos(double x){
        Xpos=x;
    }
    @Override
    public double getYpos(){
        return Ypos;
    }
    @Override
    public void setYpos(double y){
        Ypos=y;
    }
    public Shape getObject(){
        return Object;
    }
    public void setObject(Shape shape){
        Object=shape;
    }
    public void incrementYpos(double change){
        if (Object!=null)
            Object.setLayoutY(Object.getLayoutY()+change);
        Ypos+=change;
    }
    public abstract void Collide();
}
