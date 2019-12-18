package se.invidi.campaignbuilder.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.invidi.campaignbuilder.model.CampaignBuilderRequest;
import se.invidi.campaignbuilder.model.CampaignBuilderResponse;
import se.invidi.campaignbuilder.service.CampaignBuilderService;

@RestController
@RequestMapping("/invidi")
public class CampaignBuilderController {

  @Autowired
  private CampaignBuilderService campaignBuilderService;

  @PostMapping("/campaign")
  @ApiOperation(
          value = "Build best possible combination of sold campaigns to maximise the revenue",
          response = CampaignBuilderResponse.class)
  public CampaignBuilderResponse buildCampaign(
          @RequestBody CampaignBuilderRequest request, @RequestParam int monthlyInventory) {

    if (monthlyInventory <= 0) {
      // Throw bad request
    }

    return campaignBuilderService.buildOptimizedCampaign(monthlyInventory, request);
  }
}
