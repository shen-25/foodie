package com.zengshen.service.impl;

import com.zengshen.mapper.CategoryCustomMapper;
import com.zengshen.mapper.CategoryMapper;
import com.zengshen.model.pojo.Category;
import com.zengshen.model.vo.NewItemsVO;
import com.zengshen.service.CategoryService;
import com.zengshen.model.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCustomMapper categoryCustomMapper;

    @Override
    public List<Category> getRootCategoryList() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<CategoryVO> getSubCatList(String rootCatId) {
        return categoryCustomMapper.getSubCatList(rootCatId);
    }

    @Override
    public List<NewItemsVO> getSixNewItemsLazy(String rootCatId) {
        return categoryCustomMapper.getSixNewItemsLazy(rootCatId);
    }
}
