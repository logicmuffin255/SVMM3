package name.benshepley.SVMM3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
public class FileSystemRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemRepository.class);

    // TODO:
    public void createPath(Path source) {
        try {
            Files.createDirectory(source);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void movePath(Path source, Path destination) {
        File sourceFile = source.toFile();
        File destinationFile = destination.toFile();

        if (!sourceFile.exists()) {
            LOGGER.info("Move Path was called but source path did not exist.");
            return;
        }

        if (destinationFile.exists()) {
            LOGGER.info("Move Path was called but destination path did not exist.");
            return;
        }

        try {
            Files.move(source.toFile().toPath(), destination.toFile().toPath());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    // TODO:
    public void copyPath(Path source, Path destination) {
        File sourceFile = source.toFile();
        File destinationFile = destination.toFile();
    }

    public void deletePath(Path sourcePath) {
        File sourceFile = sourcePath.toFile();

        if (!sourceFile.exists()) {
            LOGGER.info("Delete Path was called but path did not exist.");
            return;
        }

        if (sourceFile.isDirectory() && Objects.requireNonNull(sourceFile.listFiles()).length == 0) {
            try {
                Files.delete(sourceFile.toPath());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return;
        }

        try (Stream<Path> walk = Files.walk(sourceFile.toPath())) {
            walk
                .map(Path::toFile)
                .forEach(File::delete);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


}
