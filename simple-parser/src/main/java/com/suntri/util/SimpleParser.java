package com.suntri.util;

import java.util.*;
import java.util.function.Predicate;

public class SimpleParser {

    public Map<String, List<String>> parse(String [] args) throws Exception {
        if(args == null || args.length == 0){
            throw new NullPointerException("Arguments cannot be null or empty.");
        }

        Map<String, List<String>> result = new HashMap<>();
        List<String> leftover = new LinkedList<>();
        List<String> selected = leftover;

        for(int i=0; i<args.length; i++){
            if(args[i].startsWith("--")){
                String key = args[i].substring(2);
                if(key.length()<2 || !this.optionNameTester.test(key)){
                    throw new Exception("Illegal option name.");
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
                selected.add(args[i]);
                if(selected != leftover){
                    selected = leftover;
                }
            }
        }
        result.put("_", leftover);
        return Collections.unmodifiableMap(result);
    }

    private Predicate<String> optionNameTester = new Predicate<String>() {

        private static final String legalLeadingChar = "abcdefghijklmnopqtstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        @Override
        public boolean test(String s) {
            if(!legalLeadingChar.contains(s.substring(0, 1))){
                return false;
            }
            return true;
        }
    };
}
