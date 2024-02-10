package com.example.demo.Profile.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Profile.Model.MainProfile;


public interface MainProfileJpaRepository extends JpaRepository<MainProfile, Long>{
    Optional<MainProfile> findByMobileNumber(String mobileNumber);
}
