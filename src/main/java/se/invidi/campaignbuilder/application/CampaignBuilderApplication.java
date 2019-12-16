package se.invidi.campaignbuilder.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "se.invidi")
@EnableSwagger2
public class CampaignBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampaignBuilderApplication.class, args);
    }
}
