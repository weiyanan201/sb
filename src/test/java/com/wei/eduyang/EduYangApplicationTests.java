package com.wei.eduyang;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.mapper.TagMapper;
import com.wei.eduyang.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EduYangApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void testRest() {
        System.out.println("begion");
        String url = "http://dwapi.dw.sdo.com:8090/ApiServer/apiAction?decode_field=QZexcq%2BPrPmkywbjEfEL93tuA0c59tdz2UdW9grUU%2BI&dsId=126&encode=true&key=c00fd9f4891443eda088173dca5f011e&method=decode&timestamp=1541057934&sign=5f0f8baecf287d92e3e3b782e563a14d";
//        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        JSONObject postData = new JSONObject();
        postData.put("decode_field","QZexcq+PrPmkywbjEfEL93tuA0c59tdz2UdW9grUU+I");
        postData.put("dsId",126);
        postData.put("encode",true);
        postData.put("method","decode");
        postData.put("timestamp","1541063167");
        postData.put("sign","5f0f8baecf287d92e3e3b782e563a14d");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("decode_field","QZexcq+PrPmkywbjEfEL93tuA0c59tdz2UdW9grUU+I");
        map.add("dsId",126+"");
        map.add("encode",true+"");
        map.add("method","decode");
        map.add("timestamp","1541057934");
        map.add("sign","5f0f8baecf287d92e3e3b782e563a14d");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
      System.out.println(response);


//        String res = restTemplate.postForObject("http://dwapi.dw.sdo.com:8090/ApiServer/apiAction",postData,String.class);
//        String res = restTemplate.postForLocation("http://dwapi.dw.sdo.com:8090/ApiServer/apiAction",);
//        System.out.println(res);
//        System.out.println("end");

    }


}
