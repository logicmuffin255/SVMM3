package name.benshepley.SVMM3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SVMM3Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SVMM3Application.class)
				.headless(false)
				.run(args);
	}

}
