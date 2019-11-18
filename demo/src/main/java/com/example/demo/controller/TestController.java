package com.example.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.demo.dao.TestDAO;
import com.example.demo.dto.TTTT;
import com.example.demo.dto.TestDTO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@CrossOrigin(origins = "*")
@RestController
public class TestController {

    

    @Autowired
    TestDAO dao;

    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Map<String, Object> submit(@RequestBody Map<String, Object> data) {
        System.out.println("=====================================================================");
        System.out.println(data);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/swaggercheck", method = RequestMethod.POST)
    public  Map<String,Object> swaggercheck(@RequestBody Map<String, Object> data) throws Exception {
        System.out.println("=====================================================================");
        System.out.println("스와거 url 체크");
        System.out.println(data);
        
        String swaggerUrl = data.get("swaggerUrl").toString();
        System.out.println(swaggerUrl);
        //-----------------------------------
        Map<String,Object> response = new HashMap<>(); //응답 객체 스와거 판별 메세지 담음
            try{
            //URL 호출
            URL url = new URL(swaggerUrl);
            //한글 처리 및 데이터 읽기
            InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
            //JSON Parsing // 예외처리
            JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
            // System.out.println(items);
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

            // System.out.println("===================스와거 인지 확인=============="); //전체 데이터
            boolean isSwagger = false;
            System.out.println(linkedHashMap.keySet());
            Iterator it4 = linkedHashMap.keySet().iterator();
            while (it4.hasNext()) {
                if(it4.next().toString().equals("swagger")){
                    isSwagger=true;
                    break;
                }
            }
            response.put("urlCheck", isSwagger);
            response.put("swaggerData",(isSwagger)?items:"");
            
        }catch(Exception e){
            System.out.println("에러러러ㅓ러러러러");
            response.put("urlCheck",false );
        }finally{
            return response;
        }

    }


    public ArrayList<String> strings;

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
    @RequestMapping(value = "/addservicecategory", method = RequestMethod.POST)
    public JSONObject addservice(@RequestBody Map<String, Object> data) throws Exception {
        System.out.println("=====================================================================");
        System.out.println("서비스 카테고리 추가");
        System.out.println(data);
        
        String swaggerUrl = data.get("swaggerUrl").toString();
        System.out.println(swaggerUrl);
        //-----------------------------------
        JSONArray bodyArray = new JSONArray();
        System.out.println("11111111111111111111");
        //URL 호출
        URL url = new URL(swaggerUrl);
        System.out.println("222222222222222222222");
        //한글 처리 및 데이터 읽기
        InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
        System.out.println("3333333333333333333333");
        //JSON Parsing // 예외처리
        JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
        System.out.println("444444444444444444");
        // System.out.println(items);


        // return items;

    
        // 항상 넘어오는 데이터를 잘 확인해야함 
        // 데이터에 :: 두개 있어서 삽질4시간
        //===items.toString()=== 전달받은 데이터 {"data":[{"test1":"200","test2":"dummy1"},{"test1":"100","test2":"dummy2"}]}
        // JSON 배열 변환
        // bodyArray = (JSONArray) items.get("data");
        // System.out.println("555555555555555555");
        // System.out.println(bodyArray);
        // return bodyArray;
        //-----------------------------------


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
        // System.out.println("===================KKKKKKKKKKKKKKKKKK=============="); //자른 데이터
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

    @ResponseBody
    @RequestMapping(value = "/addapicategory", method = RequestMethod.POST)
    public Map<String, Object> addApiCategory(@RequestBody Map<String, Object> data) {
        System.out.println("=====================================================================");
        System.out.println("서비스 카테고리 추가");
        System.out.println(data);

        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/addapi", method = RequestMethod.POST)
    public Map<String, Object> request(@RequestBody Map<String, Object> data) {
        System.out.println("=====================================================================");
        System.out.println("api 추가");
        System.out.println(data);

        return data;
    }



    // @ResponseBody
    // @RequestMapping(value = "/manyjson", method = RequestMethod.GET)
    // public JSONObject manyJson(){

    // System.out.println("매우 많은 json test");

    // JSONObject jsonObj=new JSONObject();
    // JSONParser jsonparser = new JSONParser();
 
    // jsonObj =  jsonparser.parse("123");
    // return jsonObj;
    // }



    // @ResponseBody
    // @RequestMapping(value = "/test", method = RequestMethod.GET)
    // public List<TestDTO> home23(){
    // TestDTO asd = new TestDTO();

    // System.out.println("test들어옴");
    // // System.out.println(dao.listAll());
    // List<TestDTO> t1 = dao.list();
    // System.out.println(t1);

    // JSONObject jsonObj=new JSONObject();
    // JSONParser jsonparser = new JSONParser();
    // try {
    // for (TestDTO dto : t1) {
    // System.out.println("-------------------");
    // System.out.println(dto.getParam().toString());
    // jsonObj = (JSONObject) jsonparser.parse(dto.getParam().toString());
    // dto.setParam(jsonObj);
    // }
    // // jsonObj = (JSONObject) jsonparser.parse(t1.get(0).getParam().toString());
    // } catch (Exception e) {
    // //TODO: handle exception
    // }
    // return t1;
    // }

    // @ResponseBody
    // @RequestMapping(value = "/test", method = RequestMethod.GET)
    // public List<TestDTO> home23(){
    // System.out.println("test들어옴");
    // // System.out.println(dao.listAll());
    // List<TestDTO> t1 = dao.list();
    // System.out.println(t1);

    // JSONObject jsonObj=new JSONObject();
    // JSONParser jsonparser = new JSONParser();
    // try {
    // for (TestDTO dto : t1) {
    // System.out.println("-------------------");
    // System.out.println(dto.getParam().toString());
    // jsonObj = (JSONObject) jsonparser.parse(dto.getParam().toString());
    // dto.setParam(jsonObj);
    // }
    // // jsonObj = (JSONObject) jsonparser.parse(t1.get(0).getParam().toString());
    // } catch (Exception e) {
    // //TODO: handle exception
    // }
    // return t1;
    // }

    @ResponseBody
    @RequestMapping(value = "/jsontest", method = RequestMethod.GET)
    public JSONObject home() {

        System.out.println("들어옴");
        // System.out.println(dao.list());
        // List<TestDTO> result = dao.list();
        JSONObject jsonObj = new JSONObject();
        try {
            JSONParser jsonparser = new JSONParser();

            String json = "{\"service_api_no\":2,\"service_no\":1,\"service_api\":null,\"api_url\":\"/qwera/{id}\",\"param\":{\"param1\":{\"name\":\"id\",\"type\":\"query\",\"description\":\"idididid\",\"required\":true,\"datatype\":\"String\"},\"param2\":{\"name\":\"password\",\"type\":\"query\",\"description\":\"pwpwpwpwpw\",\"required\":true,\"datatype\":\"String\"}},\"method\":\"PUT\",\"content_type\":null,\"is_deleted\":\"F\",\"insert_timestamp\":\"2019-11-11T19:17:33\",\"updated_timestamp\":\"2019-11-11T19:17:33\",\"employee_no\":1,\"service_api_name\":\"/docs/write\",\"err_status\":\"F\",\"delay_status\":\"F\",\"response_list\":{\"response200\":{\"resultMsg\":\"su\",\"resultCode\":200,\"resultData\":\"200 data\"},\"response500\":{\"resultMsg\":\"fa\",\"resultCode\":500,\"resultData\":\"500 data\"}}}";
            jsonObj = (JSONObject) jsonparser.parse(json);
            System.out.println(json);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return jsonObj;
    }

    // @ResponseBody
    // @RequestMapping(value = "/1", method = RequestMethod.GET)
    // public Object home1(){

    // System.out.println("1들어옴");
    // // System.out.println(dao.listAll());
    // System.out.println(dao.list());
    // List<TestDTO> result = dao.list();
    // // JSONObject jsonObj=new JSONObject();
    // // try {

    // // JSONParser jsonparser = new JSONParser();
    // // jsonObj = (JSONObject) jsonparser.parse(result.get(0).toString());
    // // } catch (Exception e) {
    // // //TODO: handle exception
    // // }
    // return result.get(0);
    // }

}