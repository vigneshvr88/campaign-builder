package se.invidi.campaignbuilder.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import se.invidi.campaignbuilder.model.CampaignBuilderRequest;
import se.invidi.campaignbuilder.model.CampaignBuilderResponse;

import static se.invidi.campaignbuilder.service.CampaignBuilderService.buildOptimizedCampaign;

@RestController
@RequestMapping("/invidi")
public class CampaignBuilderController {

    @PostMapping("/campaign")
    @ApiOperation(
            value = "Build best possible combination of sold campaigns to maximise the revenue",
            response = CampaignBuilderResponse.class)
    public CampaignBuilderResponse buildCampaign(
            @RequestBody CampaignBuilderRequest request, @RequestParam int monthlyInventory) {

        if (monthlyInventory <= 0) {
            // Throw bad request
        }

        return buildOptimizedCampaign(monthlyInventory, request);
    }
}
