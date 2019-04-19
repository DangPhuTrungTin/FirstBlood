/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 *
 * @author Dang Phu Trung Tin
 */
public class Server{

    public void serverSide() throws IOException{
        Selector selector=Selector.open();
        InetSocketAddress address=new InetSocketAddress("localhost",1234);
        ServerSocketChannel serverchannel=ServerSocketChannel.open();
        serverchannel.configureBlocking(false);
        serverchannel.bind(address);
        System.out.println(serverchannel.toString());
        //dangki
        int ops = serverchannel.validOps();
        serverchannel.register(selector, ops,null);
        while(true){
            //remote channel con thi no se con ton tai
            selector.select();
            System.out.println("select lai:");
            //key isAcceptable dc tao ra khi co yeu cau connect
            //key
            Iterator<SelectionKey> keys=selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey nextkey = keys.next();
                System.out.println("Key la:");
                System.out.println(nextkey.toString());
                if (nextkey.isAcceptable()) {
//                    ServerSocketChannel socketchannel=(ServerSocketChannel)nextkey.channel();
                    SocketChannel sc=serverchannel.accept();
                    System.out.println("OK nhan duoc request:" +sc.toString());
                    sc.configureBlocking(false);
                    
                    SelectionKey a=sc.register(selector, SelectionKey.OP_READ);
                    System.out.println("day la: "+ a.toString());
                }else if(nextkey.isReadable())
                {
                    System.out.println("nhan duoc request read");
                    SocketChannel sc=(SocketChannel)nextkey.channel();
                    
                    ByteBuffer byteBuffer=ByteBuffer.allocate(256);
                    System.out.println(byteBuffer.position());
                    sc.read(byteBuffer);
                    System.out.println(byteBuffer.position());
                    String result=new String(byteBuffer.array());
                    System.out.println(result);
                    if(result.equals("Exit")){
                        System.out.println("Stop");   
                        sc.close();
                    }
                }
//                else if (nextkey.isWritable()){
//                    System.out.println("nhan duoc request write");
//                    SocketChannel sc=(SocketChannel)nextkey.channel();
//                    ByteBuffer byteBuffer=ByteBuffer.allocate(256);
//                    String a="hello";
//                    byteBuffer.put(a.getBytes());
//                    sc.write(byteBuffer);
//                }
                keys.remove();
            }
        }
    }
}
