package ca.bc.gov.open.pcss.civil.comparison;

import ca.bc.gov.open.pcss.civil.comparison.services.TestService;
import ca.bc.gov.open.pcss.three.GetFileDetailCivil;
import ca.bc.gov.open.pcss.three.GetFileDetailCivilRequest;
import ca.bc.gov.open.pcss.three.GetFileDetailCivilResponse;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComparisonRunner {
    @Autowired private TestService testService;

    public static void main(String args[]) {
        SpringApplication.run(ComparisonRunner.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            GetFileDetailCivil request = new GetFileDetailCivil();
            ca.bc.gov.open.pcss.one.GetFileDetailCivilRequest one =
                    new ca.bc.gov.open.pcss.one.GetFileDetailCivilRequest();
            one.setPhysicalFileId("3");
            one.setRequestAgencyIdentifierId("83.0001");
            one.setRequestDtm(Instant.now());
            one.setRequestPartId("83.0001");
            GetFileDetailCivilRequest three = new GetFileDetailCivilRequest();
            three.setGetFileDetailCivilRequest(one);
            request.setGetFileDetailCivilRequest(three);

            testService.compare(new GetFileDetailCivilResponse(), request);
        };
    }
}
