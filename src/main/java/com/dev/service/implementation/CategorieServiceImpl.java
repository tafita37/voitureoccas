package com.dev.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.model.categorie.Categorie;
import com.dev.repository.CategorieRepository;
import com.dev.service.CategorieService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    
    @Override
	public List<Categorie> findAllCategorie() {
        return categorieRepository.findAll();
	}

	@Override
	public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
	}

	@Override
	public Categorie update(int idCategorie, String nomCategorie) {
        Categorie categorie=categorieRepository.findById(idCategorie).get();
        categorie.setNomCategorie(nomCategorie);
        return categorieRepository.save(categorie);
	}

	@Override
	public void delete(int idCategorie) {
		categorieRepository.deleteById(idCategorie);
	}
	
	public List<Categorie> findAllCategorieById(int[] idCategorie) {
		ArrayList<Integer> idCategories=new ArrayList<>();
		for(int i=0; i<idCategorie.length; i++) {
			idCategories.add(idCategorie[i]);
		}
		return categorieRepository.findAllById(idCategories);
	}

	@Override
	public Optional<Categorie> findById(int idCategorie) {
		return categorieRepository.findById(idCategorie);
	}
}
