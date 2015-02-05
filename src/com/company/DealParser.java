package com.company;
import java.util.*;
import java.io.*;

/**
 * Created by wedeng on 2/3/15.
 */
public class DealParser {
    public static void main(String[] args) {
        String path = "/Users/wedeng/Documents/Practice/algo_java/top_deals_DE_7_1423063140.tsv";//args[1];
        System.out.println(path);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(path));
            String line;
            while((line=input.readLine()) != null) {

                String[] words = line.split("\t");
                Integer i = map.get(words[1]);
                if(i==null)
                    i = 0;
                map.put(words[1], i+1);
            }
            input.close();

            for(Map.Entry<String, Integer> e : map.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
