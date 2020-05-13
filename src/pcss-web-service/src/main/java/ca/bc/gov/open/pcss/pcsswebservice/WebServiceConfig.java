package ca.bc.gov.open.pcss.pcsswebservice;

import ca.bc.gov.open.pcss.pcsswebservice.civil.PcssCivilEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;


@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<CXFServlet> dispatcherServlet() {

        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean
    @Primary
    public DispatcherServletPath dispatcherServletPathProvider() {

        return () -> "";
    }

    @Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {

        SpringBus cxfBus = new SpringBus();
        return cxfBus;
    }

    @Bean
    public Endpoint CivilEndpoint(Bus bus, PcssCivilEndpoint pcssCivilEndpoint) {

        EndpointImpl endpoint = new EndpointImpl(bus, pcssCivilEndpoint);
        endpoint.setBindingUri(SOAPBinding.SOAP12HTTP_BINDING);
        endpoint.publish("JusticePCSSCivil.wsProvider.pcssCivil/JusticePCSSCivil_wsProvider_pcssCivil_Port");

        //endpoint.getProperties().put("schema-validation-enabled", "true");

        return endpoint;

    }

    @Bean
    public Endpoint CommonEndpoint(Bus bus, PcssCommonEndpoint pcssCommonEndpoint) {

        EndpointImpl endpoint = new EndpointImpl(bus, pcssCommonEndpoint);
        endpoint.publish("/JusticePCSSCommon.wsProvider:pcssCommon");

        return endpoint;
    }

    @Bean
    public Endpoint CriminalEndpoint(Bus bus, PcssCriminalEndpoint pcssCriminalEndpoint) {

        EndpointImpl endpoint = new EndpointImpl(bus, pcssCriminalEndpoint);
        endpoint.publish("/JusticePCSSCriminal.wsProvider:pcssCriminal");

        return endpoint;
    }

    @Bean
    public Endpoint ReportEndpoint(Bus bus, PcssReportEndpoint pcssReportEndpoint) {

        EndpointImpl endpoint = new EndpointImpl(bus, pcssReportEndpoint);
        endpoint.publish("/JusticePCSSCommon.wsProvider:pcssReport");

        return endpoint;
    }

    @Bean
    public Endpoint JusticeCddsEndpoint(Bus bus, JusticeCddsEndpoint justiceCddsEndpoint) {

        EndpointImpl endpoint = new EndpointImpl(bus, justiceCddsEndpoint);
        endpoint.publish("/JusticeCDDS.wsProvider:cdds");

        return endpoint;
    }

}
