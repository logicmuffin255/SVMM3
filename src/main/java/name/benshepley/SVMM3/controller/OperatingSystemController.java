package name.benshepley.SVMM3.controller;

import name.benshepley.SVMM3.service.FileSystemSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class OperatingSystemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperatingSystemController.class);

    /* Spring Beans: */
    private final FileSystemSyncService fileSystemSyncService;

    @Autowired
    public OperatingSystemController(FileSystemSyncService fileSystemSyncService) {
        this.fileSystemSyncService = fileSystemSyncService;
    }

    public void execute(String... source) {
        try {
            new ProcessBuilder(source)
                    .start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void sync()  {
        try {
            this.fileSystemSyncService.sync();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
