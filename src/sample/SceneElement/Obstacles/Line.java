package sample.SceneElement.Obstacles;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.ArrayList;

public class Line extends sample.SceneElement.Obstacles.Obstacle {
    public TranslateTransition Transition = new TranslateTransition();
    public Line(){
        setListOfShapes(new ArrayList<>());
    }
    @Override
    public void Move() {
        Play();
    }
    @Override
    public void Play() {
        Transition.play();
    }
    @Override
    public void Pause() {
        Transition.pause();
    }
    @Override
    public double getCurrentTime() {
        return Transition.getCurrentTime().toMillis();
    }
    @Override
    public void setCurrentTime(double millis) {
        Play();
        Pause();
        Transition.jumpTo(new Duration(millis));
    }
}
