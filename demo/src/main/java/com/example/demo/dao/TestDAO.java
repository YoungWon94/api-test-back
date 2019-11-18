package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.TestDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * TestDAO
 */
@Mapper
public interface TestDAO {  
    
    @Select("select * from wehago_common.t_service_api")
    public List<TestDTO> list();

    
    // @Select("select * from wehago_common.test")
    // public List<TestDTO> listAll();

    
}