package com.zengshen.service;

import com.zengshen.model.pojo.Items;
import com.zengshen.model.pojo.ItemsImg;
import com.zengshen.model.pojo.ItemsParam;
import com.zengshen.model.pojo.ItemsSpec;
import com.zengshen.model.vo.ItemInfoVO;

import java.util.List;

public interface ItemsService {

    /**
     * 根据商品ID查询详情
     */
    public Items selectItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表

     */
    public List<ItemsImg> selectItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格

     */
    public List<ItemsSpec> selectItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数

     */
    public ItemsParam selectItemParam(String itemId);
}