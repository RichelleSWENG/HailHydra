package AccountProfile;

public class AccountProfile 
{
    private String type, name, address, city, postCode, country, 
            phone1, phone2, phone3, faxNumber, emailAddress,
            website, contactPerson;
    private Float creditLimit;
    public int terms;
    public boolean status;
    
    public static class AccountProfileBuilder
    {
        private final String type, name;
        private String address, city, postCode, country, 
                phone1, phone2, phone3, faxNumber, emailAddress,
                website, contactPerson;
        private final Float creditLimit;
        public final int terms;
        public final boolean status;
       
        public AccountProfileBuilder(String type, String name, Float creditLimit, int terms, boolean status)
        {
            this.type="customer";
            this.name=name;
            this.creditLimit=creditLimit;
            this.terms=terms;
            this.status=status;
        }
        
        public AccountProfileBuilder setAddress(String temp)
        {
            this.address=temp;
            return this;
        }
        
        public AccountProfileBuilder setCity(String temp)
        {
            this.city=temp;
            return this;
        }
        
        public AccountProfileBuilder setPostCode(String temp)
        {
            this.postCode=temp;
            return this;
        }
        
        public AccountProfileBuilder setCountry(String temp)
        {
            this.country=temp;
            return this;
        }
        
        public AccountProfileBuilder setPhone1(String temp)
        {
            this.phone1=temp;
            return this;
        }
        
        public AccountProfileBuilder setPhone2(String temp)
        {
            this.phone2=temp;
            return this;
        }
        
        public AccountProfileBuilder setPhone3(String temp)
        {
            this.phone3=temp;
            return this;
        }
        
        public AccountProfileBuilder setFaxNumber(String temp)
        {
            this.faxNumber=temp;
            return this;
        }
        
        public AccountProfileBuilder setEmailAddress(String temp)
        {
            this.emailAddress=temp;
            return this;
        }
        
        public AccountProfileBuilder setWebsite(String temp)
        {
            this.website=temp;
            return this;
        }
        
        public AccountProfileBuilder setContactPerson(String temp)
        {
            this.contactPerson=temp;
            return this;
        }
        
        public AccountProfile build()
        {
            return new AccountProfile(this);
        }
        
        
    }
    
    public AccountProfile(AccountProfileBuilder temp)
    {
    	this.type=temp.type;
        this.name=temp.name;
        this.address=temp.address;
        this.city=temp.city;
        this.postCode=temp.postCode;
        this.country=temp.country;
        this.creditLimit=temp.creditLimit;
        this.terms=temp.terms;
        this.phone1=temp.phone1;
        this.phone2=temp.phone2;
        this.phone3=temp.phone3;
        this.faxNumber=temp.faxNumber;
        this.emailAddress=temp.emailAddress;
        this.website=temp.website;
        this.contactPerson=temp.contactPerson;
    }
    
    public String getType()
    {
    	return this.type;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public float getCreditLimit()
    {
    	return this.creditLimit;
    }
    
    public int getTerms()
    {
    	return this.terms;
    }
    
    public boolean getStatus()
    {
    	return this.status;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public String getCity()
    {
        return this.city;
    }
    
    public String getPostCode()
    {
        return this.postCode;
    }
    
    public String getCountry()
    {
        return this.country;
    }
    
    public String getPhone1()
    {
        return this.phone1;
    }
    
    public String getPhone2()
    {
        return this.phone2;
    }
    
    public String getPhone3()
    {
        return this.phone3;
    }
    
    public String getFaxNumber()
    {
        return this.faxNumber;
    }
    
    public String getEmailAddress()
    {
        return this.emailAddress;
    }
    
    public String getWebsite()
    {
        return this.website;
    }
    
    public String getContactPerson()
    {
        return this.contactPerson;
    }
    
    
}
