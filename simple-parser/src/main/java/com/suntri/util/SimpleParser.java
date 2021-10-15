package com.suntri.util;

import java.util.*;

public class SimpleParser {

    public Map<String, List<String>> parse(String [] args) throws Exception {
        if(args == null || args.length == 0){
            throw new NullPointerException("Arguments cannot be null or empty.");
        }

        Map<String, List<String>> result = new HashMap<>();
        List<String> selected = null;

        for(int i=0; i<args.length; i++){
            if(args[i].startsWith("--")){
                String key = args[i].substring(2);
                if(key.length()<2){
                    throw new Exception("Option name is too short.");
                }
                if(!result.containsKey(key)){
                    result.put(key, new LinkedList<>());
                }
                selected = result.get(key);
            } else if(args[i].startsWith("-")) {
                char [] keys = args[i].substring(1).toCharArray();
                for(int j=0; j<keys.length; j++){
                    String key = Character.toString(keys[j]);
                    if(!result.containsKey(key)){
                        result.put(key, new LinkedList<>());
                    }
                    selected = result.get(key);
                }
            } else {
                if(selected==null){
                    throw new Exception("Illegal syntax.");
                }
                selected.add(args[i]);
            }
        }

        return Collections.unmodifiableMap(result);
    }
}
