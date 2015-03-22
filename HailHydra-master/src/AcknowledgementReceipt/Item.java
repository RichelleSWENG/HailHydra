/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcknowledgementReceipt;

public class Item
{
    private String partNum;
    private String description;
    private float price;

    public Item()
    {
        partNum = "";
        description = "";
        price = 0;
    }
    
    public Item(String partNum, String description, float price)
    {
        this.partNum = partNum;
        this.description = description;
        this.price = price;
    }
    
    /**
     * @return the partNum
     */
    public String getPartNum()
    {
        return partNum;
    }

    /**
     * @param partNum the partNum to set
     */
    public void setPartNum(String partNum)
    {
        this.partNum = partNum;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
    
    
}
