package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.repository.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
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

    public void createPath(File file) {
        System.out.println("Create Path");
    }

    public void movePath(File file, File target) {
        System.out.println("Move Path");
    }

    public void deletePath(File file) {
        System.out.println("Delete Path");
    }


}
