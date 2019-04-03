package model;

public class Insured 
{
	private String id;
	private String lastname;
	private String firstname;
	private String socialId;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private String bankUsername;
	private String bankName;
	private String bankAccount;
	private String policyId;
	
	public Insured(String id, String lastname, String firstname, String socialId, String gender, int age, String phone, String email,
				   String bankUsername, String bankName, String bankAccount, String policyId)
	{
		this.id = id;
		setPolicyId(policyId);
		setLastname(lastname);
		setFirstname(firstname);
		setSocialId(socialId);
		setGender(gender);
		setAge(age);
		setPhone(phone);
		setEmail(email);
		setBankUserName(bankUsername);
		setBankName(bankName);
		setBankAccount(bankAccount);
		setPolicyId(policyId);
	}
	
	public void setPolicyId(String policyId)
	{
		this.policyId = policyId;
	}
	
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
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
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setBankUserName(String bankUsername)
	{
		this.bankUsername = bankUsername;
	}
	
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
	
	public void setBankAccount(String bankAccount)
	{
		this.bankAccount = bankAccount;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public String getPolicyId()
	{
		return policyId;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public String getSocialId()
	{
		return socialId;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getBankUserName()
	{
		return bankUsername;
	}
	
	public String getBankName()
	{
		return bankName;
	}
	
	public String getBankAccount()
	{
		return bankAccount;
	}
}
