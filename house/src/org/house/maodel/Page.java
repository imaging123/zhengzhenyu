package org.house.maodel;

import java.util.ArrayList;
import java.util.Collection;

public class Page
{
	private int start;//ÿҳ�Ŀ�ʼ����
	private int currentNo=1;//��ǰҳ
	private int pageSize=4;//ÿҳ��ʾ����
	private int totalCount;//������
	private int pageCount;//��ҳ��
	private boolean next;
	private boolean pre;
	private Collection<Houseinfo> data=new ArrayList<Houseinfo>();//Ҫ��ʾ������
	
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
