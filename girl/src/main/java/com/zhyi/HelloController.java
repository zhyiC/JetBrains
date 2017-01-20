package com.zhyi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhyi(997295009@qq.com) on 2017/1/18.
 */
@RestController
@RequestMapping(value = {"/hello", "/hi"})
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/getCupSize", method = RequestMethod.GET)
    public String getCupSize(){
        return girlProperties.getCupSize();
    }

    @RequestMapping(value = "/getAge", method = RequestMethod.POST)
    public Integer getAge(){
        return girlProperties.getAge();
    }

    //http://127.0.0.1:8080/hi/say/id=998
    @RequestMapping(value = "/say/{id}", method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id){
        return "id: " + id;
    }

    //http://127.0.0.1:8080/hi/speak?id=998
    //@GetMapping(value = "/speak")
    @RequestMapping(value = "/speak", method = RequestMethod.GET)
    public String speak(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId){
        return "id: " + myId;
    }

}
