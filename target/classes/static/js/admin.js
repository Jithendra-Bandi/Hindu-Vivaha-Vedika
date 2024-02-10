const searchInput = document.getElementById("searchInput");
const searchError = document.getElementById("searchError");
const display = document.getElementById("display");
const totalCount = document.getElementById("totalCount");
const varuduCount = document.getElementById("varuduCount");
const vadhuvuCount = document.getElementById("vadhuvuCount");

fetch("http://localhost:8080/admin/totalProfiles").then(res => res.text()).then(data => totalCount.textContent="Total: "+data).catch(err=> searchError.text=err);
fetch("http://localhost:8080/admin/varuduProfiles").then(res => res.text()).then(data => varuduCount.textContent="Varudu: "+data).catch(err=> searchError.text=err);
fetch("http://localhost:8080/admin/vadhuvuProfiles").then(res => res.text()).then(data => vadhuvuCount.textContent="Vadhuvu: "+data).catch(err=> searchError.text=err);






searchInput.addEventListener("keydown", event => {
    if (event.key === "Enter") search();
})

function search() {
    let searchId = searchInput.value.trim();
    if (/^\d+$/.test(searchId)) {
        searchError.textContent="";
        searchInput.value="";
        // fetch the mainProfile details here...
        fetch("http://localhost:8080/profile/"+searchId)
            .then(res => res.json())
            .then(profiles => {
                let content= "";
                profiles.forEach(data => {
                content += `<div class="profile-container row mt-3 mb-5 p-2">
                <div class="col-lg-7 mr-3 text-center">
                    <img src="data:image;base64,${data.image}" class="profile-image">
                </div>
        
                <div class="details col-lg-4">
                    <span>Profile No: <b>${data.profileId}</b></span><br>
                    <p>Tokens Left: ${data.tokens}</p>
                    <p>${data.firstName} ${data.lastName}</p>
        
                    <div class="d-flex">
                        <div>
                            <span><i class="fa-solid fa-cake-candles"></i></span>${data.birthDate}-${data.birthMonth}-${data.birthYear}<span>
                        </div>
                        <div class="mr-1 ml-1">
                            <li>${data.feet}'${data.inch}"</li>
                        </div>
                        <div>
                            <li>${data.rasiNakshitram}</span></li> 
                        </div>
                    </div>
                    <span><i class="fa-solid fa-graduation-cap"></i></span> ${data.education}<br/>
                    <span><i class="fa-solid fa-briefcase"></i></span> ${data.occupation} ${data.occupationRole}<br/>
                    <span><i class="fa-solid fa-location-dot"></i></span> Place of Job :- ${data.cityName} ${data.occupationState} ${data.otherCountryCityName} ${data.countryName}<br/>
                    <span><i class="fa-solid fa-sack-dollar"></i></span> ${data.salary} LPA<br/>
                    <span><i class="fa-solid fa-people-roof"></i> </span>Brother :-${data.numberOfBrothers},  Sisters :-${data.numberOfSisters}<br/>
                    <span><i class="fa-solid fa-user"></i></span>${data.maritialStatus}<br/>
                    <span><i class="fa-solid fa-house"></i></i></span> ${data.apDistrict} ${data.nativeState}<br/>
                    <li>Caste :- ${data.casteGeneral}- ${data.caste} ${data.subCaste}</li>
                    <div id="buttons">
                        <button id="recharge" onclick="recharge()" class="btn btn-success">Recharge</button>
                        <button id="clearTokens" onclick="clearTokens()" class="btn btn-warning">Clear Tokens</button>
                        <button id="contact" onclick="contact(${data.profileId})" class="btn btn-primary">Contact Details</button>
                        <button id="dlt" onclick="dlt()" class="btn btn-danger">Delete Profile</button>
                    </div>
                    <div id="rechargeConfirm" class="d-none text-center">
                        <button id="" onclick="confirmRecharge(${data.profileId})" class="btn btn-info">Confirm Recharge</button>
                        <button id="" onclick="cancel()" class="btn btn-secondary">Cancel</button>
                    </div>
                    <div id="clearConfirm" class="d-none text-center">
                        <button id="" onclick="confirmClear(${data.profileId})" class="btn btn-info">Confirm Clear</button>
                        <button id="" onclick="cancel()" class="btn btn-secondary">Cancel</button>
                    </div>
                    <div id="dltConfirm" class="d-none text-center">
                        <button id="" onclick="confirmDelete(${data.profileId})" class="btn btn-info">Confirm Delete</button>
                        <button id="" onclick="cancel()" class="btn btn-secondary">Cancel</button>
                    </div>
                    <p id="actionResult" class="text-success"></p>
                </div>
                <div id="contactDetails"></div>
            </div>`;
            })
                display.innerHTML = content;
            })
            .catch(err => searchError.textContent = err);
    }
    else {
        searchError.textContent = "Please Enter Valid Profile Number";
    }
}

function recharge() {
    alert("This Action will Recharge 10 Tokens to This Profile");
    buttons.classList.add("d-none");
    rechargeConfirm.classList.remove("d-none");
}
function clearTokens() {
    alert("This Action will clear All the Tokens in This Profile");
    buttons.classList.add("d-none");
    clearConfirm.classList.remove("d-none");
}
function dlt() {
    alert("This Action will DELETE this Profile Permenently");
    buttons.classList.add("d-none");
    dltConfirm.classList.remove("d-none");
}
function cancel() {
    buttons.classList.remove("d-none");
    rechargeConfirm.classList.add("d-none");
    clearConfirm.classList.add("d-none");
    dltConfirm.classList.add("d-none");
}

function confirmRecharge(id) {
    cancel();
    fetch("http://localhost:8080/admin/recharge/"+id)
        .then(res => res.text())
        .then(data => {
            if (data === "Successfully Recharged") actionResult.textContent="5 Tokens Recharged Successfully!!!";
            else actionResult.textContent = "Something Went Wrong!";
        })
        .catch(err => actionResult.textContent = "Error: Something Went Wrong");
}
function confirmClear(id) {
    cancel();
    fetch("http://localhost:8080/admin/clear/"+id)
        .then(res => res.text())
        .then(data => {
            if (data === "Profile Tokens are Recharged") actionResult.textContent="Tokens cleared to Zero (0)";
            else actionResult.textContent = "Something Went Wrong!";
        })
        .catch(err => actionResult.textContent = "Error: Something Went Wrong");
}
function confirmDelete(id) {
    cancel();
    fetch("http://localhost:8080/profile/delete/"+id)
        .then(res => res.text())
        .then(data => {
            alert("Your Profile Account Deleted Successfully");
            if (data === "Profile Account Deleted") actionResult.textContent=data;
            else actionResult.textContent = data;
        })
        .catch(err => actionResult.textContent ="Error: Something Went Wrong");
}
function contact(id) {
    alert("Contact");
    let detailsContainer = document.getElementById("contactDetails");
    fetch("http://localhost:8080/admin/contact/"+id).then(res=> res.json())
        .then(data => {
            let details = `
            <div>
                <ul>
                    <li>Mobile Number: ${data.mobileNumber}</li>
                    <li>Alternate Mobile Number(Optional): ${data.alternateMobileNumber}</li>
                    <li>Email(Optional): ${data.emailId}</li>
                    <li>Village: ${data.village}</li>
                </ul>
            </div>
            <div>
                <button class="btn btn-primary" onclick="likedList(${data.id})">Liked List</button>
                <button class="btn btn-info" onclick="likerList(${data.id})">Liker List</button>
                <button class="btn btn-success" onclick="connectedList(${data.id})">Connected List</button>
                <p id="listError"></p>
            </div>
            <div id="listDetails"></div>
            `;
            detailsContainer.innerHTML = details;
        })
        .catch(err => {
        detailsContainer.textContent = "Error: Something Went Wrong!";
        console.log(err);
        });

}




function getRequest(url,options={method:"GET"}) {
    listError.textContent="";
    fetch(url, options)
    .then(response => response.json())
    .then(profiles => {
        let count = 0;
        let profile = "";
        profiles.forEach(data => {
            count++;
        profile += ` 
        <div class="profile-container row mt-3 mb-5 p-2">
            <div class="col-lg-7 mr-3 text-center">
                <img src="data:image;base64,${data.image}" class="profile-image">
            </div>

        <div class="details col-lg-4">
            <span>Profile No: <b>${data.profileId}</b></span><br>
            <p>Tokens Left: ${data.tokens}</p>
            <p>${data.firstName} ${data.lastName}</p>


            <div class="d-flex">
                <div>
                    <span><i class="fa-solid fa-cake-candles"></i></span>${data.birthDate}-${data.birthMonth}-${data.birthYear}<span>
                </div>
                <div class="mr-1 ml-1">
                    <li>${data.feet}'${data.inch}"</li>
                </div>
                <div>
                    <li>${data.rasiNakshitram}</span></li> 
                </div>
            </div>
            <span><i class="fa-solid fa-graduation-cap"></i></span> ${data.education}<br/>
            <span><i class="fa-solid fa-briefcase"></i></span> ${data.occupation} ${data.occupationRole}<br/>
            <span><i class="fa-solid fa-location-dot"></i></span> Place of Job :- ${data.cityName} ${data.occupationState} ${data.otherCountryCityName} ${data.countryName}<br/>
            <span><i class="fa-solid fa-sack-dollar"></i></span> ${data.salary} LPA<br/>
            <span><i class="fa-solid fa-people-roof"></i> </span>Brother :-${data.numberOfBrothers},  Sisters :-${data.numberOfSisters}<br/>
            <span><i class="fa-solid fa-user"></i></span>${data.maritialStatus}<br/>
            <span><i class="fa-solid fa-house"></i></i></span> ${data.apDistrict} ${data.nativeState}<br/>
            <li>Caste :- ${data.casteGeneral}- ${data.caste} ${data.subCaste}</li>
        </div>
    </div>`;
        });
        listError.textContent = "count : " + count;
        listDetails.innerHTML=profile;
        
    })
    .catch(err=> {
        console.log(err);
        listError.textContent= "Error: Something went Wrong!";
    }); 
}



function likedList(id) {
    getRequest("http://localhost:8080/admin/likedList/"+id);
}

function likerList(id) {
    getRequest("http://localhost:8080/admin/likerList/"+id);
}
function connectedList(id) {
    getRequest("http://localhost:8080/admin/connectedList/"+id);
}



