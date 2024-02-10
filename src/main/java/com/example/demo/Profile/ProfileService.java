package com.example.demo.Profile;


import java.io.IOException;
// import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
// import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Profile.Model.MainProfile;
import com.example.demo.Profile.Model.ProfileDetails;
import com.example.demo.Profile.Repository.MainProfileJpaRepository;
import com.example.demo.Profile.Repository.ProfileDetailsJpaRepository;
import com.example.demo.Profile.Repository.ProfileRepository;




@Service
public class ProfileService implements ProfileRepository {
    @Autowired
    private ProfileDetailsJpaRepository profileDetailsJpaRepository;

    @Autowired
    private MainProfileJpaRepository mainProfileJpaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;




    // Registration ------------------------
    @Override
    public String registerProfile(MultipartFile file, MainProfile mainProfile, ProfileDetails profileDetails) {
        try {

            String mobileNumber = mainProfile.getMobileNumber();
            Optional<MainProfile> profileFromDb = mainProfileJpaRepository.findByMobileNumber(mobileNumber);

            if (!profileFromDb.isPresent()) {
                mainProfile.setPassword(passwordEncoder.encode(mainProfile.getPassword()));
                MainProfile saveMainProfile = mainProfileJpaRepository.save(mainProfile);
                long profileId = saveMainProfile.getId();
                HashSet<Long> hset = new HashSet<>();
                profileDetails.setLiked(hset);
                profileDetails.setLiking(hset);
                profileDetails.setConnected(hset);
                profileDetails.setTokens(0);
                profileDetails.setProfileId(profileId);
                profileDetails.setImage(file.getBytes());
                profileDetailsJpaRepository.save(profileDetails);
                return "Registration Completed";
            }
            else {
                return "This Mobile Number already Registered";
            }
        }
        catch(Exception e) {
            return "Upload image format like jpg/png/jpeg only!";
        }
    }

    // Get Profile By Id ----------------------------------
    @Override
    public ArrayList<ProfileDetails> getProfileDetails(long profileId) {
        try{
            ProfileDetails profileDetails = profileDetailsJpaRepository.findByProfileId(profileId);
            ArrayList<ProfileDetails> profileDetailsList = new ArrayList<>();
            profileDetailsList.add(profileDetails);
            return profileDetailsList;
        }
        catch(Exception e) {
            ArrayList<ProfileDetails> arr = new ArrayList<>();
            return arr;
        }
    }

    // Get All Profiles -------------------------------------------------------------
    @Override
    public Set<ProfileDetails> getProfiles() {
        ArrayList<ProfileDetails> profiles = new ArrayList<>(profileDetailsJpaRepository.findAll());
        HashSet<ProfileDetails> mixedProfiles = new HashSet<>(profiles);
        return mixedProfiles;
    }


    
    // Liked List ----------------------------------------------------
    @Override
    public List<ProfileDetails> getLikedList(Authentication authentication) {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long profileId = mainProfile.getId();
        List<ProfileDetails> likedProfiles = new ArrayList<>();
        HashSet<Long> likedList = profileDetailsJpaRepository.findByProfileId(profileId).getLiked();
        for (Long eachProfileId : likedList) {
            likedProfiles.add(profileDetailsJpaRepository.findByProfileId(eachProfileId));
        }
        return likedProfiles;
    }


    // Liker List -----------------------------------
    @Override
    public List<ProfileDetails> getLikerList(Authentication authentication) {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long profileId = mainProfile.getId();
        List<ProfileDetails> likerProfiles = new ArrayList<>();
        HashSet<Long> likerList = profileDetailsJpaRepository.findByProfileId(profileId).getLiking();
        for (Long eachProfileId : likerList) {
            likerProfiles.add(profileDetailsJpaRepository.findByProfileId(eachProfileId));
        }
        return likerProfiles;
    }


    // Connected List ---------------------------------------------
    @Override
    public List<ProfileDetails> getConnectedList(Authentication authentication) {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long profileId = mainProfile.getId();
        List<ProfileDetails> connectedProfiles = new ArrayList<>();
        HashSet<Long> connectedList = profileDetailsJpaRepository.findByProfileId(profileId).getConnected();
        for (Long eachProfileId : connectedList) {
            connectedProfiles.add(profileDetailsJpaRepository.findByProfileId(eachProfileId));
        }
        return connectedProfiles;
    }


    // Connect Button ------------------------------------------------------------------
    @Override
    public String addLiked(Authentication authentication, long likedId) throws Exception{

        MainProfile likerMainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long likerProfileId = likerMainProfile.getId();
        ProfileDetails likerProfileDetails = profileDetailsJpaRepository.findByProfileId(likerProfileId);
        int likerTokens = likerProfileDetails.getTokens();
        HashSet<Long> likerLikedList = likerProfileDetails.getLiked();
        HashSet<Long> likerLikingList = likerProfileDetails.getLiking();
        HashSet<Long> likerConnectedList = likerProfileDetails.getConnected();
        String likerGender = likerProfileDetails.getGender();     

        ProfileDetails likedProfileDetails = profileDetailsJpaRepository.findByProfileId(likedId);
        String likedGender = likedProfileDetails.getGender();

        if (likerGender.equals(likedGender)) return "Request Denied! (Request can't be send to same Partner(Gender)";

        if (likerLikingList.contains(likedId)) return "This Profile already send connect Request to you. Please check in Liker Tab";

        if (likerConnectedList.contains(likedId)) return "You Already Connected to this Profile";

        if (likerLikedList.contains(likedId)) return "You Alreay Liked This Profile";

        if (likerTokens>0) {
            // HashSet<Long> likerLikedList = likerProfileDetails.getLiked();
            likerLikedList.add(likedProfileDetails.getProfileId());
            likerProfileDetails.setLiked(likerLikedList);
            profileDetailsJpaRepository.save(likerProfileDetails);
            
            HashSet<Long> likedLikingList = likedProfileDetails.getLiking();
            likedLikingList.add(likerProfileDetails.getProfileId());
            likedProfileDetails.setLiking(likedLikingList);
            profileDetailsJpaRepository.save(likedProfileDetails);

            return "Success";
        }
        else if (likerTokens<1) return "Insufficient Tokens";

        else return "Unknown Error";
        
        
    }

    // Cancel Request Button -----------------------------------------------------------
    @Override
    public String cancelProfile(Authentication authentication, long cancelId) throws Exception {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long profileId = mainProfile.getId();
        ProfileDetails profileDetails = profileDetailsJpaRepository.findByProfileId(profileId);
        HashSet<Long> likedList = profileDetails.getLiked();
        likedList.remove(cancelId);
        profileDetails.setLiked(likedList);
        profileDetailsJpaRepository.save(profileDetails);
        return "Request Cancelled Successfully";
    }

    // Accept Request Button ------------------------------------------------
    @Override
    public String acceptProfile(Authentication authentication, long requestId) throws Exception {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long acceptId = mainProfile.getId();
        ProfileDetails accepteeProfileDetails = profileDetailsJpaRepository.findByProfileId(acceptId);
        ProfileDetails requesteeProfileDetails = profileDetailsJpaRepository.findByProfileId(requestId);

        HashSet<Long> accepteeLikingList = accepteeProfileDetails.getLiking();
        HashSet<Long> accepteeConnectedList = accepteeProfileDetails.getConnected();

        HashSet<Long> requesteeLikedList = requesteeProfileDetails.getLiked();
        HashSet<Long> requesteeConnectedList = requesteeProfileDetails.getConnected();
        
        if (requesteeLikedList.contains(acceptId)) {

            if (accepteeProfileDetails.getTokens()>0 && requesteeProfileDetails.getTokens()>0) {
                accepteeProfileDetails.setTokens(accepteeProfileDetails.getTokens() - 1);
                requesteeProfileDetails.setTokens(requesteeProfileDetails.getTokens() - 1);
                
                accepteeLikingList.remove(requestId);
                accepteeProfileDetails.setLiking(accepteeLikingList);
                accepteeConnectedList.add(requestId);
                accepteeProfileDetails.setConnected(accepteeConnectedList);

                requesteeLikedList.remove(acceptId);
                requesteeProfileDetails.setLiked(requesteeLikedList);
                requesteeConnectedList.add(acceptId);
                requesteeProfileDetails.setConnected(requesteeConnectedList);

                profileDetailsJpaRepository.save(accepteeProfileDetails);
                profileDetailsJpaRepository.save(requesteeProfileDetails);
                return "Connection established Successfully. Please Check Contact Details in Connected List Tab";
            }
            else if (accepteeProfileDetails.getTokens()>0 && requesteeProfileDetails.getTokens()<1) return "Requested Profile has Insufficient Tokens";
            else if (accepteeProfileDetails.getTokens()<1) return "Insufficient Tokens";
            else return "Something Went Wrong";
        }
        else return "Something Went Wrong";
    }

    // View Contact Details Button -----------------------------------------------
    @Override
    public MainProfile contactProfile(Authentication authentication, long sendeeId) throws Exception {
        MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long requesteeId = mainProfile.getId();
        ProfileDetails requesteeProfileDetails = profileDetailsJpaRepository.findByProfileId(requesteeId);
        HashSet<Long> requesteeConnectedList = requesteeProfileDetails.getConnected();
        
        ProfileDetails sendeeProfileDetails = profileDetailsJpaRepository.findByProfileId(sendeeId);
        HashSet<Long> sendeeConnectedList = sendeeProfileDetails.getConnected();

        if (sendeeConnectedList.contains(requesteeId) && requesteeConnectedList.contains(sendeeId)) {
            MainProfile sendeeMainProfile = mainProfileJpaRepository.findById(sendeeId).get();
            sendeeMainProfile.setPassword("");
            return sendeeMainProfile;
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request(Both Parties should Accept eachother for Contact Details)");
    }

    @Override
    public String editProfile(Authentication authentication, MultipartFile file, MainProfile mainProfile,
            ProfileDetails profileDetails) throws IOException {
            MainProfile originalMainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
            long profileId = originalMainProfile.getId();
            ProfileDetails originalProfileDetails = profileDetailsJpaRepository.findByProfileId(profileId);
            if (mainProfile.getAlternateMobileNumber().length() != 0) originalMainProfile.setAlternateMobileNumber(mainProfile.getAlternateMobileNumber());
            if (profileDetails.getEducation().length() != 0) originalProfileDetails.setEducation(profileDetails.getEducation());
            if (profileDetails.getApDistrict().length() != 0) originalProfileDetails.setApDistrict(profileDetails.getApDistrict());
            if (profileDetails.getCityName().length() != 0) originalProfileDetails.setCityName(profileDetails.getCityName());
            if (profileDetails.getCountryName().length() != 0) originalProfileDetails.setCountryName(profileDetails.getCountryName());
            if (mainProfile.getDoorNo().length() != 0) originalMainProfile.setDoorNo(mainProfile.getDoorNo());
            if (mainProfile.getEmailId().length() != 0) originalMainProfile.setEmailId(mainProfile.getEmailId());
            if (profileDetails.getNativeState().length() != 0) originalProfileDetails.setNativeState(profileDetails.getNativeState());
            if (profileDetails.getOccupation().length() != 0) originalProfileDetails.setOccupation(profileDetails.getOccupation());
            if (profileDetails.getOccupationPlace().length() != 0) originalProfileDetails.setOccupationPlace(profileDetails.getOccupationPlace());
            if (profileDetails.getOccupationRole().length() != 0) originalProfileDetails.setOccupationRole(profileDetails.getOccupationRole());
            if (profileDetails.getOccupationState().length() != 0) originalProfileDetails.setOccupationState(profileDetails.getOccupationState());
            if (profileDetails.getOtherCountryCityName().length() != 0) originalProfileDetails.setOtherCountryCityName(profileDetails.getOtherCountryCityName());
            if (profileDetails.getPropertyValue().length() != 0) originalProfileDetails.setPropertyValue(profileDetails.getPropertyValue());
            if (profileDetails.getSalary().length() != 0) originalProfileDetails.setSalary(profileDetails.getSalary());
            if (mainProfile.getStreetName().length() != 0) originalMainProfile.setStreetName(mainProfile.getStreetName());
            if (mainProfile.getVillage().length() != 0) originalMainProfile.setVillage(mainProfile.getVillage());
            if (file.getSize() != 0) originalProfileDetails.setImage(file.getBytes());
            profileDetailsJpaRepository.save(originalProfileDetails);
            mainProfileJpaRepository.save(originalMainProfile);
            
            return "Profile Changes are Successfull";
    }

    @Override
    public ProfileDetails myProfile(Authentication authentication) {
        MainProfile originalMainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
        long profileId = originalMainProfile.getId();
        return profileDetailsJpaRepository.findByProfileId(profileId);
    }

    @Override
    public String deleteProfile(Authentication authentication, long profileId) throws Exception {
        boolean identify = false;
        if (authentication.getName().equals("0000000000")) {
            identify = true;
        }
        else {
            MainProfile mainProfile = mainProfileJpaRepository.findByMobileNumber(authentication.getName()).get();
            long originalProfileId = mainProfile.getId();
            if (originalProfileId == profileId) identify = true;
        }

        if (identify) {
            ProfileDetails profileDetails = profileDetailsJpaRepository.findByProfileId(profileId);
            long profileDetailsId = profileDetails.getDetailsId();
            HashSet<Long> profileLikedList = profileDetails.getLiked();
            HashSet<Long> profileLikerList = profileDetails.getLiking();
            HashSet<Long> profileConnectedList = profileDetails.getConnected();
            
            try {
                for (long each : profileLikedList) {
                    ProfileDetails likerProfile =  profileDetailsJpaRepository.findByProfileId(each);
                    HashSet<Long> likerList = likerProfile.getLiking();
                    likerList.remove(profileId);
                    likerProfile.setLiking(likerList);
                    profileDetailsJpaRepository.save(likerProfile);
                }
                for (long each : profileLikerList) {
                    ProfileDetails likedProfile = profileDetailsJpaRepository.findByProfileId(each);
                    HashSet<Long> likedList = likedProfile.getLiked();
                    likedList.remove(profileId);
                    likedProfile.setLiked(likedList);
                    profileDetailsJpaRepository.save(likedProfile);
                }
                for (long each: profileConnectedList) {
                    ProfileDetails connectedProfile = profileDetailsJpaRepository.findByProfileId(each);
                    HashSet<Long> connectedList = connectedProfile.getConnected();
                    connectedList.remove(profileId);
                    connectedProfile.setConnected(connectedList);
                    profileDetailsJpaRepository.save(connectedProfile);
                }
                profileDetailsJpaRepository.deleteById(profileDetailsId);
                mainProfileJpaRepository.deleteById(profileId);
                return "Profile Account Deleted";
            }
            catch(Exception e) {
                return "Something Went Wrong";
            }
        }
        else return "Forbidden Request";
    }

    @Override
    public Set<ProfileDetails> getFilterProfiles(String gender, String casteGeneral, String occupationPlace, String nativeState,
            String maritialStatus) {
            
            ArrayList<ProfileDetails> arrList = new ArrayList<>(profileDetailsJpaRepository.getAllFilters(gender, casteGeneral, occupationPlace, nativeState, maritialStatus));
            HashSet<ProfileDetails> filterProfiles = new HashSet<>(arrList);
            return filterProfiles;
        
    }






    @Override
    public String rechargeProfile(Authentication authentication, long profileId) {
        if (!authentication.getName().equals("0000000000")) return "Forbidden Request";
        try{
            ProfileDetails profileDetails = profileDetailsJpaRepository.findByProfileId(profileId);
            profileDetails.setTokens(profileDetails.getTokens()+5);
            profileDetailsJpaRepository.save(profileDetails);
            return "Successfully Recharged";
        }
        catch(Exception e) {
            return "Something Went Wrong!";
        }
    }

    @Override
    public String clearProfile(Authentication authentication, long profileId) {
        if (!authentication.getName().equals("0000000000")) return "Forbidden Request";
        try {
            ProfileDetails profileDetails = profileDetailsJpaRepository.findByProfileId(profileId);
            profileDetails.setTokens(0);
            profileDetailsJpaRepository.save(profileDetails);
            return "Profile Tokens are Recharged";
        }
        catch(Exception e) {
            return "Something Went Wrong!";
        }
    }

    @Override
    public MainProfile contactDetailsProfile(Authentication authentication, long profileId) {
        if (!authentication.getName().equals("0000000000")) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        try {
            MainProfile mainProfile = mainProfileJpaRepository.findById(profileId).get();
            mainProfile.setPassword("");
            return mainProfile;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String totalProfiles(Authentication authentication) {
        if (!authentication.getName().equals("0000000000")) return "Forbidden Request";
        return profileDetailsJpaRepository.totalProfiles();
    }

    @Override
    public String varuduProfiles(Authentication authentication) {
        if (!authentication.getName().equals("0000000000")) return "Forbidden Request";
        return profileDetailsJpaRepository.varuduProfiles();
    }

    @Override
    public String vadhuvuProfiles(Authentication authentication) {
        if (!authentication.getName().equals("0000000000")) return "Forbidden Request";
        return profileDetailsJpaRepository.vadhuvuProfiles();
    }

    @Override
    public List<ProfileDetails> getLikedListByAdmin(Authentication authentication, long profileId) {
        if (!authentication.getName().equals("0000000000")) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        List<ProfileDetails> likedProfiles = new ArrayList<>();
        HashSet<Long> likedList = profileDetailsJpaRepository.findByProfileId(profileId).getLiked();
        for (Long eachProfileId : likedList) {
            likedProfiles.add(profileDetailsJpaRepository.findByProfileId(eachProfileId));
        }
        return likedProfiles;
    }

    @Override
    public List<ProfileDetails> getLikerListByAdmin(Authentication authentication, long profileId) {
        List<ProfileDetails> likerProfiles = new ArrayList<>();
        HashSet<Long> likerList = profileDetailsJpaRepository.findByProfileId(profileId).getLiking();
        for (Long eachProfileId : likerList) {
            likerProfiles.add(profileDetailsJpaRepository.findByProfileId(eachProfileId));
        }
        return likerProfiles;
    }

    @Override
    public List<ProfileDetails> getConnectedListByAdmin(Authentication authentication, long profileId) {
        List<ProfileDetails> connectedProfiles = new ArrayList<>();
        HashSet<Long> connectedList = profileDetailsJpaRepository.findByProfileId(profileId).getConnected();
        for (Long eachProfileId : connectedList) {
            connectedProfiles.add(profileDetailsJpaRepository.findByProfileId(eachProfileId));
        }
        return connectedProfiles;
    }

    

    





    


        // Clear ----------------------------------------------
        // public String clear() {
        //     List<ProfileDetails> list = profileDetailsJpaRepository.findAll();
        //     HashSet<Long> hset = new HashSet<>();
        //     for (ProfileDetails each : list) {
        //         each.setConnected(hset);
        //         each.setLiking(hset);
        //         each.setLiked(hset);
        //         profileDetailsJpaRepository.save(each);
        //     }
        //     return "Successfully cleared";
        // }


    

    


}
