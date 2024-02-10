package com.example.demo.Profile.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Profile.Model.ProfileDetails;



@Repository
public interface ProfileDetailsJpaRepository extends JpaRepository<ProfileDetails, Long>{
    
    ProfileDetails findByProfileId(long profileId);

    @Query(value = "SELECT * FROM profile_details s WHERE (CASE WHEN :gender = '' THEN true ELSE s.gender=:gender END) AND (CASE WHEN :casteGeneral = '' THEN true ELSE s.caste_general=:casteGeneral END) AND (CASE WHEN :occupationPlace = '' THEN true ELSE s.occupation_place=:occupationPlace END) AND (CASE WHEN :nativeState = '' THEN true ELSE s.native_state=:nativeState END) AND (CASE WHEN :maritialStatus = '' THEN true ELSE s.maritial_status=:maritialStatus END)", nativeQuery = true)
    public List<ProfileDetails> getAllFilters(@Param("gender") String gender, @Param("casteGeneral") String casteGeneral, @Param("occupationPlace") String occupationPlace, @Param("nativeState") String nativeState, @Param("maritialStatus") String maritialStatus); 
    
    @Query(value = "SELECT COUNT(*) FROM profile_details s", nativeQuery = true)
    String totalProfiles();
    
    @Query(value = "SELECT COUNT(*) FROM profile_details s WHERE s.gender='Male'", nativeQuery = true)
    String varuduProfiles();
    
    @Query(value = "SELECT COUNT(*) FROM profile_details s WHERE s.gender='Female'", nativeQuery = true)
    String vadhuvuProfiles();


}
