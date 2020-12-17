package sample.Shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import sample.Shapes.Shape;

public class Pentagon extends Shape {
    public Pentagon(double x,double y,int startangle){
        super(x,y);
        SVGPath path = new SVGPath();
        path.setContent("M 30 80 L 30 80 L -30 140 L -30 160 L -10 160 L 50 100 A 10 10 90 0 0 30 80");
        path.setLayoutX(x);
        path.setLayoutY(y);
        path.setRotate(startangle);
        setShape(path);
    }
    public Pentagon(double x,double y,int startangle,Color col){
        super(x,y,col);
        SVGPath path = new SVGPath();
        path.setContent("M 30 80 L 30 80 L -30 140 L -30 160 L -10 160 L 50 100 A 10 10 90 0 0 30 80");
        path.setLayoutX(x);
        path.setLayoutY(y);
        path.setFill(col);
        path.setStroke(col);
        path.setRotate(startangle);
        setShape(path);
    }
}
