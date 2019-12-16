package se.invidi.campaignbuilder.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class CampaignBuilderRequest {

    public List<CampaignInformation> campaignInformationList;
}