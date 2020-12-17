package sample.Shapes;

import javafx.scene.paint.Color;
import sample.Shapes.Shape;

public class Circle extends Shape {
    public Circle(double x,double y,double radius){
        super(x,y);
        javafx.scene.shape.Shape shape=new javafx.scene.shape.Circle(x,y,radius);
        setShape(shape);
    }
    public Circle(double x, double y,double radius,Color col){
        super(x,y,col);
        javafx.scene.shape.Shape shape=new javafx.scene.shape.Circle(x,y,radius,col);
        shape.setStroke(col);
        shape.setFill(col);
        setShape(shape);
    }
}
