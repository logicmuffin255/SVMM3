package name.benshepley.SVMM3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class OperatingSystemRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemRepository.class);

    public void execute(String executable) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(executable);
            processBuilder.start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
