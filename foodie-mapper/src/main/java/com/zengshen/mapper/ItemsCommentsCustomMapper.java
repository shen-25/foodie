package com.zengshen.mapper;

import com.zengshen.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsCommentsCustomMapper {
    List<CommentVO> getCommentList(@Param("itemId") String itemId,@Param("level") String level);
}