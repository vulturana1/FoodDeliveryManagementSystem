package main;

import business.DeliveryService;
import presentation.Login;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MainClass {

    public static void main(String[] args) {

        try {
            FileInputStream f = new FileInputStream("output.txt");
            ObjectInputStream o = new ObjectInputStream(f);
            DeliveryService deliveryService = (DeliveryService) o.readObject();
            Login log = new Login(deliveryService);
        } catch (Exception e) {
            DeliveryService deliveryService = new DeliveryService();
            Login log = new Login(deliveryService);
        }


    }
}
