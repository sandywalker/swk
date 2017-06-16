package com.wenku.doc.dao;

import com.wenku.doc.model.BaseDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.doc.model.BaseDoc;

@Mapper
public interface DocDao {
    int insert( @Param("table") String table,@Param("doc") BaseDoc doc);

    int insertSelective( @Param("table") String table,@Param("doc") BaseDoc doc);

    int insertList( @Param("table") String table,@Param("docs") List<BaseDoc> doc);

    int update( @Param("table") String table,@Param("doc") BaseDoc doc);

    List<BaseDoc> find(@Param("table") String table);

    BaseDoc findOne( @Param("table") String table,@Param("id") long id);

    void delete( @Param("table") String table,@Param("id") long id);

}
