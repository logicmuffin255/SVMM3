package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.nio.file.Path;

@Controller
public class OperatingSystemController {
    // Spring Beans:
    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    public OperatingSystemController(OperatingSystemRepository operatingSystemRepository) {
        this.operatingSystemRepository = operatingSystemRepository;
    }

    public void executeProcess(Path path) {

    }

    public void createPath() {

    }

    public void movePath() {

    }

    public void deletePath() {

    }


}
