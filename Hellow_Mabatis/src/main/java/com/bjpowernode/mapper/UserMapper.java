package com.bjpowernode.mapper;

import com.bjpowernode.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

     User selectById(int id);

     User selectByUser(User user);

     int updateUser(User user);


     /*
      * 注解
      * */
     @Select("select id,name 'username',password 'pwd',bank_card, status from tb_user")
     List<User> selectUser();

}
