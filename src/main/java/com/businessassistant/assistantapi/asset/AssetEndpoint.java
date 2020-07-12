package com.businessassistant.assistantapi.asset;

import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.*;
import com.businessassistant.assistantapi.gen.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class AssetEndpoint {
    private AssetService assetService;

    @Autowired
    public AssetEndpoint(AssetService assetService) {
        this.assetService = assetService;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/asset", localPart = "getAllAssetsRequest")
    @ResponsePayload
    public GetAllAssetsResponse findAll(@RequestPayload GetAllAssetsRequest getAllAssetsRequest){

        List<Asset> assets = assetService.findAll();
        GetAllAssetsResponse getAllAssetsResponse = new GetAllAssetsResponse();

        getAllAssetsResponse.setAssets(assets);

        return getAllAssetsResponse;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/asset", localPart = "getAssetRequest")
    @ResponsePayload
    public GetAssetResponse getAssetById(@RequestPayload GetAssetRequest getAssetRequest){
        Optional<Asset> asset = assetService.findById(getAssetRequest.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        GetAssetResponse getAssetResponse = new GetAssetResponse();

        if(!asset.isPresent()){
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No asset with this id");
            throw new ServiceFaultException("Not Found",serviceStatus);
        }

        Asset payload =asset.get();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        getAssetResponse.setAsset(payload);
        getAssetResponse.setServiceStatus(serviceStatus);

        return getAssetResponse;
    }
}
