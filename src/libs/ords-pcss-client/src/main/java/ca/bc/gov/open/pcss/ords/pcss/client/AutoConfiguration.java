package ca.bc.gov.open.pcss.ords.pcss.client;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OrdsProperties.class)
public class AutoConfiguration {

    private final OrdsProperties ordsProperties;

    public AutoConfiguration(OrdsProperties ordsProperties) {
        this.ordsProperties = ordsProperties;
    }

    @Bean(name = "pcssApiClient")
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();

        apiClient.setBasePath(ordsProperties.getBasePath());

        if(StringUtils.isNotBlank(ordsProperties.getUsername()))
            apiClient.setUsername(ordsProperties.getUsername());

        if(StringUtils.isNotBlank(ordsProperties.getPassword()))
            apiClient.setPassword(ordsProperties.getPassword());

        return apiClient;
    }

    @Bean
    public PcssApi pcssApi(@Qualifier("pcssApiClient") ApiClient apiClient) {
        return new PcssApi(apiClient);
    }

    @Bean
    public PcssCivilApi pocsscivilApi(@Qualifier("pcssApiClient") ApiClient apiClient) {
        return new PcssCivilApi(apiClient);
    }

}

