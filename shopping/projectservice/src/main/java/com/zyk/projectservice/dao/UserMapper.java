package com.zyk.projectservice.dao;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dto.UserListDTO;
import com.zyk.projectservice.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    User selectByEmail(String  email);

    User getByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Page<UserListDTO> selectWithPage();

    void batchDelete(@Param("userIds") Integer[] userIds);
}