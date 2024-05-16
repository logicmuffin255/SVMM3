package name.benshepley.SVMM3;

import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SVMM3Application {
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		FlatLightLaf.setup();
		SVMM3Application.context = new SpringApplicationBuilder(SVMM3Application.class)
				.headless(false)
				.run(args);
	}

	public static ApplicationContext getContext() {
		return SVMM3Application.context;
	}

}
