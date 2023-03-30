package com.zengshen.api.controller;

import com.zengshen.common.ApiRestResponse;
import com.zengshen.model.pojo.Items;
import com.zengshen.model.pojo.ItemsImg;
import com.zengshen.model.pojo.ItemsParam;
import com.zengshen.model.pojo.ItemsSpec;
import com.zengshen.model.vo.ItemInfoVO;
import com.zengshen.service.ItemsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsService itemService;


    @GetMapping("/info/{itemId}")
    public ApiRestResponse info(@PathVariable String itemId) {

        // 整合返回item的数据

        Items item = itemService.selectItemById(itemId);
        List<ItemsImg> itemImgList = itemService.selectItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.selectItemSpecList(itemId);
        ItemsParam itemsParam = itemService.selectItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);

        return ApiRestResponse.success(itemInfoVO);
    }
}
