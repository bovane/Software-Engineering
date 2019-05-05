package edu.zjgsu.ito.contractmgn.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ActralReceipt
 */
@JsonInclude(value = Include.NON_NULL)
public class ActuralReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    /**
     * 期数
     */
    @JsonProperty("issue_id")
    private Integer issueId;

    /**
     * 收款
     */
    private Double receipt;

    /**
     * 销售员提成列表
     */
    
    private List<Map<String,Object>> sellers;

    /**
     * 备注
     */
    private String remark;

    /**
     * 收款日期
     */
    @JsonProperty("receipt_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8",locale = "zh")
    private Date receiptDate;

    /**
     * @return the issueId
     */
    public Integer getIssueId() {
        return issueId;
    }

    /**
     * @param issueId the issueId to set
     */
    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    /**
     * @return the receipt
     */
    public Double getReceipt() {
        return receipt;
    }

    /**
     * @param receipt the receipt to set
     */
    public void setReceipt(Double receipt) {
        this.receipt = receipt;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the receiptDate
     */
    public Date getReceiptDate() {
        return receiptDate;
    }

    /**
     * @param receiptDate the receiptDate to set
     */
    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    /**
     * @return the sellers
     */
    public List<Map<String, Object>> getSellers() {
        return sellers;
    }

    /**
     * @param sellers the sellers to set
     */
    public void setSellers(List<Map<String, Object>> sellers) {
        this.sellers = sellers;
    }
        
    
}