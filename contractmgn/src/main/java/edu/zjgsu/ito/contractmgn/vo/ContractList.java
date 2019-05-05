package edu.zjgsu.ito.contractmgn.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.io.Serializable;
import java.util.Date;

public class ContractList implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("archive_tag")
    private Integer archiveTag;

    
    /**
     * 合同id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    /**
     * 合同类别标记
     */

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("contract_tag")
    private Integer contractTag;
    /**
     * 合同编号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String number;

    /**
     * 合同名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    /**
     * 归属区块
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String block;

    /**
     * 合同类别
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("contract_type")
    private String contractType;

    /**
     * 签订时间
     */
    @JsonProperty("sign_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date signDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Integer getContractTag() {
        return contractTag;
    }

    public void setContractTag(Integer contractTag) {
        this.contractTag = contractTag;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    /**
     * @return the archiveTag
     */
    public Integer getArchiveTag() {
        return archiveTag;
    }

    /**
     * @param archiveTag the archiveTag to set
     */
    public void setArchiveTag(Integer archiveTag) {
        this.archiveTag = archiveTag;
    }

    /**
     * @Property("archive_tag")
     * @return the archiveTag
     */
   
}
