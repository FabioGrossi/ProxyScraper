package com._FabioTNT.proxyscraper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static File proxies;

    public static void getFromProxyscrape() {
        try {
            URL website = new URL("https://api.proxyscrape.com/?request=getproxies&proxytype=socks4&country=all");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("proxys.txt");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void initFile(){
        try {
            File file = new File("proxys.txt");
            if(file.exists()){
                file.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getFromProxyscrape();
    }

    public static void print(String message, String type){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("[" + simpleDateFormat.format(date) + "][" + type + "] " + message);
    }

    public static void readProxys() {
        File file = new File("proxys.txt");
        if (!file.exists()) {
            print("File dei proxy non trovato. - " + file.getPath(), "INFO");
            System.exit(0);
        } else {
            proxies = file;
        }
    }
}
