package com.zengshen.mapper;

import com.zengshen.model.pojo.Category;
import com.zengshen.model.vo.CategoryVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CategoryMapper extends Mapper<Category> {
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    public Category getAll();
}