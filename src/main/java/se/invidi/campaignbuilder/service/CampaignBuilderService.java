package se.invidi.campaignbuilder.service;

import org.springframework.stereotype.Service;
import se.invidi.campaignbuilder.model.CampaignBuilderRequest;
import se.invidi.campaignbuilder.model.CampaignBuilderResponse;
import se.invidi.campaignbuilder.model.CampaignInformation;
import se.invidi.campaignbuilder.model.CampaignInformationPerCustomer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CampaignBuilderService {

    public CampaignBuilderResponse buildOptimizedCampaign(
            int monthlyInventory, CampaignBuilderRequest request) {
        CampaignInformation[] arrayOfCampaigns =
                request.getCampaignInformationList().stream().toArray(CampaignInformation[]::new);
        Map<CampaignInformation, Integer> resultMap =
                unboundedKnapsack(monthlyInventory, arrayOfCampaigns.length, arrayOfCampaigns);

        CampaignBuilderResponse response = new CampaignBuilderResponse();

        List<CampaignInformationPerCustomer> campaignInformationPerCustomerList = new ArrayList<>();
        for (CampaignInformation campaign : arrayOfCampaigns) {

            CampaignInformationPerCustomer campaignInformationPerCustomer =
                    new CampaignInformationPerCustomer();

            campaignInformationPerCustomer.setCustomer(campaign.customer);

            int numberOfCampaignToSell = resultMap.containsKey(campaign) ? resultMap.get(campaign) : 0;
            campaignInformationPerCustomer.setNumberOfCampaignsToSell(numberOfCampaignToSell);
            campaignInformationPerCustomer.setTotalImpressions(
                    campaign.impressionsPerCampaign * numberOfCampaignToSell);
            campaignInformationPerCustomer.setTotalRevenue(
                    campaign.pricePerCampaign * numberOfCampaignToSell);

            campaignInformationPerCustomerList.add(campaignInformationPerCustomer);
        }
        response.setCampaignInformationPerCustomer(campaignInformationPerCustomerList);
        response.setTotalImpressions(
                campaignInformationPerCustomerList.stream()
                        .map(campaign -> campaign.getTotalImpressions())
                        .reduce(0L, (campaign1, campaign2) -> campaign1 + campaign2));
        response.setTotalRevenue(
                campaignInformationPerCustomerList.stream()
                        .map(campaign -> campaign.getTotalRevenue())
                        .reduce(0, (campaign1, campaign2) -> campaign1 + campaign2));

        return response;
    }

    private Map<CampaignInformation, Integer> unboundedKnapsack(
            int monthlyInventory, int noOfCampaigns, CampaignInformation[] campaignInformations) {

        long[] optimisedValue = new long[monthlyInventory + 1];
        long[] result = new long[monthlyInventory + 1];

        for (int i = 0; i <= monthlyInventory; i++) {
            for (int j = 0; j < noOfCampaigns; j++) {
                int campaignImpression = campaignInformations[j].impressionsPerCampaign;
                int campaignValue = campaignInformations[j].pricePerCampaign;
                if (campaignImpression <= i) {
                    if (optimisedValue[i - campaignImpression] + campaignValue > optimisedValue[i]) {
                        optimisedValue[i] = optimisedValue[i - campaignImpression] + campaignValue;
                        result[i] = campaignValue;
                    }
                }
            }
        }

        long currentValue = result[monthlyInventory];
        Map<CampaignInformation, Integer> finalMap = new HashMap<>();
        for (int i = monthlyInventory; i > 0; ) {
            int index = indexOf(campaignInformations, currentValue);
            if (index == -1) {
                break;
            }
            CampaignInformation currentCampaign = campaignInformations[index];
            int currentImpression = campaignInformations[index].impressionsPerCampaign;

            if (finalMap.containsKey(currentCampaign)) {
                int v = finalMap.get(currentCampaign);
                finalMap.put(currentCampaign, v + 1);
            } else {
                finalMap.put(currentCampaign, 1);
            }
            i = i - currentImpression;
            currentValue = result[i];
        }
        return finalMap;
    }

    public int indexOf(CampaignInformation[] arr, long value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].pricePerCampaign == value) {
                return i;
            }
        }
        return -1;
    }
}
