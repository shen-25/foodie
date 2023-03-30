package com.zengshen.service;

import com.zengshen.common.utils.PageInfoResult;
import com.zengshen.model.vo.CommentLevelCountsVO;

public interface CommentService {
    CommentLevelCountsVO getCommentLevelCounts(String itemId);

   PageInfoResult getCommentList(String itemId, String level, int page, int pageSize);
}
