package name.benshepley.SVMM3;

import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class SVMM3Application {
    public static final String APPLICATION_VERSION = "1.0";

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new SpringApplicationBuilder(SVMM3Application.class)
                .headless(false)
                .run(args);
    }


}

