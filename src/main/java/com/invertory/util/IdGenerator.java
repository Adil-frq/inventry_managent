package com.invertory.util;

public interface IdGenerator {

    public static String generateId(String id){
        //SUP-00001
        int index = id.indexOf("-");  //3
        ++index;

        String substring = id.substring(index); // 00001
        int i = Integer.parseInt(substring);
        i++;

        return String.format("%0" + substring.length() + "d", i);
    }
}
