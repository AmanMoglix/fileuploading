package com.online.fileuploading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.fileuploading.domain.PlaFeed;
@Repository
public interface FileUploadingRepository extends JpaRepository<PlaFeed,Long>{

}
