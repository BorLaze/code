package ua.in.lbn.sb2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Properties;

@Configuration
@Import({ProjectInfoAutoConfiguration.class})
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public BuildProperties buildProperties() {
        return new BuildProperties(new Properties());
    }

    @Bean
    @ConditionalOnMissingBean
    public GitProperties gitProperties() {
        return new GitProperties(new Properties());
    }
}
