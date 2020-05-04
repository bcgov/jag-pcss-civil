package ca.bc.gov.open.pcss.pcssreportsapi;

import org.apache.commons.lang3.StringUtils;

public class Keys {

    protected Keys() {}

    // **************** SOAP CONFIGURATION ****************

    /**
     * The xml namespace
     */
    public static final String NAMESPACE_URI = "http://github.com/bcgov/pcss";

    /**
     * DO NOT CHANGE - The default output notification value.
     */
    public static final String REPORTS_SERVICE_VALUE = "pcssReportsService";

    /**
     * DO NOT CHANGE - The xsd files that defines requests and responses.
     */
    public static final String REPORTS_SERVICE_WSDL = REPORTS_SERVICE_VALUE + ".wsdl";

    /**
     * The name of the SOAP port.
     */
    public static final String REPORTS_SERVICE_PORT = StringUtils.capitalize(REPORTS_SERVICE_VALUE) + "Port";

}
