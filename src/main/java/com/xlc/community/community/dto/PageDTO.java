package com.xlc.community.community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :xlc
 * @date: 2020-6-1
 * @description: 分页类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T>
{
    private List<T> list; // 数据
    private Integer currentPage; // 当前页
    private Integer pageSize; // 每页显示条数
    private Integer totalPage; // 总页数
    private boolean showPrevious;
    private boolean showFirstPage; //
    private boolean showNext; // 是否展示下一页
    private boolean showEndPage; // 是否展示最后一页
    private List<Integer> pages = new ArrayList<>();

    public void setPage(Integer totalCount,Integer pageSize,Integer currentPage){
       // int totalPage; // 总页数


        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        this.pageSize = pageSize;
        if (currentPage > totalPage){
            currentPage = totalPage;
        }
        this.currentPage = currentPage;

        pages.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }

            if (currentPage + i <= totalPage) {
                pages.add(currentPage + i);
            }
        }
        // 是否展示上一页
        if (currentPage == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        // 是否展示下一页
        if (currentPage == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        // 是否展示最后一页
        if (pages.contains(totalCount)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }

}
