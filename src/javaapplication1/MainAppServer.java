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
public class MainAppServer {
    public static void main(String[] args) {
        Server server=new Server();
        try {
            server.serverSide();
        } catch (IOException ex) {
            Logger.getLogger(MainAppServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
