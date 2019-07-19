package ua.in.lbn.sb2;

import org.apache.commons.lang3.SystemUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

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

    @Bean
    @Profile({"default"})
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setMaxPayloadLength(10240);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }

    @Bean
    public DataSource dataSource() throws IOException {
        String locale = "locale";
        String lcMessages = "lc-messages";

        EmbeddedPostgres.Builder builder;

        if (SystemUtils.IS_OS_WINDOWS) {
            builder = EmbeddedPostgres.builder()
                    .setLocaleConfig(locale, "en-us")
                    .setLocaleConfig(lcMessages, "en-us");
        } else if (SystemUtils.IS_OS_MAC) {
            builder = EmbeddedPostgres.builder()
                    .setLocaleConfig(locale, "en_US")
                    .setLocaleConfig(lcMessages, "en_US");
        } else if (SystemUtils.IS_OS_LINUX) {
            builder = EmbeddedPostgres.builder()
                    .setLocaleConfig(locale, "en_US.utf8")
                    .setLocaleConfig(lcMessages, "en_US.utf8");
        } else {
            throw new RuntimeException("System not detected!");
        }

        return builder
                .start()
                .getPostgresDatabase();
    }
}
