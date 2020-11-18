package com.oneplatform.equipment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.oneplatform.equipment.service.DialogFlowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dialogFlow")
@AllArgsConstructor
public class DialogFlowController {

    private final DialogFlowService dialogFlowService;
    private final ObjectMapper objectMapper;

    @PostMapping
    ResponseEntity<GoogleCloudDialogflowV2WebhookResponse> processMessage(@RequestHeader(value = "senderUserId", required = false) String senderUserId,
                                                                          @RequestBody String message) {
        return Optional.ofNullable(dialogFlowService.processMessage(message))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
