package com.zengshen.service;

import com.zengshen.model.pojo.Category;
import com.zengshen.model.vo.CategoryVO;
import com.zengshen.model.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {

    /**
     * 获取一级分类
     */
     List<Category> getRootCategoryList();

    /**
     * 根据一级分类获取下级和下级的分类
     */
     List<CategoryVO> getSubCatList(String  rootCatId);

    /**
     * 查询每个一级分类下的最新6条商品数据
     */
     List<NewItemsVO> getSixNewItemsLazy(String rootCatId);
}
