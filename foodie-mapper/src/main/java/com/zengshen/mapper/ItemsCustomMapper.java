package com.zengshen.mapper;

import com.zengshen.model.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsCustomMapper {

    List<SearchItemsVO> searchItemsByCategoryId(@Param("catId") String catId, @Param("sort") String sort);
    List<SearchItemsVO> searchItemsByKeyword(@Param("keyword") String keyword, @Param("sort") String sort);

}