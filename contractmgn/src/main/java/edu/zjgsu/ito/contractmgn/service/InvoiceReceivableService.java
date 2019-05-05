package edu.zjgsu.ito.contractmgn.service;

import edu.zjgsu.ito.contractmgn.model.InvoiceReceivable;
import edu.zjgsu.ito.contractmgn.pojo.Message;

public interface InvoiceReceivableService {
    Message getInvoiceReInfo(Integer contractId);//获取合同总额以及剩余开票金额

    Message addInvoiceReceivable(InvoiceReceivable invoiceReceivable);//添加应开票

    Message deleteInvoiceRe(Integer invoiceReId);//删除应开票

    Message updateInvoiceRe(InvoiceReceivable invoiceReceivable);//更新应开票
}
