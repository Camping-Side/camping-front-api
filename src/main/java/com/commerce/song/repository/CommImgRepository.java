package com.commerce.song.repository;

import com.commerce.song.domain.entity.CommImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface CommImgRepository extends JpaRepository<CommImg, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update CommImg c set c.type = 98 where c.imgId = ?1")
    int updateConfirmTypeByImgId(@NonNull Long imgId);
}
