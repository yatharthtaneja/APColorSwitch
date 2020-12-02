package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Arc extends sample.Shape {
    public Arc(double centerx,double centery,int startangle){
        super(centerx,centery);
        javafx.scene.shape.Arc arc2 = new javafx.scene.shape.Arc();
        arc2.setRadiusX(105.0f);
        arc2.setRadiusY(105.0f);
        arc2.setStartAngle(startangle);
        arc2.setType(ArcType.ROUND);
        arc2.setLayoutY(centery);
        arc2.setLayoutX(centerx);
        arc2.setLength(90.0f);

        javafx.scene.shape.Arc arc1= new javafx.scene.shape.Arc();
        arc1.setLayoutY(centery);
        arc1.setLayoutX(centerx);
        arc1.setRadiusX(120.0f);
        arc1.setRadiusY(120.0f);
        arc1.setStartAngle(startangle);
        arc1.setType(ArcType.ROUND);
        arc1.setLength(90.0f);

        javafx.scene.shape.Shape shape = javafx.scene.shape.Shape.subtract(arc1, arc2);
        setShape(shape);
    }
    public Arc(double centerx,double centery,int startangle,Color col){
        super(centerx,centery,col);
        javafx.scene.shape.Arc arc2 = new javafx.scene.shape.Arc();
        arc2.setRadiusX(105.0f);
        arc2.setRadiusY(105.0f);
        arc2.setStartAngle(startangle);
        arc2.setType(ArcType.ROUND);
        arc2.setLayoutY(centery);
        arc2.setLayoutX(centerx);
        arc2.setLength(90.0f);

        javafx.scene.shape.Arc arc1= new javafx.scene.shape.Arc();
        arc1.setLayoutY(centery);
        arc1.setLayoutX(centerx);
        arc1.setRadiusX(120.0f);
        arc1.setRadiusY(120.0f);
        arc1.setStartAngle(startangle);
        arc1.setType(ArcType.ROUND);
        arc1.setLength(90.0f);

        javafx.scene.shape.Shape shape = javafx.scene.shape.Shape.subtract(arc1, arc2);
        shape.setFill(col);
        shape.setStroke(col);
        setShape(shape);
    }
    public Arc(double centerx,double centery,int startangle,double inner,double outer) {
        super(centerx,centery);
        javafx.scene.shape.Arc arc2 = new javafx.scene.shape.Arc();
        arc2.setRadiusX(inner);
        arc2.setRadiusY(inner);
        arc2.setStartAngle(startangle);
        arc2.setType(ArcType.ROUND);
        arc2.setLayoutY(centery);
        arc2.setLayoutX(centerx);
        arc2.setLength(90.0f);

        javafx.scene.shape.Arc arc1= new javafx.scene.shape.Arc();
        arc1.setLayoutY(centery);
        arc1.setLayoutX(centerx);
        arc1.setRadiusX(outer);
        arc1.setRadiusY(outer);
        arc1.setStartAngle(startangle);
        arc1.setType(ArcType.ROUND);
        arc1.setLength(90.0f);

        javafx.scene.shape.Shape shape = javafx.scene.shape.Shape.subtract(arc1, arc2);
        setShape(shape);
    }
    public Arc(double centerx,double centery,int startangle,double inner,double outer,Color col) {
        super(centerx,centery);
        javafx.scene.shape.Arc arc2 = new javafx.scene.shape.Arc();
        arc2.setRadiusX(inner);
        arc2.setRadiusY(inner);
        arc2.setStartAngle(startangle);
        arc2.setType(ArcType.ROUND);
        arc2.setLayoutY(centery);
        arc2.setLayoutX(centerx);
        arc2.setLength(90.0f);

        javafx.scene.shape.Arc arc1= new javafx.scene.shape.Arc();
        arc1.setLayoutY(centery);
        arc1.setLayoutX(centerx);
        arc1.setRadiusX(outer);
        arc1.setRadiusY(outer);
        arc1.setStartAngle(startangle);
        arc1.setType(ArcType.ROUND);
        arc1.setLength(90.0f);

        javafx.scene.shape.Shape shape = javafx.scene.shape.Shape.subtract(arc1, arc2);
        shape.setFill(col);
        shape.setStroke(col);
        setShape(shape);
        setShape(shape);
    }
}