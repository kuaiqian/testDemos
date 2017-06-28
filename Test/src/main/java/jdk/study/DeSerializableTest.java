package jdk.study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeSerializableTest {
    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream obi = new ObjectInputStream(new FileInputStream("serial.txt"));
        Object object = obi.readObject();
        System.out.println(object);
        obi.close();
        ObjectInputStream obi1 = new ObjectInputStream(new FileInputStream("serial.external.txt"));
        Object object1 = obi1.readObject();
        System.out.println(object1);
        obi1.close();

    }
}
