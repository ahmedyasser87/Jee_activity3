package ma.emsi.hopital1;

import ma.emsi.hopital1.entities.Patient;
import ma.emsi.hopital1.reposiroty.PatientReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Hopital1Application implements CommandLineRunner {
    @Autowired
    private PatientReposiroty patientReposiroty;

    public static void main(String[] args) {
        SpringApplication.run(Hopital1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        patientReposiroty.save(new Patient(null, "Mohamed", new Date(), false, 314));
        patientReposiroty.save(new Patient (null, "Hanane", new Date(), false, 4321));
        patientReposiroty.save(new Patient (null, "Imane", new Date(), true, 134));

    }

}
