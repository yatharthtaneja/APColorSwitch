package sample;

import javafx.scene.shape.Shape;

public abstract class Powerups implements SceneElements{
    private double Xpos;
    private double Ypos;
    private Shape Object;
    @Override
    public double getXpos(){
        return Xpos;
    }
    @Override
    public void setXpos(double x){
        if (Object!=null)
            Object.setLayoutX(x);
        Xpos=x;
    }
    @Override
    public double getYpos(){
        return Ypos;
    }
    @Override
    public void setYpos(double y){
        if (Object!=null)
            Object.setLayoutY(y);
        Ypos=y;
    }
    public Shape getObject(){
        return Object;
    }
    public void setObject(Shape shape){
        Object=shape;
    }
    public abstract void Collide();
}
