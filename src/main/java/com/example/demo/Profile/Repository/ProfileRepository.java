package com.example.demo.Profile.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Profile.Model.MainProfile;
import com.example.demo.Profile.Model.ProfileDetails;

public interface ProfileRepository {
    
    String registerProfile(MultipartFile file, MainProfile mainProfile, ProfileDetails profileDetails) throws IOException;
    
    String editProfile(Authentication authentication, MultipartFile file, MainProfile mainProfile, ProfileDetails profileDetails) throws IOException;

    ArrayList<ProfileDetails> getProfileDetails(long profileId);
    
    Set<ProfileDetails> getProfiles();

    Set<ProfileDetails> getFilterProfiles(String gender, String casteGeneral, String occupationPlace, String nativeState, String maritialStatus);
    
    String addLiked(Authentication authentication, long likedId) throws Exception;

    String cancelProfile(Authentication authentication, long cancelId) throws Exception;

    String acceptProfile(Authentication authentication, long requestId) throws Exception;

    String deleteProfile(Authentication authentication, long profileId) throws Exception;

    MainProfile contactProfile(Authentication authentication, long contactId) throws Exception;

    List<ProfileDetails> getLikedList(Authentication authentication);

    List<ProfileDetails> getLikerList(Authentication authentication);

    List<ProfileDetails> getConnectedList(Authentication authentication);

    ProfileDetails myProfile(Authentication authentication);


    // Admin ============
    String rechargeProfile(Authentication authentication, long profileId);

    String clearProfile(Authentication authentication, long profileId);

    MainProfile contactDetailsProfile(Authentication authentication, long profileId);

    String totalProfiles(Authentication authentication);

    String varuduProfiles(Authentication authentication);
    
    String vadhuvuProfiles(Authentication authentication);

    List<ProfileDetails> getLikedListByAdmin(Authentication authentication, long profileId);

    List<ProfileDetails> getLikerListByAdmin(Authentication authentication, long profileId);

    List<ProfileDetails> getConnectedListByAdmin(Authentication authentication, long profileId);
}
