package com.commerce.song.repository;

import com.commerce.song.domain.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    Resources findByResourceNmAndHttpMethod(String resourceName, String httpMethod);

    @Query("select DISTINCT  r from Resources r join fetch r.roleSet where r.resourceTp = 'url' order by r.seq desc")
    List<Resources> findAllResources();

    @Query("select r from Resources r join fetch r.roleSet where r.resourceTp = 'method' order by r.seq desc")
    List<Resources> findAllMethodResources();

    @Query("select r from Resources r join fetch r.roleSet where r.resourceTp = 'pointcut' order by r.seq desc")
    List<Resources> findAllPointcutResources();
}
