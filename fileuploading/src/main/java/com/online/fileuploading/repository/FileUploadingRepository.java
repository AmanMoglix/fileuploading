package com.online.fileuploading.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.fileuploading.domain.PlaFeed;
import com.online.fileuploading.domain.dto.CommonResponseObjectDTO;

@Repository
public interface FileUploadingRepository extends JpaRepository<PlaFeed, Long> {
	@Query(value = "Select new com.online.fileuploading.domain.dto.CommonResponseObjectDTO(p.msn,"
			+ " p.gtin,p.identifierExists,p.customLabel2,p.customLabel3,p.customLabel4,p.promotionId,p.isGoogleActive,"
			+ " p.isFacebookActive,p.isCriteoActive,p.availability) "
			+ " FROM com.online.fileuploading.domain.PlaFeed p "
			+ " WHERE p.msn like concat('%',:wildCard,'%') OR p.customLabel3 like concat('%',:wildCard,'%')"
			+ " OR p.customLabel4 like concat('%',:wildCard,'%') OR p.availability like concat('%',:wildCard,'%')"
			+ " OR p.promotionId like concat('%',:wildCard,'%')")
	public List<CommonResponseObjectDTO> findCustom(String wildCard,Pageable pageable);
}
