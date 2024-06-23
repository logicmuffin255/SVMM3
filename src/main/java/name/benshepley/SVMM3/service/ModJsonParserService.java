package name.benshepley.SVMM3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.annotation.PostConstruct;
import name.benshepley.SVMM3.model.filesystem.smapi.ModManifestDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ModJsonParserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModJsonParserService.class);

    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        this.objectMapper = JsonMapper.builder().configure(
                MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)

            .build();
    }

    public ModManifestDataModel parseManifestJson(String manifestJson) {
        ModManifestDataModel model = new ModManifestDataModel();
        try {
            manifestJson = manifestJson.replace("\uFEFF", "");
            model = this.objectMapper.readValue(manifestJson, ModManifestDataModel.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return model;
    }


}
