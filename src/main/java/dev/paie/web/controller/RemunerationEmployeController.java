package dev.paie.web.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	EntrepriseRepository er;

	@Autowired
	ProfilRemunerationRepository prr;

	@Autowired
	GradeRepository gr;

	@Autowired
	RemunerationEmployeRepository rer;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");

		List<Entreprise> entreprises = er.findAll();
		mv.addObject("entreprises", entreprises);

		List<ProfilRemuneration> profileRemunerations = prr.findAll();
		mv.addObject("profil_remunerations", profileRemunerations);

		List<Grade> grades = gr.findAll();
		mv.addObject("grades", grades);

		mv.addObject("employe", new RemunerationEmploye());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String creerEmployeP(@ModelAttribute("employe") RemunerationEmploye reEmploye) {
		reEmploye.setDate(ZonedDateTime.now());
		rer.save(reEmploye);
		return "redirect:/mvc/employes/lister";

	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmployes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");

		List<RemunerationEmploye> remEmp = rer.findAll();
		mv.addObject("employes", remEmp);

		return mv;
	}

}