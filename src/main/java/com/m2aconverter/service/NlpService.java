package com.m2aconverter.service;

import com.m2aconverter.entity.Action;
import com.m2aconverter.entity.Element;
import com.m2aconverter.entity.Page;
import com.m2aconverter.util.ActionType;
import com.m2aconverter.util.ElementType;
import common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class NlpService {

    @Autowired
    private RestTemplate restTemplate;

    private Map<String, List<String>> nlps;

    NlpService(RestTemplate restTemplate
//            , WebClient.Builder builder
    ) {
        this.restTemplate = restTemplate;
//        this.builder = builder;
    }

    public void nlpLoader() {

        log.info("NLP loader started");

        nlps = new HashMap<>();

        ResponseDTO responseDTO = restTemplate.getForObject("http://ex-logic:8090/nlpfile", ResponseDTO.class);

//        ResponseDTO responseDTO = builder.baseUrl("http://ex-logic/nlpfile")

        byte[] data = responseDTO.data;
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\User\\Documents\\IntelliJ\\Microservices\\Converter\\m2a-converter\\src\\main\\resources\\nlps.txt")) {
            fileOutputStream.write(data);
            Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\User\\Documents\\IntelliJ\\Microservices\\Converter\\m2a-converter\\src\\main\\resources\\nlps.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    List<String> newNlps = new ArrayList<>();
                    String nlpType = line.split("-")[0];
                    newNlps.add(line.split("-")[1].trim());
                    nlps.put(nlpType, newNlps);
                }
            }
            System.out.println("Nlps loaded");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param action
     * @return Appropriate NLP chose based on action
     */

    public ResponseEntity<String> getNlpByAction(Action action) {
        if (nlps == null) nlpLoader();
        Page page = action.getPage();
        ActionType actionType = action.getAction();
        Element element = action.getElement();
        ElementType elementType = element.getType();
        String scriptType = action.getScriptType();

//        nlps.entrySet().stream().forEach(System.out::println);

        // NLP picking logic
//        Map.Entry<String, List<String>> filteredNlps =
        nlps.entrySet().stream()
//                .filter((entry) -> entry.getKey().contains(action.getScriptType()) && entry.getValue().contains(actionType.toString()))
                .filter((entry) -> entry.getValue().get(0).toLowerCase().contains(actionType.toString().toLowerCase())).forEach(System.out::println);

//        System.out.println(filteredNlps);
        return null;
    }
}