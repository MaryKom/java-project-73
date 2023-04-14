package hexlet.code.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder;

@Configuration
@ComponentScan({
// UPDATE TO YOUR PROJECT PACKAGE
        "hexlet.code.app"
})
public class RollbarConfig {

    // Добавляем токен через переменные окружения
    @Value("${rollbar_token:}")
    private String rollbarToken;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    /**
     * Register a Rollbar bean to configure App with Rollbar.
     */
    @Bean
    public Rollbar rollbar() {
        //9a552721d243421987271e0ce13e4916

        return new Rollbar(getRollbarConfigs(rollbarToken));
    }

    private Config getRollbarConfigs(String accessToken) {

        return RollbarSpringConfigBuilder.withAccessToken(accessToken)
                .environment("development")
                .enabled(activeProfile == "prod")
                .build();
    }
}
