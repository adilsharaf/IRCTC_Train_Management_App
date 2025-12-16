package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.dto.TrainDTO;
import com.tcs.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Long>{

	Train findByTrainId(Long id);
}
