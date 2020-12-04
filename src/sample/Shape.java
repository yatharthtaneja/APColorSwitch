package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

import java.io.Serializable;

public class Shape implements Serializable {
    private static final long serialVersionUID= 3l;
    private double Xpos;
    private double Ypos;
    private Color Colour;
    private javafx.scene.shape.Shape shape;
    Shape(double x,double y){
        Xpos=x;
        Ypos=y;
    }
    Shape(double x,double y,Color colour){
        Xpos=x;
        Ypos=y;
        Colour=colour;
    }
    public double getXpos(){
        return Xpos;
    }
    public void setXpos(double x){
        Xpos=x;
    }
    public double getYpos(){
        return Ypos;
    }
    public void setYpos(double y){
        Ypos=y;
    }
    public Color getColour(){
        return Colour;
    }
    public void setColour(Color colour){
        Colour=colour;
    }
    public javafx.scene.shape.Shape getShape(){
        return shape;
    }
    public void setShape(javafx.scene.shape.Shape shape){
        this.shape=shape;
    }
}

