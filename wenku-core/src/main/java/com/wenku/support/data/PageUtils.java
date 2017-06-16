package com.wenku.support.data;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sandy on 09/06/2017.
 */
public class PageUtils {

    public static void startPage(Pageable pageable){
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
    }

    public static <T> Page<T> wrapPage(List<T> data){
        PageInfo pageInfo = new PageInfo(data);
        Pageable pageable = new PageRequest(pageInfo.getPageNum()-1,pageInfo.getPageSize());
        return  new PageImpl<>(data,pageable,pageInfo.getTotal());
    }
}
