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

    public void mkdir(Path source) {
        try {
            Files.createDirectory(source);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void mv(Path source, Path destination) {
        File sourceFile = source.toFile();
        File destinationFile = destination.toFile();

        if (!sourceFile.exists()) {
            LOGGER.error("Move was called but source path did not exist.");
            throw new RuntimeException("Move was called but source path did not exist.");
        }

        if (!Files.isDirectory(source) && Files.isDirectory(destination)) {
            try {
                Files.move(source.toFile().toPath(), destination.toFile().toPath());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        } else if (Files.isDirectory(source) && Files.isDirectory(destination)) {
            if (!sourceFile.renameTo(new File(destinationFile + "\\" + sourceFile.getName()))) {
                throw new RuntimeException("Unable to move " + sourceFile + " to " + destinationFile);
            }
        } else {
            throw new RuntimeException("Cannot move file into file or directory into file. Unable to move " + sourceFile + " to " + destinationFile);
        }
    }

    public void cp(Path source, Path destination) {
        File sourceFile = source.toFile();

        if (!sourceFile.exists()) {
            LOGGER.error("Copy was called but source path did not exist.");
            throw new RuntimeException("Copy was called but source path did not exist.");
        }

        try {
            Files.copy(source.toFile().toPath(), destination.toFile().toPath());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void rm(Path sourcePath) {
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
