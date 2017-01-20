package com.zhyi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by zhyi(997295009@qq.com) on 2017/1/20.
 */@Service
public class GirlService {

     @Autowired
    private GirlRepository girlRepository;

     @Transactional
     public void inertTwo(){
         Girl girl1 = new Girl();
         girl1.setCupSize("B");
         girl1.setAge(18);
         girlRepository.save(girl1);

         Girl girl2 = new Girl();
         girl2.setCupSize("G");
         girl2.setAge(26);
         girlRepository.save(girl2);
     }
}
