package com.example.SoapTp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        ServletRegistrationBean<MessageDispatcherServlet> registrationBean = new ServletRegistrationBean<>(servlet, "/ws/*");
        registrationBean.setName("MessageDispatcherServlet"); // Important: Must be EXACTLY this name
        return registrationBean;
    }

    @Bean(name = "currencyConverter") // This defines /ws/currencyConverter.wsdl
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema currencySchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CurrencyConverterPort");
        definition.setLocationUri("/ws"); // Matches the servlet mapping
        definition.setTargetNamespace("http://exemple.com/currencyconverter");
        definition.setSchema(currencySchema);
        return definition;
    }

    @Bean
    public XsdSchema currencySchema() {
        return new SimpleXsdSchema(new ClassPathResource("currency-converter.xsd"));
    }
}
