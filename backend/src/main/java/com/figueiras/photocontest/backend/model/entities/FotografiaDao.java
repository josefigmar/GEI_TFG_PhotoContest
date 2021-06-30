package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.Fotografia;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FotografiaDao extends PagingAndSortingRepository<Fotografia, Long> {
}
