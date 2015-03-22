/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

public class Company
{
        private int id;
	private String name;
	private String addressLoc;
        private String addressCity;
        private String addressCountry;
        private String postalCode;
        private String phone1;
        private String phone2;
        private String phone3;
        private String faxNum;
        private String website;
        private String email;
        private String contactPerson;
        private String status;
        private float creditLimit;
        private int terms;
        private String type;

    public Company()
    {
        this.id = 0;
        this.name = "";
        this.addressLoc = "";
        this.addressCity = "";
        this.addressCountry = "";
        this.postalCode = "";
        this.phone1 = "";
        this.phone2 = "";
        this.phone3 = "";
        this.faxNum = "";
        this.website = "";
        this.email = "";
        this.contactPerson = "";
        this.status = "";
        this.creditLimit = 0;
        this.terms = 0;
        this.type = "";
    }
        
    public Company(int id, String name, String addressLoc, String addressCity, String addressCountry, String postalCode, String phone1, String phone2, String phone3, String faxNum, String website, String email, String contactPerson, String status, float creditLimit, int terms, String type)
    {
        this.id = id;
        this.name = name;
        this.addressLoc = addressLoc;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
        this.postalCode = postalCode;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.faxNum = faxNum;
        this.website = website;
        this.email = email;
        this.contactPerson = contactPerson;
        this.status = status;
        this.creditLimit = creditLimit;
        this.terms = terms;
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddressLoc()
    {
        return addressLoc;
    }

    public void setAddressLoc(String addressLoc)
    {
        this.addressLoc = addressLoc;
    }

    public String getAddressCity()
    {
        return addressCity;
    }

    public void setAddressCity(String addressCity)
    {
        this.addressCity = addressCity;
    }

    public String getAddressCountry()
    {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry)
    {
        this.addressCountry = addressCountry;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getPhone1()
    {
        return phone1;
    }

    public void setPhone1(String phone1)
    {
        this.phone1 = phone1;
    }

    public String getPhone2()
    {
        return phone2;
    }

    public void setPhone2(String phone2)
    {
        this.phone2 = phone2;
    }

    public String getPhone3()
    {
        return phone3;
    }

    public void setPhone3(String phone3)
    {
        this.phone3 = phone3;
    }

    public String getFaxNum()
    {
        return faxNum;
    }

    public void setFaxNum(String faxNum)
    {
        this.faxNum = faxNum;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getContactPerson()
    {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public float getCreditLimit()
    {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit)
    {
        this.creditLimit = creditLimit;
    }

    public int getTerms()
    {
        return terms;
    }

    public void setTerms(int terms)
    {
        this.terms = terms;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    
}
