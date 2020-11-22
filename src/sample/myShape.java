package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class myShape extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing Circle1

        Shape shape= MakeRectangle("#35e2f2",150,150,0);
        Shape shape2= MakeRectangle("#f6df0e",150,150,90);
        Shape shape3= MakeRectangle("#8c13fb",150,150,180);
        Shape shape4= MakeRectangle("#ff0080",150,150,270);

        //        Creating a Group object
        Group root = new Group(shape,shape2,shape3,shape4);
//Group root = new Group(arc1,arc2);
        //Creating a scene object
        Scene scene = new Scene(root, 450, 800);

        //Setting title to the Stage
        stage.setTitle("Subtraction Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public Shape MakeArc(String c1,double Centerx,double Centery,int Start_angle){

        Arc arc2 = new Arc();
        arc2.setRadiusX(85.0f);
        arc2.setRadiusY(85.0f);
        arc2.setStartAngle(Start_angle);
        arc2.setType(ArcType.ROUND);
//        arc2.setCenterX(Centerx);
//        arc2.setCenterY(Centery);
        arc2.setLayoutY(Centery);
        arc2.setLayoutX(Centerx);
        arc2.setLength(90.0f);
        arc2.setStroke(Color.valueOf(c1));

        Arc arc1= new Arc();
        //Setting the position of the circle
//        arc1.setCenterX(Centerx);
//        arc1.setCenterY(Centery);
        arc1.setLayoutY(Centery);
        arc1.setLayoutX(Centerx);
        arc1.setRadiusX(100.0f);
        arc1.setRadiusY(100.0f);
        arc1.setStartAngle(Start_angle);
        arc1.setType(ArcType.ROUND);
        arc1.setLength(90.0f);

        //Setting the color of the circle
        arc1.setFill(Color.DARKSLATEBLUE);

        //Performing subtraction operation on the circle
        Shape shape = Shape.subtract(arc1, arc2);

//        Setting the fill color to the result
        shape.setFill(Color.valueOf(c1));
        return shape;

    }
    public Shape MakePentagon(String s,double Centerx,double Centery,int Start_angle){
        SVGPath path = new SVGPath();
        path.setContent("M 30 80 L 30 80 L -30 140 L -30 160 L -10 160 L 50 100 A 10 10 90 0 0 30 80");
        path.setLayoutX(Centerx);
        path.setLayoutY(Centery);
        path.setFill(Color.valueOf(s));
        path.setRotate(Start_angle);
        return path;
    }
    public Shape MakeRectangle(String s1,double x, double y,int start_angle){
        javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(x,y,25,175);
        rect.setArcHeight(20);
        rect.setWidth(20);
        rect.setFill(Color.valueOf(s1));
        rect.rotateProperty().setValue(start_angle);
        rect.setStyle("-fx-arc-height: 20; -fx-arc-width: 20");
        return rect;

//            <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#ff0080" height="175.0" layoutY="62.0" stroke="BLACK" strokeType="INSIDE" strokeWidth="0.0" style="-fx-arc-height: 20; -fx-arc-width: 20;" width="25.0" />
    }
    public Shape MakeRectangle2(String s,double Xpos,double Ypos){
//        Shape shape;

        javafx.scene.shape.Rectangle shape = new javafx.scene.shape.Rectangle(Xpos,Ypos,112.5,12.5);

        shape.setFill(Color.web(s));
        shape.setStroke(Color.web(s));
        return shape;
    }
    public static void main(String args[]){
        launch(args);
    }
}