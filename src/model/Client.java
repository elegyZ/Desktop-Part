package model;

import java.util.Date;

public class Client 
{
	private String id;
	private String userId;
	private String lastName;
	private String firstName;
	private String socialId;
	private String gender;
	private int age;
	private String country;
	private String province;
	private String city;
	private String phone;
	private String email;
	private Date createDate;
	private Date updateDate;
	
	public Client(String id, String userId, String lastName, String firstName, String socialId, String gender, int age,
				  String country, String province, String city, String phone, String email, Date createDate, Date updateDate)
	{
		this.id = id;
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setSocialId(socialId);
		setGender(gender);
		setAge(age);
		setCountry(country);
		setProvince(province);
		setCity(city);
		setPhone(phone);
		setEmail(email);
		setCreateDate(createDate);
		setUpdateDate(updateDate);
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setSocialId(String socialId)
	{
		this.socialId = socialId;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getSocialId()
	{
		return socialId;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public int age()
	{
		return age;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public Date getCreateDate()
	{
		return createDate;
	}
	
	public Date getUpdateDate()
	{
		return updateDate;
	}
}
