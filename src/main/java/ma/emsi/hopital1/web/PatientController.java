package ma.emsi.hopital1.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.emsi.hopital1.entities.Patient;
import ma.emsi.hopital1.reposiroty.PatientReposiroty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientReposiroty patientReposiroty;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "5") int s,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Patient> patientPage = patientReposiroty.findByNomContains(kw, PageRequest.of(p, s));
        model.addAttribute("Listpatients", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);

        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", kw);
        return "patients";

    }
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id , String keyword, int page )
    {
        Patient patient=patientReposiroty.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("patient introuvable");
        model.addAttribute("patient",new Patient());
        model.addAttribute("ketword",keyword);
        model.addAttribute("page",page );

                return "editPatient";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {

        patientReposiroty.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;

    }

    @GetMapping("/formPatients")
    public String formPatient(Model model) {

        model.addAttribute("patient", new Patient());
        return "formPatients";

    }

    @PostMapping("/save")
    public String sav(Model model, @Valid Patient patient, BindingResult bindingResult,
                      @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "") String keyword  ) {
        if (bindingResult.hasErrors()) return "formPatients";
        patientReposiroty.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }


}