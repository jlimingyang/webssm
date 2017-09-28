package com.item1024.mapper;

import com.item1024.pojo.SystemOption;

public interface SystemOptionMapper {
    int deleteByPrimaryKey(Integer webId);

    int insert(SystemOption record);

    int insertSelective(SystemOption record);

    SystemOption selectByPrimaryKey(Integer webId);

    int updateByPrimaryKeySelective(SystemOption record);

    int updateByPrimaryKey(SystemOption record);
}