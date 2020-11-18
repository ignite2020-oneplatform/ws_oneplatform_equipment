package com.oneplatform.equipment.service;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2IntentMessageText;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.oneplatform.equipment.entity.Equipment;
import com.oneplatform.equipment.repository.EquipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DialogFlowService {

    private static final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
    private final EquipmentRepository equipmentRepository;

    public GoogleCloudDialogflowV2WebhookResponse processMessage(String incomingMessage) {
        log.info("Incoming Message:\n" + incomingMessage);
        try {
            GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(incomingMessage).parse(GoogleCloudDialogflowV2WebhookRequest.class);
            if (request.getQueryResult().getAction().contains("TrailerFinder.TrailerFinder-custom")) {
                String equipmentNumber = getEquipmentNumber(request);
                String responseText = Optional.ofNullable(equipmentRepository.findByEquipmentNumber(equipmentNumber))
                        .map(this::equipmentFoundMessage).orElse(equipmentNotFoundMessage(equipmentNumber));
                return buildWebhookResponse(responseText);
            }
        } catch (IOException e) {
            log.error("Json Message parsing issue: " + e.getMessage());
        }
        return null;
    }

    private String getEquipmentNumber(GoogleCloudDialogflowV2WebhookRequest request) {
        Map<String, Object> parameters = request.getQueryResult().getParameters();
        return Optional.ofNullable(parameters.get("number-integer"))
                .map(Object::toString).orElse(null);
    }

    private String equipmentFoundMessage(Equipment equipment) {
        return "Your trailer is at " + equipment.getGpsCurrentLocation();
    }

    private String equipmentNotFoundMessage(String trailerNumber) {
        return "Sorry, unable to locate your trailer " + trailerNumber + ". Please check if the given trailer number is correct";
    }

    private GoogleCloudDialogflowV2WebhookResponse buildWebhookResponse(String responseText) {
        return new GoogleCloudDialogflowV2WebhookResponse()
                .setFulfillmentMessages(Collections.singletonList(new GoogleCloudDialogflowV2IntentMessage()
                        .setText(new GoogleCloudDialogflowV2IntentMessageText().setText(Collections.singletonList(responseText)))));
    }

}
