package org.house.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.house.maodel.District;
import org.house.maodel.HouseCond;
import org.house.maodel.Houseinfo;
import org.house.maodel.Page;
import org.house.maodel.RoomType;
import org.house.maodel.Street;
import org.house.service.DistrictService;
import org.house.service.HouseinfoService;
import org.house.service.RoomTypeService;
import org.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class HouseinfoAction extends ActionSupport
{
	@Autowired
	private HouseinfoService infoService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private StreetService streetService;
	
	private Houseinfo info;
	private RoomType roomType;
	private Street street;
	private HouseCond hc;
	private Page page;
	private String currentNo;
	private Integer id;

	private List<Houseinfo> infos = new ArrayList<Houseinfo>();
	private List<Street> streets = new ArrayList<>();
	private List<RoomType> types = new ArrayList<>();
	private List<District> districts = new ArrayList<>(); 
	
	public List<Street> getStreets()
	{
		return streets;
	}

	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}


	public Street getStreet()
	{
		return street;
	}

	public void setStreet(Street street)
	{
		this.street = street;
	}

	public void setStreets(List<Street> streets)
	{
		this.streets = streets;
	}

	public List<RoomType> getTypes()
	{
		return types;
	}

	public void setTypes(List<RoomType> types)
	{
		this.types = types;
	}

	public List<Houseinfo> getInfos()
	{
		return infos;
	}

	public void setInfos(List<Houseinfo> infos)
	{
		this.infos = infos;
	}

	public Houseinfo getInfo()
	{
		return info;
	}

	public void setInfo(Houseinfo info)
	{
		this.info = info;
	}

	public RoomType getRoomType()
	{
		return roomType;
	}

	public void setRoomType(RoomType type)
	{
		this.roomType = type;
	}

	public HouseCond getHc()
	{
		return hc;
	}

	public void setHc(HouseCond hc)
	{
		this.hc = hc;
	}

	public Page getPage()
	{
		return page;
	}

	public void setPage(Page page)
	{
		this.page = page;
	}

	public String getCurrentNo()
	{
		return currentNo;
	}

	public void setCurrentNo(String currentNo)
	{
		this.currentNo = currentNo;
	}

	@Override
	public String execute() throws Exception
	{
		return list();
	}

	public String list() throws Exception
	{
		districts = districtService.getAll();
		for (District district : districts)
		{
			for (Iterator<Street> iter = district.getStreets().iterator();iter.hasNext();)
			{
				Street street =(Street)iter.next();
				streets.add(street);
			}
			
		}
		types = roomTypeService.getAll();
		
		
		if (currentNo == null || "".equals(currentNo))
		{
			currentNo = "1";
		}
		page = (Page) ServletActionContext.getRequest().getSession()
				.getAttribute("page");
		if (page == null)
		{
			page = new Page();
			page.setCurrentNo(Integer.valueOf(currentNo));
			page = infoService.getAllByPage(page);
		} else
		{
			page.setCurrentNo(Integer.valueOf(currentNo));
		}
		if (Integer.valueOf(currentNo) == page.getPageCount())
		{
			ServletActionContext.getRequest().getSession()
					.removeAttribute("page");
		}


		return SUCCESS;
	}

	public String detail() throws Exception
	{
		info = infoService.getInfoById(id);
		
		return "details";
	}
	
	
	
	public String query() throws Exception
	{
		getCondition();
		
		List<Houseinfo> infos2 = infoService.getByCondition(hc);
		deleteMatch();
		if (currentNo == null || "".equals(currentNo))
		{
			currentNo = "1";
		}
		page = new Page();
		page.setCurrentNo(Integer.valueOf(currentNo));
		page.setData(infos2);
		page.setTotalCount(infos2.size());
		ServletActionContext.getRequest().getSession()
				.setAttribute("page", page);
		System.out.println(ServletActionContext.getRequest().getContextPath());
		return SUCCESS;
	}

	public void deleteMatch()
	{
		if(hc.getTitle() != null && !"".equals(hc.getTitle()))
		{
			String hcTitle = hc.getTitle();
			hc.setTitle(hcTitle.substring(1,hcTitle.length()-1));
		}
//		if(hc.getRoomtype() != null)
//		{
//			String roomTypeName = hc.getRoomtype().getName();
//			hc.getRoomtype().setName(roomTypeName.substring(1,roomTypeName.length()-1));
//		}
//		
//		if(hc.getStreet() != null)
//		{
//			String streetName = hc.getStreet().getName();
//			hc.getStreet().setName(streetName.substring(1,streetName.length()-1));
//		}
	}

	public void getCondition()
	{
		Integer id = hc.getStreet().getId();
		HouseCond condition = new HouseCond();
		condition.setStreet(streetService.getStreetById(id));
		if (hc.getTitle() != null && !"".equals(hc.getTitle()))
		{
			condition.setTitle("%"+hc.getTitle()+"%");
		}
//		if (hc.getRoomtype() != null && hc.getRoomtype().getName() != null
//				&& !"".equals(hc.getRoomtype().getName()))
//		{
//			condition.getRoomtype().setName("%"+hc.getRoomtype().getName()+"%");
//		}
//		if (hc.getStreet() != null && hc.getStreet().getName() != null
//				&& !"".equals(hc.getStreet().getName()))
//		{
//			condition.getStreet().setName("%"+hc.getStreet().getName()+"%");
//		}
		condition.setMinArea(hc.getMinArea());
		condition.setMaxArea(hc.getMaxArea());
		condition.setMaxPrice(hc.getMaxPrice());
		condition.setMinPrice(hc.getMinPrice());
		hc = condition;
	}

}
