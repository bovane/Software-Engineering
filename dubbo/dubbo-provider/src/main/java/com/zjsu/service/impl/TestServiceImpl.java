package com.zjsu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjsu.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Service(interfaceClass = TestService.class)
@Transactional
@Component
public class TestServiceImpl implements TestService {
    public void test( ) {
        System.out.println( "--- TestServiceImpl test() ---" );
    }

    public String testString( String str ) {
        System.out.println( "--- TestServiceImpl testString() ---" );
        return "str：" + str;
    }
}
