package ua.in.lbn.sb2;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Properties;

@Configuration
@Import({ProjectInfoAutoConfiguration.class})
public class ApplicationConfig {

    @Bean
    @ConditionalOnMissingBean
    public BuildProperties buildProperties() {
        return new BuildProperties(new Properties());
    }

}
