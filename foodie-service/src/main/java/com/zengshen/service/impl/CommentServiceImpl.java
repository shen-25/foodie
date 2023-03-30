package com.zengshen.service.impl;

import com.github.pagehelper.PageHelper;
import com.zengshen.common.enums.BusinessExceptionEnum;
import com.zengshen.common.enums.CommentLevel;
import com.zengshen.common.exception.BusinessException;
import com.zengshen.common.utils.PageInfoResult;
import com.zengshen.common.utils.PageUtil;
import com.zengshen.mapper.ItemsCommentsCustomMapper;
import com.zengshen.mapper.ItemsCommentsMapper;
import com.zengshen.model.pojo.ItemsComments;
import com.zengshen.model.vo.CommentLevelCountsVO;
import com.zengshen.model.vo.CommentVO;
import com.zengshen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsCommentsCustomMapper itemsCommentsCustomMapper;

    @Override
    public CommentLevelCountsVO getCommentLevelCounts(String itemId) {
        int goodCounts = getCommentLevelCounts(itemId, CommentLevel.GOOD.getScore());
        int normalCounts = getCommentLevelCounts(itemId, CommentLevel.NORMAL.getScore());
        int badCounts = getCommentLevelCounts(itemId, CommentLevel.BAD.getScore());
        int totalCounts = goodCounts + normalCounts + badCounts;

        CommentLevelCountsVO countsVO = new CommentLevelCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);
        return countsVO;
    }

    @Override
    public PageInfoResult getCommentList(String itemId, String level, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CommentVO> commentList = itemsCommentsCustomMapper.getCommentList(itemId, level);
        return PageUtil.setPageInfoResult(commentList, page);

    }

    public Integer getCommentLevelCounts(String itemId, Integer score) {
        ItemsComments condition = new ItemsComments();
        if (score != null) {
            condition.setCommentLevel(score);
        }
        condition.setItemId(itemId);
        return itemsCommentsMapper.selectCount(condition);
    }
}
