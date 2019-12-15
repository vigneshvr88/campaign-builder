package se.invidi.campaignbuilder.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class CampaignBuilderResponse {
    private List<CampaignInformationPerCustomer> campaignInformationPerCustomer;
    private long totalImpressions;
    private long totalRevenue;
}
