package edu.zjgsu.ito.contractmgn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("edu.zjgsu.ito.contractmgn.mapper")
public class ContractmgnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractmgnApplication.class, args);
    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.zjgsu.ito.contractmgn.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("合同管理系统测试接口平台API")
                .description("cathy demo  API.")
                .termsOfServiceUrl("Terms of service")
                .contact(new Contact("ylw", "", "807281235@qq.com"))
                .version("1.0")
                .build();
    }

}



