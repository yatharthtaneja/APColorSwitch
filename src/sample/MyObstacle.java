package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyObstacle extends Application {
    @Override
    public void start(Stage stage) {


        Group root1= MakeringObstacle(225,250);
        Group root3= MakeSquareTrap(225,150);
        Group root2=MakeCross(225,150);
        Group root = new Group(root1,root2,root3);
        setarcY(root1,50);
        rotateTransition(root2,5);

//        System.out.println(root.getLayoutY());
//        System.out.println(root.getChildren().get(3).getLayoutY());
        RotateArc(root1,360,0,4,225,300);

        Scene scene = new Scene(root, 450, 800);
        stage.setTitle("Obstacle Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public Group MakeringObstacle(double Centerx, double Centery){

        myShape shape = new myShape();

        Shape s1=shape.MakeArc("#35e2f2",Centerx,Centery,0);
        Shape s2=shape.MakeArc("#f6df0e",Centerx,Centery,90);
        Shape s3=shape.MakeArc("#8c13fb",Centerx,Centery,180);
        Shape s4=shape.MakeArc("#ff0080",Centerx,Centery,270);

        Group group = new Group(s1,s2,s3,s4);
        return group;

    }
    public void rotateTransition(Group g1,int duration){
        RotateTransition rt = new RotateTransition();
        rt.setAxis(Rotate.Z_AXIS);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setDuration(Duration.seconds(duration));
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setNode(g1);
        rt.play();
    }
    public void setarcY(Group g1,double Centery){
        System.out.println(g1.getChildren().get(0).getLayoutX());
        g1.getChildren().get(0).setTranslateY(Centery);
        g1.getChildren().get(1).setTranslateY(Centery);
        g1.getChildren().get(2).setTranslateY(Centery);
        g1.getChildren().get(3).setTranslateY(Centery);
    }


    public void RotateArc(Group a1, int start, int end, int duration , double centerx , double centery) {

        Rotate rt2 = new Rotate(0,centerx,centery);
        a1.getTransforms().add(rt2);
        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rt2.angleProperty(),start)),
        new KeyFrame(Duration.seconds(duration), new KeyValue(rt2.angleProperty(),end)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public Group MakeCross(double X , double Y){
        myShape shape= new myShape();
        Shape shape1= shape.MakePentagon("#35e2f2",X+42.5,Y-42.5,0);
        Shape shape2= shape.MakePentagon("#f6df0e",X+42.5,Y+42.5,90);
        Shape shape3= shape.MakePentagon("#8c13fb",X-42.5,Y+42.5,180);
        Shape shape4= shape.MakePentagon("#ff0080",X-42.5,Y-42.5,270);
        Group group = new Group(shape1,shape2,shape3,shape4);
        return group;
    }
    public Group MakeSquareTrap(double x, double y){
        myShape shape= new myShape();
        Shape shape1= shape.MakeRectangle("#35e2f2",x+87.5,y,0);
        Shape shape2= shape.MakeRectangle("#f6df0e",x,y+87.5,90);
        Shape shape3= shape.MakeRectangle("#8c13fb",x-87.5,y,180);
        Shape shape4= shape.MakeRectangle("#ff0080",x,y-87.5,270);
        Group group = new Group(shape1,shape2,shape3,shape4);
        return group;
    }
    public Group MakeLine2a(double Y) {
        myShape shape = new myShape();
        Shape shape1 = shape.MakeRectangle2("#35e2f2", 0, Y);
        Shape shape2 = shape.MakeRectangle2("#f6df0e", 112.5, Y);
        Shape shape3 = shape.MakeRectangle2("#8c13fb", 225, Y);
        Shape shape4 = shape.MakeRectangle2("#ff0080", 337.5, Y);
        Shape shape5 = shape.MakeRectangle2("#35e2f2", 450, Y);
        Shape shape6 = shape.MakeRectangle2("#f6df0e", 562.5, Y);
        Shape shape7 = shape.MakeRectangle2("#8c13fb", 675, Y);
        Shape shape8 = shape.MakeRectangle2("#ff0080", 787.5, Y);
        Group group = new Group(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8);
        return group;
    }
    public Group MakeLine2b(double Y) {
        myShape shape = new myShape();
        Shape shape1= shape.MakeRectangle2("#35e2f2",-450,Y);
        Shape shape2= shape.MakeRectangle2("#f6df0e",-337.5,Y);
        Shape shape3= shape.MakeRectangle2("#8c13fb",-225,Y);
        Shape shape4= shape.MakeRectangle2("#ff0080",-112.5,Y);
        Shape shape5= shape.MakeRectangle2("#35e2f2",0,Y);
        Shape shape6= shape.MakeRectangle2("#f6df0e",112.5,Y);
        Shape shape7= shape.MakeRectangle2("#8c13fb",225,Y);
        Shape shape8= shape.MakeRectangle2("#ff0080",337.5,Y);
        Group group = new Group(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8);
        return group;
    }
    public void MoveLine2(Group group,int duration,int X){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.setDuration(Duration.seconds(duration));
        translateTransition.setNode(group);
        translateTransition.setByX(X);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }
    public Group MakeLine(double Y){
        myShape shape= new myShape();
        Shape shape1= shape.MakeRectangle2("#35e2f2",-450,Y);
        Shape shape2= shape.MakeRectangle2("#f6df0e",-337.5,Y);
        Shape shape3= shape.MakeRectangle2("#8c13fb",-225,Y);
        Shape shape4= shape.MakeRectangle2("#ff0080",-112.5,Y);
        Shape shape5= shape.MakeRectangle2("#35e2f2",0,Y);
        Shape shape6= shape.MakeRectangle2("#f6df0e",112.5,Y);
        Shape shape7= shape.MakeRectangle2("#8c13fb",225,Y);
        Shape shape8= shape.MakeRectangle2("#ff0080",337.5,Y);
        Shape shape9= shape.MakeRectangle2("#35e2f2",450,Y);
        Shape shape10= shape.MakeRectangle2("#f6df0e",562.5,Y);
        Shape shape11= shape.MakeRectangle2("#8c13fb",675,Y);
        Shape shape12= shape.MakeRectangle2("#ff0080",787.5,Y);
        Group group = new Group(shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8,shape9,shape10,shape11,shape12);
        return group;
    }
    public void MoveLine(Group group,int duration){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(duration));
        translateTransition.setNode(group);
        translateTransition.setByX(450);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    public static void main(String args[]){
        launch(args);
    }
}
