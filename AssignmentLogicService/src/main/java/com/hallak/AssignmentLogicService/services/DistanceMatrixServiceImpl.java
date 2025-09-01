package com.hallak.AssignmentLogicService.services;

import com.hallak.AssignmentLogicService.SyncConfig.Google.DistanceMatrixClient;
import com.hallak.AssignmentLogicService.dtos.DistanceMatrixDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final DistanceMatrixClient distanceMatrixClient;

    @Value("${GOOGLE_MAPS_API_KEY}")
    private String apiKey;

    @Autowired
    public DistanceMatrixServiceImpl(DistanceMatrixClient distanceMatrixClient) {
        this.distanceMatrixClient = distanceMatrixClient;
    }

    @Override
    public int getTravelTimeInHours(String originCep, String destinationCep) {
        DistanceMatrixDTO response = distanceMatrixClient.getDistanceMatrix(originCep, destinationCep, apiKey);
        return response.getRows().getFirst().getElements().getFirst().getDuration().getValue() / 3600;


    }

}
