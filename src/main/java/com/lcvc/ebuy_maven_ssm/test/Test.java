package com.lcvc.ebuy_maven_ssm.test;

import com.lcvc.ebuy_maven_ssm.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public  static  void  main(String[] args){
        //建立一个List 集合
       /* List list=new ArrayList();
        list.add("张三");    //位置：0
        list.add("李四");    //位置：1
        list.add(3);          //位置：2
        list.add("呆呆");    //位置：3
        for (int i=0;i< list.size();i++){
            System.out.println(list.get(2));
        }*/

       List <Admin> list=new ArrayList<Admin>();
       list.add(new Admin(33));
        list.add(new Admin(153));
        list.add(new Admin(363));
        for (int i=0;i< list.size();i++){
            Admin admin=list.get(i);
            System.out.println(admin.getId());
        }
    }




}
