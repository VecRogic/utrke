package com.tvz.utrke.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvz.utrke.dto.RacePredictionDto;
import com.tvz.utrke.service.PerplexityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Slf4j
@Service
public class PerplexityServiceImpl implements PerplexityService {

    private final OpenAiChatModel openAiChatModel;

    @Override
    public RacePredictionDto getGeneratedRacePrediction(String race) {
        ObjectMapper mapper = new ObjectMapper();
        String aiResponse = this.openAiChatModel.call(getParsedRacePredictionToAIPayload(race));
        log.info("AI Response: {}", aiResponse);

        Pattern pattern = Pattern.compile("\\{.*?\\}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(aiResponse);
        if (matcher.find()) {
            aiResponse = matcher.group();
        }
        log.info("AI JSON Response: {}", aiResponse);
        try {
            return mapper.readValue(aiResponse, RacePredictionDto.class);
        } catch (Exception e) {
            log.error("Failed to parse JSON into RacePredictionDto for ai response {}", aiResponse, e);
            return new RacePredictionDto();
        }
    }

    private String getParsedRacePredictionToAIPayload(String race) {
        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, Object>> messages = getMaps(race);
        Map<String, Object> schema = getAnswerFormat();

        Map<String, Object> responseFormat = new HashMap<>();
        responseFormat.put("type", "json_schema");
        responseFormat.put("json_schema", Map.of("schema", schema));

        Map<String, Object> payload = new HashMap<>();
        payload.put("messages", messages);
        payload.put("response_format", responseFormat);

        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON into RacePredictionDto", e);
        }
    }

    private static List<Map<String, Object>> getMaps(String race) {
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "Be precise and concise.");

        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", "Predict the winner for this race " + race + ". Please output a JSON object containing the following fields: driver, constructor, explanation(no more than 2 sentences).");

        return Arrays.asList(systemMessage, userMessage);
    }

    private Map<String, Object> getAnswerFormat() {
        Map<String, Object> answerFormat = new HashMap<>();
        answerFormat.put("driver", null);
        answerFormat.put("constructor", null);
        answerFormat.put("explanation", null);

        return answerFormat;
    }


}
