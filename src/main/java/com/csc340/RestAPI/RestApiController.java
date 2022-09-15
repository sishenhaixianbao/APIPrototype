package com.csc340.RestAPI;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sentini
 */
@RestController
public class RestApiController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    /**
     * Get a random quote from quotable and make it available at this endpoint.
     *
     * @return
     */
    @GetMapping("/ip")
    public Object getIP() {
        String url = "https://api.ipify.org?format=json";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonIP = restTemplate.getForObject(url, Object.class);

        //Print the whole response to console.
        String ip = restTemplate.getForObject(url, String.class);
        //Parse out the most important info from the response.
        JSONObject jo = new JSONObject(ip);
        System.out.println(jo.toString());
        String IP = jo.getString("ip");
    
        System.out.println(IP);
    

        return jSonIP;
    }

    /**
     * Get a list of universities from hipolabs and parse the most important
     * info to console.
     *
     * @return
     */
    @GetMapping("/universities")
    public List<Object> getUniversities() {
        String url = "http://universities.hipolabs.com/search?name=sports";
        RestTemplate restTemplate = new RestTemplate();
        Object[] universities = restTemplate.getForObject(url, Object[].class);

        //Print the whole response to console
        JSONArray ja = new JSONArray(universities);
        System.out.println(ja.toString());

        //Parse out relevant info from each entry in the response
        Iterator<Object> iter = ja.iterator();
        while (iter.hasNext()) {
            JSONObject jo = (JSONObject) iter.next();
            String name = jo.getString("name");
            String country = jo.getString("country");
            System.out.println(name + " : " + country);

        }

        return Arrays.asList(universities);
    }

}
