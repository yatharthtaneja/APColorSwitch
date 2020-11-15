package sample;

import java.awt.*;
import java.util.ArrayList;

class uml {
    public static void main(String args[]){
        GameMenu Menu;
    }
}

abstract class SceneElements{
    private double Xpos;
    private double Ypos;
    public double getXpos() {
        return Xpos;
    }
    public void setXpos(double xpos) {
        Xpos = xpos;
    }
    public double getYpos() {
        return Ypos;
    }
    public void setYpos(double ypos) {
        Ypos = ypos;
    }
}
class Obstacle extends SceneElements{
    private ArrayList<Shape> ListOfShapes=new ArrayList<>();
    private Double Speed;
    public Double getSpeed() {
        return Speed;
    }
    public void setSpeed(Double speed) {
        Speed = speed;
    }
    public void Rotate(){};
}
class Ring extends Obstacle{

}
class Line extends Obstacle{

}
class Cross extends Obstacle{

}
class Trilateral extends Obstacle{

}
class Ball extends SceneElements{
    public boolean CheckCollision(){
        return true;
    }

}
abstract class Powerups extends SceneElements{

}
class Star extends Powerups{
    public void Explode(){};
}
class ColorBoost extends Powerups{

}
class Shape{
    private double Xpos;
    private double Ypos;
    private Image Image;
    private Color Colour;
    private double TimeSinceLastColourChange;

    public double getXpos() {
        return Xpos;
    }
    public void setXpos(double xpos) {
        Xpos = xpos;
    }
    public double getYpos() {
        return Ypos;
    }
    public void setYpos(double ypos) {
        Ypos = ypos;
    }
    public java.awt.Image getImage() {
        return Image;
    }
    public void setImage(java.awt.Image image) {
        Image = image;
    }
    public Color getColour() {
        return Colour;
    }
    public void setColour(Color colour) {
        Colour = colour;
    }
}
class Arc extends Shape{
    private int Type;

    public int getType() {
        return Type;
    }
    public void setType(int type) {
        Type = type;
    }
}
class Rectangle extends Shape{
    private int Type;

    public int getType() {
        return Type;
    }
    public void setType(int type) {
        Type = type;
    }
}
class Circle extends Shape{

}
class Triangle extends Shape{
    private int Type;

    public int getType() {
        return Type;
    }
    public void setType(int type) {
        Type = type;
    }
}
class Pentagon extends Shape{
    private int Type;

    public int getType() {
        return Type;
    }
    public void setType(int type) {
        Type = type;
    }
}
class GameMenu{
    private ArrayList<Player>SavedPlayers;
    private Settings MySettings;
    public ArrayList<Player> getSavedPlayers() {
        return SavedPlayers;
    }
    public void setSavedPlayers(ArrayList<Player> savedPlayers) {
        SavedPlayers = savedPlayers;
    }
    public Settings getMySettings() {
        return MySettings;
    }
    public void setMySettings(Settings mySettings) {
        MySettings = mySettings;
    }
}
class Game{
    ArrayList<Obstacle> Obstacles;
    Ball Ball;
    private int CurrentStars;
}
class Player{
    private String Name;
    private Game Game;
    private int TotalStars;
    private int CurrentStars;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getTotalStars() {
        return TotalStars;
    }
    public void incrementTotalStars(){
        TotalStars++;
    }
    public void incrementCurrentStars(){
        CurrentStars++;
    }
    public void setTotalStars(int totalStars) {
        TotalStars = totalStars;
    }
    public int getCurrentStars() {
        return CurrentStars;
    }
    public void setCurrentStars(int currentStars) {
        CurrentStars = currentStars;
    }
}
class Settings{
    private String About;
    private String Credits;
    private int SoundLevel;
    private int MusicLevel;
    private boolean DarkTheme;

    public String getAbout() {
        return About;
    }
    public void setAbout(String about) {
        About = about;
    }
    public String getCredits() {
        return Credits;
    }
    public void setCredits(String credits) {
        Credits = credits;
    }
    public int getSoundLevel() {
        return SoundLevel;
    }
    public void setSoundLevel(int soundLevel) {
        SoundLevel = soundLevel;
    }
    public int getMusicLevel() {
        return MusicLevel;
    }
    public void setMusicLevel(int musicLevel) {
        MusicLevel = musicLevel;
    }
    public boolean isDarkTheme() {
        return DarkTheme;
    }
    public void setDarkTheme(boolean darkTheme) {
        DarkTheme = darkTheme;
    }
}
