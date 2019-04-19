/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dang Phu Trung Tin
 */
public class MainAppClient {
    public static void main(String[] args) {
        Client cl=new Client();
        try {
            cl.clientSide();
        } catch (IOException ex) {
            Logger.getLogger(MainAppClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
