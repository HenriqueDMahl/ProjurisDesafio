package com.br.projuris;

import java.util.ArrayList;
import java.util.List;

public class MyFindChar implements FindCharachter {

    public char findChar(String word){

        List<String> ok = new ArrayList();
        List<String> notok = new ArrayList();

        String l = "";
        for(char a : word.toCharArray()){
            l = String.valueOf(a);
            if(notok.contains(l)){
                continue;
            }
            if(ok.contains(l)){
                ok.remove(l);
                notok.add(l);
            }else{
                ok.add(l);
            }
        }
        if(!ok.isEmpty())
            return ok.get(0).toCharArray()[0];
        else
            return "\0".toCharArray()[0];
    }

}
