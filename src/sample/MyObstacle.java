package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
        Group root2=MakeCross(225,150);
        Group root = new Group(root1,root2);
        setarcY(root1,50);

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
        Shape shape1= shape.MakePentagon("#35e2f2",X+60.1040764008,Y-60.1040764008,0);
        Shape shape2= shape.MakePentagon("#f6df0e",X+60.1040764008,Y+60.1040764008,90);
        Shape shape3= shape.MakePentagon("#8c13fb",X-60.1040764008,Y+60.1040764008,180);
        Shape shape4= shape.MakePentagon("#ff0080",X-60.1040764008,Y-60.1040764008,270);
        Group group = new Group(shape1,shape2,shape3,shape4);
        return group;
    }
    public static void main(String args[]){
        launch(args);
    }
}
