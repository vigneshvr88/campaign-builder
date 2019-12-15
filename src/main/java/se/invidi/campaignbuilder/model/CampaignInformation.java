package se.invidi.campaignbuilder.model;

import lombok.Data;

@Data
public class CampaignInformation {
    private String customer;
    private int impressionsPerCampaign;
    private int pricePerCampaign;
}
