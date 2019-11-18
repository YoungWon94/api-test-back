package com.example.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@CrossOrigin(origins = "*")
@RestController
public class TestController2222 {

    
    public ArrayList<String> strings;

    //원래 서비스로 만들어야함.
    public void sliceJSON(LinkedHashMap linkedHashMap, int level){     
        Set keyList = linkedHashMap.keySet();
        // System.out.println(keyList);
        Iterator it2 = keyList.iterator();

        String tab = "";
        for(int i=0; i<level; i++){
            tab += "\t";
        }
        while(it2.hasNext()){
            // System.out.println(linkedHashMap.get(it2.next()).getClass());
            Object key = it2.next();
           
            System.out.println(tab+"key : "+ key); //콘솔
            strings.add(tab+"key : "+ key+"\n"); //파일
            // System.out.println(tab+"valueType : "+linkedHashMap.get(key).getClass()); //콘솔
            // strings.add(tab+"valueType : "+linkedHashMap.get(key).getClass()+"\n"); //파일
            if(linkedHashMap.get(key).getClass()==LinkedHashMap.class){
                LinkedHashMap subLinkedHashMap = (LinkedHashMap) linkedHashMap.get(key);
                // System.out.println("변환11?"+subLinkedHashMap);
                sliceJSON(subLinkedHashMap, level+1);
            }else if(linkedHashMap.get(key).getClass()==LinkedList.class){
                LinkedList subLinkedList = (LinkedList) linkedHashMap.get(key);
                // System.out.println("변환22?"+subLinkedList);
                System.out.println(tab+"values") ; //콘솔
                strings.add(tab+"values"+"\n"); //파일
                int index = 0;
                for (Object object : subLinkedList) {
                    System.out.println(tab+index+" : "+ object.toString()); //콘솔
                    strings.add(tab+index+" : "+ object.toString()+"\n"); //파일
                    index++;
                } 
            }else{
                System.out.println(tab+"value : "+linkedHashMap.get(key)); //콘솔
                strings.add(tab+"value : "+linkedHashMap.get(key)+"\n"); //파일
            }
            System.out.println(); //콘솔
            strings.add("\n"); //파일
        }
    }


    @ResponseBody
    @RequestMapping(value = "/addservicecategory2", method = RequestMethod.POST)
    public JSONObject addservice(@RequestBody Map<String, Object> data) throws Exception {
        System.out.println("=====================================================================");
        System.out.println("서비스 카테고리 추가");
        System.out.println(data);
        
        String swaggerUrl = data.get("swaggerUrl").toString();
        System.out.println(swaggerUrl);
        //-----------------------------------
        JSONArray bodyArray = new JSONArray();
        //URL 호출
        URL url = new URL(swaggerUrl);
        //한글 처리 및 데이터 읽기
        InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
        //JSON Parsing // 예외처리
        JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
        // System.out.println(items);


        // return items;

        //==============================================
        JSONParser parser = new JSONParser();
        ContainerFactory orderedKeyFactory = new ContainerFactory()
        {
            public List creatArrayContainer() {
            return new LinkedList();
            }
            public Map createObjectContainer() {
            return new LinkedHashMap();
            }
        };

        Object obj = parser.parse(items.toJSONString(),orderedKeyFactory);  
        LinkedHashMap linkedHashMap = (LinkedHashMap)obj;

        // System.out.println("===================MMMMMMMMMMMMMMMMMM=============="); //전체 데이터
        // System.out.println(linkedHashMap);

        //************************************************************************ */
        System.out.println("===================KKKKKKKKKKKKKKKKKK=============="); //자른 데이터
        strings=new ArrayList<>();
        File file = new File("test1.txt");
        FileWriter writer = writer = new FileWriter(file, false);

        sliceJSON(linkedHashMap,1);

        for (String line : strings) {
            writer.write(line);
            writer.flush();            
        }

        writer.close();
        //************************************************************************ */
      
        return items; //받아온 데이터 통째로 넘김 일단        
    }
}