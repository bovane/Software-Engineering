package edu.zjgsu.ito.contractmgn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zjgsu.ito.contractmgn.mapper.IFinancialMapper;
import edu.zjgsu.ito.contractmgn.model.Financial;
import edu.zjgsu.ito.contractmgn.service.IFinancialService;

/**
 * FinancialService
 */
@Service
public class FinancialService implements IFinancialService {

    @Autowired
    private IFinancialMapper financialMapper;
    @Override
    public Financial showMoney(Integer id) {
        return financialMapper.showMoney(id);
    }

    
}