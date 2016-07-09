package org.sdust.searchEngine.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zzy on 2016/5/15.
 */

public class Page {

    private int start;// 从数据库中第几行开始检索
    private int currentNo = 1; // 当前页
    private int pageSize = 5; // 每页显示几行
    private int totalCount; // 总行数
    private int pageCount;// 共有几页
    private int maxDocs;//查询结果总数
    private Long time;//查询用时
    private List<DocumentEntity> data = new ArrayList();

    public int getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(int currentNo) {
        this.currentNo = currentNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        if (getTotalCount() % getPageSize() == 0) {
            return getTotalCount() / getPageSize();
        }
        return getTotalCount() / getPageSize() + 1;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<DocumentEntity> getData() {
        int fromIndex = (currentNo-1)*pageSize;
        int toIndex = fromIndex+pageSize;
        if (toIndex>=totalCount)
            return data.subList(fromIndex,totalCount);
       return  data.subList(fromIndex,toIndex);
    }

    public void setData(List<DocumentEntity> data) {
        this.data = data;
    }

    public int getStart() {
        return (getCurrentNo() - 1) * getPageSize();
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean getNext() {
        return getCurrentNo() < getPageCount();
    }

    public boolean getPre() {
        return getCurrentNo() > 1;
    }

    public int getMaxDocs() {
        return maxDocs;
    }

    public void setMaxDocs(int maxDocs) {
        this.maxDocs = maxDocs;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", currentNo=" + currentNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", pageCount=" + getPageCount() +
                ", data=" + data +
                '}';
    }
}
