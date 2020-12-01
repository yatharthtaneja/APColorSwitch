package sample;

import javafx.scene.paint.Color;

public class Rectangle extends Shape{
    public Rectangle(double x, double y){
        super(x,y);
        javafx.scene.shape.Rectangle shape = new javafx.scene.shape.Rectangle(x,y,112.5,12.5);
        setShape(shape);
    }
    public Rectangle(double x, double y, Color col){
        super(x,y,col);
        javafx.scene.shape.Rectangle shape = new javafx.scene.shape.Rectangle(x,y,112.5,12.5);
        shape.setFill(col);
        shape.setStroke(col);
        setShape(shape);
    }
    public Rectangle(double x, double y,double width,double height){
        super(x,y);
        javafx.scene.shape.Rectangle shape = new javafx.scene.shape.Rectangle(x,y,width,height);
        setShape(shape);
    }
    public Rectangle(double x, double y,double width,double height, Color col){
        super(x,y,col);
        javafx.scene.shape.Rectangle shape = new javafx.scene.shape.Rectangle(x,y,width,height);
        shape.setFill(col);
        shape.setStroke(col);
        setShape(shape);
    }
    public Rectangle(double x, double y,double width,double height,int startangle,Color col){
        super(x,y,col);
        javafx.scene.shape.Rectangle shape = new javafx.scene.shape.Rectangle(x,y,width,height);
        shape.setFill(col);
        shape.setStroke(col);

        shape.setStyle("-fx-arc-height: 20; -fx-arc-width: 20");

        shape.rotateProperty().setValue(startangle);
        setShape(shape);
    }
}
