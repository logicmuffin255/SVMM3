package com.mysandbox.helloworld.service;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysandbox.helloworld.model.ManifestDataModel;

@Component
public class JacksonJsonParserService{
    public ManifestDataModel parseManifestJson(String jsonToParse){
        ObjectMapper mapper = new ObjectMapper();
        ManifestDataModel manifestData;

        try{
            manifestData = mapper.readValue(jsonToParse, ManifestDataModel.class);
        } catch (IOException err){
            throw new RuntimeException(err);
        }

        return manifestData;
    }

    @Bean
    public CommandLineRunner startup(){
        return args -> {
        String testString = "{ \"Name\": \"Automate\", \"Author\": \"Pathoschild\", \"Version\": \"2.0.7\", \"MinimumApiVersion\": \"4.0.0\", \"MinimumGameVersion\": \"1.6.3\", \"Description\": \"Lets you automate crafting machines, fruit trees, and more by connecting them to chests.\", \"UniqueID\": \"Pathoschild.Automate\", \"EntryDll\": \"Automate.dll\", \"UpdateKeys\": [ \"Nexus:1063\" ] }";
        ManifestDataModel result = parseManifestJson(testString);
        System.out.println(result);
        };
    }
}
