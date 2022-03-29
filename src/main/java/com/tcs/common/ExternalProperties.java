package com.tcs.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalProperties {

    @Value("${secret.key}")
    public String secretKey;
}
