package name.benshepley.SVMM3.repository;

import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class OperatingSystemRepository {
    public void execute(String executable) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(executable);
            processBuilder.start();
        } catch (IOException e) {
            //TODO: LOGGING
        }
    }
}
