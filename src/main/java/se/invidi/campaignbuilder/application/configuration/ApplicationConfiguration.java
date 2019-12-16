package se.invidi.campaignbuilder.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@ComponentScan(basePackages = "se.invidi")
public class ApplicationConfiguration {

    @Bean
    public Docket swaggerConfiguration() {

        Contact contact = new Contact("", "", "");
        ApiInfo apiInfo =
                new ApiInfo(
                        "Campaign Builder API",
                        "This API helps to build best possible combination of campaigns, with high revenue, " +
                                "for the given monthly inventory and sold campaigns",
                        "0.0.1",
                        "",
                        contact,
                        "",
                        "",
                        Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/invidi/*"))
                .build()
                .apiInfo(apiInfo);
    }
}
