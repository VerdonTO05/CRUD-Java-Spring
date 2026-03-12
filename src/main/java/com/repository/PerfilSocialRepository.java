package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.PerfilSocial;
@Repository
public interface PerfilSocialRepository extends JpaRepository<PerfilSocial, Integer> {

}
