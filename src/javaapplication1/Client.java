/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dang Phu Trung Tin
 */
public class Client {
    Scanner myObj = new Scanner(System.in);
    ByteBuffer byteBuffer=ByteBuffer.allocate(256);
    public void clientSide() throws IOException{
        InetSocketAddress address=new InetSocketAddress("localhost", 1234);
        SocketChannel sc=SocketChannel.open(address);
        while (true) {            
            System.out.println("Nhap di:");
            String mess = myObj.nextLine();
            ByteBuffer byteBuffer =ByteBuffer.wrap(mess.getBytes());
            sc.write(byteBuffer);
            byteBuffer.clear();
            if(mess.equals("Exit")){
                break;
            }
        }
        sc.close();
    }
}
