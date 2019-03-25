package model;

public class Insured 
{
	private String id;
	private String insuredName;
	private String socialId;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private String bankUserName;
	private String bankName;
	private String bankAccount;
	private String policyId;
	
	public Insured(String id, String insuredName, String socialId, String gender, int age, String phone, String email,
				   String bankUserName, String bankName, String bankAccount, String policyId)
	{
		this.id = id;
		setPolicyId(policyId);
		setInsuranceName(insuredName);
		setSocialId(socialId);
		setGender(gender);
		setAge(age);
		setPhone(phone);
		setEmail(email);
		setBankUserName(bankUserName);
		setBankName(bankName);
		setBankAccount(bankAccount);
		setPolicyId(policyId);
	}
	
	public void setPolicyId(String policyId)
	{
		this.policyId = policyId;
	}
	
	public void setInsuranceName(String insuredName)
	{
		this.insuredName = insuredName;
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
	
	public void setBankUserName(String bankUserName)
	{
		this.bankUserName = bankUserName;
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
	
	public String getInsuredName()
	{
		return insuredName;
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
		return bankUserName;
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
