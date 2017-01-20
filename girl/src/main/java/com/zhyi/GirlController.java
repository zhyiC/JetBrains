package com.zhyi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by zhyi(997295009@qq.com) on 2017/1/20.
 */
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生数据的列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping(value = "/girlsAdd")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    /**
     * 查询一个女生
     * @param id
     * @return
     */
    @GetMapping(value = "/girlsFindOne/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 更新女生数据
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value = "/girlsUpdate/{id}")
    public Girl girlsUpdate(@PathVariable("id") Integer id,
                            @RequestParam("cupSize") String cupSize,
                            @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    /**
     * 删除女生数据
     * @param id
     */
    @DeleteMapping(value = "/girlsDelete/{id}")
    public void girlsDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询女生列表
     * @param age
     * @return
     */
    @GetMapping(value = "/girlsByAge/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 插入两条数据，事务管理
     */
    @PostMapping(value = "/girlsAddTwo")
    public void girlsAddTwo(){
        girlService.inertTwo();
    }
}
