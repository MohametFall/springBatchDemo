package com.mfall.batchdemo.repository;

import com.mfall.batchdemo.domain.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EtudiantRepository extends CrudRepository<Etudiant,Long> , PagingAndSortingRepository<Etudiant, Long>, JpaRepository<Etudiant, Long> {

    List<Etudiant> findByNom(String nom);

    Etudiant findById(long Id);



}
