package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

    public Page<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate maxDateToday;
        if (maxDate == null || maxDate.isEmpty()) {
            maxDateToday = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        } else {
            maxDateToday = LocalDate.parse(maxDate);
        }
        LocalDate minLocalDate;
        if (minDate == null) {
            minLocalDate = maxDateToday.minusYears(1L);
        } else {
            minLocalDate = LocalDate.parse(minDate);
        }
        Page<Sale> result = repository.searchReport(minLocalDate, maxDateToday, name, pageable);
        return result.map(SaleMinDTO::new);
    }
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
}
