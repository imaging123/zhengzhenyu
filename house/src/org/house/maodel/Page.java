package org.house.maodel;

import java.util.ArrayList;
import java.util.Collection;

public class Page
{
	private int start;//每页的开始行数
	private int currentNo=1;//当前页
	private int pageSize=4;//每页显示几行
	private int totalCount;//总行数
	private int pageCount;//总页数
	private boolean next;
	private boolean pre;
	private Collection<Houseinfo> data=new ArrayList<Houseinfo>();//要显示的数据
	
	public int getStart()
	{
		return (currentNo-1)*pageSize;
	}
	public void setStart(int start)
	{
		this.start = start;
	}
	public int getCurrentNo()
	{
		return currentNo;
	}
	public void setCurrentNo(int currentNo)
	{
		this.currentNo = currentNo;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public int getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
	public int getPageCount()
	{
		if(totalCount % pageSize==0)
		{
			return totalCount/pageSize;
		}
		return totalCount/pageSize+1;
	}
	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}
	public Collection<Houseinfo> getData()
	{
		return data;
	}
	public void setData(Collection<Houseinfo> data)
	{
		this.data = data; 
	}
	
	public boolean getNext()
	{
		return currentNo < getPageCount();
	}
	
	public boolean getPre()
	{
		return currentNo > 1;
	}
	
}
