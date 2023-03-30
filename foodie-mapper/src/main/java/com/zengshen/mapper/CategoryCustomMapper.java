package com.zengshen.mapper;

import com.zengshen.model.vo.CategoryVO;
import com.zengshen.model.vo.NewItemsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryCustomMapper {
    List<CategoryVO> getSubCatList(String rootCatId);

    List<NewItemsVO> getSixNewItemsLazy(String rootCatId);
}