package com.mfall.batchdemo.job;

import com.mfall.batchdemo.domain.Etudiant;
import org.springframework.batch.item.ItemProcessor;

import java.time.Duration;
import java.time.Instant;

public class EtudiantProcessor implements ItemProcessor <Etudiant,Etudiant> {
    @Override
    public Etudiant process(Etudiant etudiant) throws Exception {
        Instant inst = Instant.parse("2019-02-03T10:37:30.00Z");
        etudiant.setNaissance(inst.plus(Duration.ofHours(5).plusMinutes(4)));
        etudiant.setAge(etudiant.getAge() + 1);
        etudiant.setNom(etudiant.getNom()+"N");
        etudiant.setPrenom(etudiant.getPrenom() + "P");
        return etudiant;
    }
}
