package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */


@Controller
public class Main {

    File file;

    BufferedReader reader;

    final int block=18;

    String[] language=new String[]{"GNU C11","Clang++17 Diagnostics","GNU C++11","GNU C++14","GNU C++17",
            "MS C++","MS C++ 2017","Mono C#","D","Go","Haskell","Java 8","Kotlin","Ocaml","Delphi",
            "FPC","PascalABC.NET","Perl","PHP","Python 2","Python 3","PyPy 2","PyPy 3","Ruby","Rust",
            "Scala","JavaScript","Node.js"
    };

    Map<String,Integer> count=new HashMap<String,Integer>();

    public Main(){
        file=new File("/home/cordercorder/java/data.in");
        for(int i=0;i<language.length;i++){
            count.put(language[i],0);
        }

        try {
            reader=new BufferedReader(new FileReader(file));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequestMapping("/index")

    public String index(ModelMap model){
        String data=null;
        String lan;
        int sum=0,pos;
        int cnt;
        try{
            while(sum<block){
                data=reader.readLine();
                if(data!=null){
                    pos=data.lastIndexOf(" ");
                    lan=data.substring(0,pos);
                    cnt=Integer.valueOf(data.substring(pos+1,data.length()));
                    count.put(lan,cnt);
                    sum++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("okokokokokok");

        return "index/index";
    }

    @RequestMapping("/index/jsondata")
    @ResponseBody
    public Map<String,Object> JsonData(){
        List<String> ls=new ArrayList<>();
        List<Integer> tot=new ArrayList<>();
        Map<String,Object> mp=new HashMap<String,Object>();
        for(int i=0;i<language.length;i++){
            ls.add(language[i]);
            tot.add(count.get(language[i]));
        }
        mp.put("language",ls);
        mp.put("count",tot);
        return mp;
    }

    class Node{
        String language;
        int count;

        Node(String language,int count){
            this.language=language;
            this.count=count;
        }

    }

    @RequestMapping("/index/jsondata/sort")
    @ResponseBody
    public Map<String,Object> JsonDataSort(){
        List<Node> ls=new ArrayList<>();

        for(int i=0;i<language.length;i++){
            ls.add(new Node(language[i],count.get(language[i])));
        }

        Collections.sort(ls, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.count>o2.count){
                    return -1;
                }
                else if(o1.count==o2.count&&o1.language.compareTo(o2.language)<0){
                    return -1;
                }
                return 1;
            }
        });

        Map<String,Object> mp=new HashMap<String,Object>();
        List<String> res=new ArrayList<>();
        List<Integer> rei=new ArrayList<>();
        for(int i=0;i<5;i++){
            res.add(ls.get(i).language);
            rei.add(ls.get(i).count);
        }
        mp.put("language",res);
        mp.put("count",rei);
        return mp;
    }


}

