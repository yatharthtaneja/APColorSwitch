package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class resourceManager {
public static void save(Serializable data, String filename) throws IOException {
    try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))){
        oos.writeObject(data);
    }
}
public static Object loadData(String filename) throws Exception{
    try(ObjectInputStream ois= new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
        return ois.readObject();
    }
}
}
