package data;

import business.DeliveryService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializator {

    public static void serialization(DeliveryService deliveryService){
        FileOutputStream f;
        try {
            
            f = new FileOutputStream("output.txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(deliveryService);
            o.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

}
