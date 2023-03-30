package com.zengshen.mapper;

import com.zengshen.model.vo.CategoryVO;
import com.zengshen.model.vo.NewItemsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryCustomMapper {
    List<CategoryVO> getSubCatList(String rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(String rootCatId);
}