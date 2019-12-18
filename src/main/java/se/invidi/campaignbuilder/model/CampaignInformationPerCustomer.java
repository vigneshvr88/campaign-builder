package se.invidi.campaignbuilder.model;

import lombok.Data;

@Data
public class CampaignInformationPerCustomer {

  private String customer;
  private int numberOfCampaignsToSell;
  private long totalImpressions;
  private int totalRevenue;
}
