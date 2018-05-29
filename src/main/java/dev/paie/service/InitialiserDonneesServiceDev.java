package dev.paie.service;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void initialiser() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("cotisations-imposables.xml",
				"cotisations-non-imposables.xml", "entreprises.xml", "grades.xml", "profils-remuneration.xml");

		Collection<Entreprise> entreprises = ctx.getBeansOfType(Entreprise.class).values();
		for (Entreprise entreprise : entreprises) {
			em.persist(entreprise);
		}

		Collection<Grade> grades = ctx.getBeansOfType(Grade.class).values();
		for (Grade grade : grades) {
			em.persist(grade);
		}

		Collection<ProfilRemuneration> profilRemunerations = ctx.getBeansOfType(ProfilRemuneration.class).values();
		for (ProfilRemuneration profilRemuneration : profilRemunerations) {
			em.persist(profilRemuneration);
		}

		Collection<Cotisation> cotisations = ctx.getBeansOfType(Cotisation.class).values();
		for (Cotisation cotisation : cotisations) {
			em.persist(cotisation);
		}

		for (int i = 1; i <= 12; i++) {
			LocalDate dateDebut = LocalDate.of(LocalDate.now().getYear(), i, 1);
			LocalDate dateFin = LocalDate.of(LocalDate.now().getYear(), i,
					LocalDate.now().withMonth(i).lengthOfMonth());
			Periode periode = new Periode();
			periode.setDateDebut(dateDebut);
			periode.setDateFin(dateFin);
			em.persist(periode);
		}
	}

}
