package com.zengshen.service.impl;

import com.github.pagehelper.PageHelper;
import com.zengshen.common.enums.YesOrNo;
import com.zengshen.common.utils.PageInfoResult;
import com.zengshen.common.utils.PageUtil;
import com.zengshen.mapper.*;
import com.zengshen.model.pojo.Items;
import com.zengshen.model.pojo.ItemsImg;
import com.zengshen.model.pojo.ItemsParam;
import com.zengshen.model.pojo.ItemsSpec;
import com.zengshen.model.vo.SearchItemsVO;
import com.zengshen.model.vo.ShopCartVO;
import com.zengshen.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    @Override
    public Items selectItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public List<ItemsImg> selectItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Override
    public List<ItemsSpec> selectItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public ItemsParam selectItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public PageInfoResult searchItemsByCategoryId(String catId, String sort, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> searchItemsVOS = itemsCustomMapper.searchItemsByCategoryId(catId, sort);
        return PageUtil.setPageInfoResult(searchItemsVOS, page);
    }

    @Override
    public PageInfoResult searchByKeyword(String keywords, String sort, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> keywordsList = itemsCustomMapper.searchItemsByKeyword(keywords, sort);
        return PageUtil.setPageInfoResult(keywordsList, page);
    }

    @Override
    public List<ShopCartVO> selectItemsBySpecIds(String itemSpecIds) {
        String ids[] = itemSpecIds.split(",");
        List<String> specIdsList = Arrays.stream(ids).collect(Collectors.toList());
        return itemsCustomMapper.queryItemsBySpecIds(specIdsList);
    }

    @Override
    public ItemsSpec queryItemSpecById(String specId) {
        return itemsSpecMapper.selectByPrimaryKey(specId);
    }

    @Override
    public String queryItemMainImgById(String itemId) {
        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNo.YES.type);
        ItemsImg result = itemsImgMapper.selectOne(itemsImg);
        return result != null ? result.getUrl() : "";
    }

    @Transactional
    @Override
    public void decreaseItemSpecStock(String itemSpecId, int buyCounts) {
       // 1. 查询库存
        int result = itemsCustomMapper.decreaseItemSpecStock(itemSpecId, buyCounts);
        if (result != 1) {
            throw new RuntimeException("订单创建失败，原因：库存不足!");
        }
    }
}
