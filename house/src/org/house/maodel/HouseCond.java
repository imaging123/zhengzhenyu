package org.house.maodel;

import java.math.BigDecimal;

public class HouseCond extends Houseinfo
{
	private Integer minPrice = -1;
	private Integer maxPrice = -1;

	private Integer minArea = -1;
	private Integer maxArea = -1;

	public Integer getMinPrice()
	{
		return minPrice;
	}

	public void setMinPrice(Integer minPrice)
	{
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice()
	{
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice)
	{
		this.maxPrice = maxPrice;
	}

	public Integer getMinArea()
	{
		return minArea;
	}

	public void setMinArea(Integer minArea)
	{
		this.minArea = minArea;
	}

	public Integer getMaxArea()
	{
		return maxArea;
	}

	public void setMaxArea(Integer maxArea)
	{
		this.maxArea = maxArea;
	}

}
