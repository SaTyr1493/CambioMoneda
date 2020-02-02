package com.bcp.api.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcp.api.entity.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, String>{
	
	@Query("select m.tipocambio from Moneda m where m.monedaorigen = :monedaorigen and m.monedadestino = :monedadestino")
	Optional<Double> findTipoCambio(@Param("monedaorigen") String monedaorigen, @Param("monedadestino") String monedadestino);

	@Transactional
	@Modifying
	@Query("update Moneda m set m.tipocambio = :tipocambio where m.monedaorigen = :monedaorigen and m.monedadestino = :monedadestino")
	void updateTipoCambio(@Param("tipocambio") Double tipocambio, @Param("monedaorigen") String monedaorigen, @Param("monedadestino") String monedadestino);
}
