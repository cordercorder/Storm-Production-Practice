package com.example.demo.controller;

import com.example.demo.domain.DataTable;
import com.example.demo.service.StormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: 金任任
 * @Class: 计科1604
 * @Number: 2016014537
 */


@Controller
public class Main {

    @Autowired
    private StormService stormService;

    private final int block=18;

    private long CurrentId;

    String[] language=new String[]{"GNU C11","Clang++17 Diagnostics","GNU C++11","GNU C++14","GNU C++17",
            "MS C++","MS C++ 2017","Mono C#","D","Go","Haskell","Java 8","Kotlin","Ocaml","Delphi",
            "FPC","PascalABC.NET","Perl","PHP","Python 2","Python 3","PyPy 2","PyPy 3","Ruby","Rust",
            "Scala","JavaScript","Node.js"
    };

    Map<String,Long> count=new HashMap<String,Long>();

    public Main(){

        for(int i=0;i<language.length;i++){
            count.put(language[i],0L);
        }

    }

    @RequestMapping("/index")

    public String index(ModelMap model){

        CurrentId=stormService.MinSubmissionId();

        System.out.println("id=="+CurrentId);
        return "index/index";
    }

    @RequestMapping("/test/MinSubmissionIdJson")
    @ResponseBody
    public long TestConnection(){
        return stormService.MinSubmissionId();
    }

    @RequestMapping("/test/DataQueryJson")
    @ResponseBody
    public DataTable TestConnection2(){
        return stormService.RequestData(57900455);
    }

    public void ReadData(){
        DataTable data;
        String language;
        long cnt;
        int sum=0;
        try{
            while(sum<block){
                data=stormService.RequestData(CurrentId++);
                if(data!=null){
                    cnt=data.getCount();
                    language=data.getLanguage();
                    count.put(language,cnt);
                    sum++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("succeed read data from databases!");
    }

    @RequestMapping(value = "/index/jsondata/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> JsonData(@PathVariable("id") String id){
        if(id.equals("refresh")){
            ReadData();
        }
        List<String> ls=new ArrayList<>();
        List<Long> tot=new ArrayList<>();
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
        long count;

        Node(String language,long count){
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
        List<Long> rei=new ArrayList<>();
        for(int i=0;i<5;i++){
            res.add(ls.get(i).language);
            rei.add(ls.get(i).count);
        }
        mp.put("language",res);
        mp.put("count",rei);
        return mp;
    }


}

