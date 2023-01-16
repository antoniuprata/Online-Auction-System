package com.demo.AuctionSystemBE.repositories;

import com.demo.AuctionSystemBE.models.Obj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjRepository extends JpaRepository<Obj, Long> {

    @Transactional
    @Query("select o from Obj o join o.user u  where u.email = :email")
    List<Obj> findAllObjectsByEmail(String email);

    @Transactional
    @Query(nativeQuery = true, value = "select * from schemaproiecttw.object where enddate>now()")
    List<Obj> findAllActiveObjects();

    @Transactional
    @Query("select o from Obj o where o.id = :id")
    Optional<Obj> findObjectById(Long id);

    @Transactional
    @Query(nativeQuery = true, value = "select * from schemaproiecttw.object where enddate>now() and id=:id")
    Optional<Obj> checkIfActive(Long id);

    @Transactional
    @Query(nativeQuery = true, value = "select * from schemaproiecttw.object where enddate<=now() and id=:id")
    Optional<Obj> checkIfInactive(Long id);

    @Transactional
    @Query(nativeQuery = true, value = "select * from schemaproiecttw.object where enddate<=now()")
    List<Obj> findAllInactiveObjects();
}
