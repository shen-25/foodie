package com.zengshen.common.utils;

import java.util.List;

public class PageInfoResultBuilder {

    private Integer page;
    private Integer pageSize;
    private Integer pages;
    private Long total;
    private Boolean isLastPage;
    private List<?> list;

    private PageInfoResultBuilder() {

    }

    public static PageInfoResultBuilder create() {
        return new PageInfoResultBuilder();
    }

    public PageInfoResultBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }
    public PageInfoResultBuilder withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    public PageInfoResultBuilder withPages(Integer pages) {
        this.pages = pages;
        return this;
    }
    public PageInfoResultBuilder withTotal(Long total) {
        this.total = total;
        return this;
    }

    public PageInfoResultBuilder withIsLastPage(Boolean isLastPage) {
        this.isLastPage = isLastPage;
        return this;
    }

    public PageInfoResultBuilder withList(List<?> list) {
        this.list = list;
        return this;
    }

    public PageInfoResult build() {
        PageInfoResult pageInfoResult = new PageInfoResult(page, pageSize, pages, total, isLastPage, list);
        return pageInfoResult;
    }

}
