package com.zengshen.mapper;

import com.zengshen.model.pojo.Items;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface ItemsMapper extends Mapper<Items> {
}