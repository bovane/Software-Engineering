package edu.zjgsu.ito.contractmgn.vo;

import java.io.Serializable;

public class AccountInfo extends ContractList implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 合同id
     */
    private Integer contractId;
    /**
     * 应收款金额
     */
    private Double accountReceivable;
    /**
     * 实收款金额
     */
    private Double accountActual;
    /**
     * 状态1未开始2已开始3逾期
     */
    private Integer status;
    /**
     * 天数（离逾期xx天、逾期xx天）
     */
    private Integer days;
    /**
     * 显示的状态
     */
    private String statusVo="未添加";
    /**
     * 合同节点
     */
    private String contractNode;
    /**
     * 期数
     */
    private Integer stage;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getStatusVo() {
        return statusVo;
    }

    public void setStatusVo(String statusVo) {
        this.statusVo = statusVo;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Double getAccountReceivable() {
        return accountReceivable;
    }

    public void setAccountReceivable(Double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }

    public Double getAccountActual() {
        return accountActual;
    }

    public void setAccountActual(Double accountActual) {
        this.accountActual = accountActual;
    }



    public String getContractNode() {
        return contractNode;
    }

    public void setContractNode(String contractNode) {
        this.contractNode = contractNode;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }
}