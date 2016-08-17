/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataInquireCard;

/**
 *
 * @author CPU01661-local
 */
public class DataResponseInquireCard {
    private String code;
    private Object data;

    public DataResponseInquireCard() {
    }

    public DataResponseInquireCard(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}
