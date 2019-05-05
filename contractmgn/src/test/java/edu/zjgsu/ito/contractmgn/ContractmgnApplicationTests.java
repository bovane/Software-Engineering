package edu.zjgsu.ito.contractmgn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.zjgsu.ito.contractmgn.model.Financial;
import edu.zjgsu.ito.contractmgn.service.IFinancialService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractmgnApplicationTests {
    @Autowired
    private IFinancialService financialService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void TestShow() {
        Financial showMoney = financialService.showMoney(1);
        System.out.println(showMoney);
    }

}
