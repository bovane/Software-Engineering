package edu.zjgsu.ito.contractmgn;

import edu.zjgsu.ito.contractmgn.schedule.ReceivableScheduleTask;
import edu.zjgsu.ito.contractmgn.service.CommissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommissionInsertTest {

    @Autowired
    CommissionService commissionService;
    @Autowired
    ReceivableScheduleTask receivableScheduleTask;

    @Test
    public void testInsert(){
        commissionService.insertCommission(1,1,20,new Date(),5);
    }

    @Test
    public void test(){
    }

}
