package ca.bc.gov.open.pcss.test.controllers;

import ca.bc.gov.open.pcss.test.services.TestService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) throws IOException {

        this.testService = testService;
        this.testService.setAuthentication("JusticePCSSCivilSecure-soapui-project-template.xml");
        this.testService.setAuthentication("PCSSCivil-soapui-project-template.xml");
        this.testService.setAuthentication("PCSS-Criminal-soapui-project-template.xml");
        this.testService.setAuthentication("pcssCriminalSecure-soapui-project-template.xml");
        this.testService.setAuthentication("PCSSCommon-soapui-project-template.xml");
        this.testService.setAuthentication("PCSSCommonSecure-soapui-project-template.xml");
    }

    @GetMapping(value = "/civil")
    public ResponseEntity runAllCivilTests() throws IOException {

        File f = testService.runAllCivilTests();
        if (f != null && f.exists()) {
            InputStream inputStream = new FileInputStream(f.getAbsolutePath());
            //      This is not great streaming would be better but small files should be ok
            byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=" + f.getName());
            responseHeaders.add("Content-Type", "application/zip");
            f.delete();
            return new ResponseEntity<byte[]>(out, responseHeaders, HttpStatus.OK);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");
            return new ResponseEntity<String>("{\"status\": \"All tests passed\"}", HttpStatus.OK);
        }
    }
    @GetMapping(value = "/criminal")
    public ResponseEntity runAllCriminalTests() throws IOException {

        File f = testService.runAllCriminalTests();
        if (f != null && f.exists()) {
            InputStream inputStream = new FileInputStream(f.getAbsolutePath());
            //      This is not great streaming would be better but small files should be ok
            byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=" + f.getName());
            responseHeaders.add("Content-Type", "application/zip");
            f.delete();
            return new ResponseEntity<byte[]>(out, responseHeaders, HttpStatus.OK);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");
            return new ResponseEntity<String>("{\"status\": \"All tests passed\"}", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/common")
    public ResponseEntity runAllCommonTests() throws IOException {

        File f = testService.runAllCommonTests();
        if (f != null && f.exists()) {
            InputStream inputStream = new FileInputStream(f.getAbsolutePath());
            //      This is not great streaming would be better but small files should be ok
            byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=" + f.getName());
            responseHeaders.add("Content-Type", "application/zip");
            f.delete();
            return new ResponseEntity<byte[]>(out, responseHeaders, HttpStatus.OK);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");
            return new ResponseEntity<String>("{\"status\": \"All tests passed\"}", HttpStatus.OK);
        }
    }

    @GetMapping("/ping")
    public String ping() {
        return "ping";
    }
}
