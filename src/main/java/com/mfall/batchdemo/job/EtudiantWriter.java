package com.mfall.batchdemo.job;

import com.mfall.batchdemo.domain.Etudiant;
import com.mfall.batchdemo.repository.EtudiantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class EtudiantWriter implements ItemWriter<Etudiant> {

    private static Logger logger = LoggerFactory.getLogger(EtudiantWriter.class);
    @Autowired
    EtudiantRepository etuRepo;

    @Override
    public void write(List<? extends Etudiant> list) throws Exception {
        logger.info("######## BATCH JPA WRITE ETUDIANT ########" + list.size());
        list.stream().forEach(etu-> ((Etudiant) etu).setPrenom("JAP"));

        logger.info("######## BATCH JPA WRIET ETUDIANT ########");
        list.stream().map(e->etuRepo.save(e));
    }
}
