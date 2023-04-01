package com.zengshen.api.controller;

import com.zengshen.common.ApiRestResponse;
import com.zengshen.common.constants.Constant;
import com.zengshen.common.utils.PageInfoResult;
import com.zengshen.model.pojo.Items;
import com.zengshen.model.pojo.ItemsImg;
import com.zengshen.model.pojo.ItemsParam;
import com.zengshen.model.pojo.ItemsSpec;
import com.zengshen.model.vo.CommentLevelCountsVO;
import com.zengshen.model.vo.ItemInfoVO;
import com.zengshen.service.CommentService;
import com.zengshen.service.ItemsService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsService itemService;

    @Autowired
    private CommentService commentService;


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

    @GetMapping("/commentLevel")
    public ApiRestResponse commentLevel(@RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return ApiRestResponse.errorMsg("itemId不能为空");
        }
        CommentLevelCountsVO commentLevelCounts = commentService.getCommentLevelCounts(itemId);
        return ApiRestResponse.success(commentLevelCounts);
    }

    @GetMapping("comments")
    public ApiRestResponse comments(@RequestParam String itemId, @RequestParam(required = false) String level,
                                    @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return ApiRestResponse.errorMsg("itemId不能为空");
        }
        if (page == null) {
            page = Constant.COMMON_PAGE;
        }
        if (pageSize == null) {
            pageSize = Constant.COMMON_PAGE_SIZE;
        }
        if (page > Constant.MAX_PAGE) {
            page = Constant.MAX_PAGE;
        }
        if (pageSize > Constant.MAX_PAGE_SIZE) {
            page = Constant.MAX_PAGE_SIZE;
        }

        PageInfoResult pageInfoResult = commentService.getCommentList(itemId, level, page, pageSize);
        return ApiRestResponse.success(pageInfoResult);

    }

    /**
     * 搜索商品列表
     * @param catId 三级分类id
     * @param sort 排序规则
     */
    @GetMapping("catItems")
    public ApiRestResponse catItems(@RequestParam String catId, @RequestParam(required = false) String sort,
                                    @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isBlank(catId)) {
            return ApiRestResponse.errorMsg("catId 不能为空");
        }
        if (page == null) {
            page = Constant.COMMON_PAGE;
        }
        if (pageSize == null) {
            pageSize = Constant.ITEMS_COMMON_PAGE_SIZE;
        }
        if (page > Constant.MAX_PAGE) {
            page = Constant.MAX_PAGE;
        }
        if (pageSize > Constant.MAX_PAGE_SIZE) {
            page = Constant.MAX_PAGE_SIZE;
        }
        PageInfoResult pageInfoResult = itemService.searchItemsByCategoryId(catId, sort, page, pageSize);
        return ApiRestResponse.success(pageInfoResult);

    }

    /**
     * 搜索商品列表
     * @param keywords 三级分类id
     * @param sort 排序规则
     */
    @GetMapping("search")
    public ApiRestResponse searchByKeyword(@RequestParam String keywords, @RequestParam(required = false) String sort,
                                    @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return ApiRestResponse.errorMsg("keywords 不能为空");
        }
        if (page == null) {
            page = Constant.COMMON_PAGE;
        }
        if (pageSize == null) {
            pageSize = Constant.ITEMS_COMMON_PAGE_SIZE;
        }
        if (page > Constant.MAX_PAGE) {
            page = Constant.MAX_PAGE;
        }
        if (pageSize > Constant.MAX_PAGE_SIZE) {
            page = Constant.MAX_PAGE_SIZE;
        }
        PageInfoResult pageInfoResult = itemService.searchByKeyword(keywords, sort, page, pageSize);
        return ApiRestResponse.success(pageInfoResult);

    }

}
