package com.example.mall2.mapper;

import com.example.mall2.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lala
 */
@Repository
public interface LogMapper {
    public int userLogin(@Param("user_name") String user_name,
                         @Param("user_password") String user_password);
    public int findEmail(@Param("user_email") String user_email);
    public User getByUserName(@Param("user_name") String user_name);
    public int insertRegister(@Param("user_name") String user_name,
                              @Param("user_password") String user_password,
                              @Param("user_email") String user_email);
}
