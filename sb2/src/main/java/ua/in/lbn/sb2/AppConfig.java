package ua.in.lbn.sb2;

import org.apache.commons.lang3.SystemUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import javax.sql.DataSource;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

@Configuration
@EnableConfigurationProperties({AppProperties.class})
@Import({ProjectInfoAutoConfiguration.class})
public class AppConfig {

    private final DataSourceProperties dataSourceProperties;
    private final AppProperties appProperties;

    public AppConfig(DataSourceProperties dataSourceProperties, AppProperties appProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.appProperties = appProperties;
    }

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
        String localeValue;
        String lcMessagesValue;

        if (SystemUtils.IS_OS_WINDOWS) {
            localeValue = "en-us";
            lcMessagesValue = "en-us";
        } else if (SystemUtils.IS_OS_MAC) {
            localeValue = "en_US";
            lcMessagesValue = "en_US";
        } else if (SystemUtils.IS_OS_LINUX) {
            localeValue = "en_US.utf8";
            lcMessagesValue = "en_US.utf8";
        } else {
            throw new IllegalStateException("System not detected!");
        }

        URI uri = URI.create(URI.create(dataSourceProperties.getUrl()).getSchemeSpecificPart());
        int port = uri.getPort();

        return EmbeddedPostgres.builder()
                .setCleanDataDirectory(false)
                .setDataDirectory(appProperties.getPostgresDataDirectory())
                .setPort(port)
                .setLocaleConfig("locale", localeValue)
                .setLocaleConfig("lc-messages", lcMessagesValue)
                .start()
                .getPostgresDatabase();
    }
}
