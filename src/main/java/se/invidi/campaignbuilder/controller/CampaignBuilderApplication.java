package se.invidi.campaignbuilder.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class CampaignBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampaignBuilderApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/invidi/*"))
                .build()
                .apiInfo(
                        new ApiInfo(
                                "Campaign Builder API",
                                "This API helps to build best possible combination of campaigns, with high revenue, for the given monthly inventory and sold campaigns",
                                "0.0.1",
                                "",
                                new Contact("", "", ""),
                                "",
                                "",
                                Collections.emptyList()));
    }
}
