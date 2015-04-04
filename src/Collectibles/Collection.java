/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collectibles;

/**
 *
 * @author Janine
 */
public class Collection {
    private String date;
    private float amount;
    private int receipt_type;
    private String number;
    private String notes;
    private String received_by;
    private String collection_type;
    private String debit_memo_id;
    private String received_date;
    
    public Collection(String number,float amount,String received_date,String received_by,String collection_type,String debit_memo_id,String date,String notes)
    {
        this.number=number;
        this.amount=amount;
        this.received_date=received_date;
        this.received_by=received_by;
        this.collection_type=collection_type;
        this.debit_memo_id=debit_memo_id;
        this.date=date;
        this.notes=notes;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * @return the receipt_type
     */
    public int getReceipt_type() {
        return receipt_type;
    }

    /**
     * @param receipt_type the receipt_type to set
     */
    public void setReceipt_type(int receipt_type) {
        this.receipt_type = receipt_type;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the received_by
     */
    public String getReceived_by() {
        return received_by;
    }

    /**
     * @param received_by the received_by to set
     */
    public void setReceived_by(String received_by) {
        this.received_by = received_by;
    }

    /**
     * @return the collection_type
     */
    public String getCollection_type() {
        return collection_type;
    }

    /**
     * @param collection_type the collection_type to set
     */
    public void setCollection_type(String collection_type) {
        this.collection_type = collection_type;
    }

    /**
     * @return the debit_memo_id
     */
    public String getDebit_memo_id() {
        return debit_memo_id;
    }

    /**
     * @param debit_memo_id the debit_memo_id to set
     */
    public void setDebit_memo_id(String debit_memo_id) {
        this.debit_memo_id = debit_memo_id;
    }
    
    
}
