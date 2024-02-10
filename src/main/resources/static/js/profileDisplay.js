const editProfileForm = document.getElementById("changeForm");
const profileEditingForm = document.getElementById("profileEditingForm");
const totalDisplay = document.getElementById("totalDisplay");
const showCase = document.getElementById("showCase");
const searchInput = document.getElementById("searchInput");
const searchError = document.getElementById("searchError");
const filter = document.getElementById("filter");
const filterBtn = document.getElementById("filterBtn");

const allProfilesUrl = "http://localhost:8080/profiles";
const filterProfilesUrl = "http://localhost:8080/profile/filter";
const searchUrl = "http://localhost:8080/profile/";
const connectUrl = "http://localhost:8080/profile/connect/";
const likedUrl = "http://localhost:8080/profile/likedList";
const likerUrl = "http://localhost:8080/profile/likerList";
const connectedUrl = "http://localhost:8080/profile/connectedList";
const cancelUrl = "http://localhost:8080/profile/cancel/";
const acceptUrl = "http://localhost:8080/profile/accept/";
const contactUrl = "http://localhost:8080/profile/contact/";
const myProfileUrl = "http://localhost:8080/profile/myProfile";


function connect(id) {
    let error = document.getElementById("error"+id);
    let button = document.getElementById("profile"+id);
    fetch(connectUrl+id).then(res => res.text()).then(data => {
        if (data === "Success") {
            error.textContent = "";
            button.textContent = "Request Sent";
            button.classList.remove("btn-success");
            button.classList.add("btn-info");
        }
        else if (data === "Insufficient Tokens") {
            error.textContent = data;
            button.classList.remove("btn-success");
            button.classList.add("btn-danger");
        }
        else {
            // button.textContent = data;
            button.classList.remove("btn-success");
            button.classList.add("btn-info");
            error.textContent = data;
        }
    }).catch(err => error.textContent="Error: Something Went Wrong");
}

function cancel(id) {
    let error = document.getElementById("error"+id);
    let button = document.getElementById("cancel"+id);
    fetch(cancelUrl+id).then(res => res.text()).then(data => {
        if (data === "Request Cancelled Successfully") {
            error.textContent = "";
            button.textContent = data;
            button.classList.remove("btn-danger");
            button.classList.add("btn-secondary");
        }
    })
    .catch(err => error.textContent = "Error: Something Went Wrong");
} 

function accept(id) {
    let error = document.getElementById("error"+id);
    let button = document.getElementById("accept"+id);
    fetch(acceptUrl+id).then(res => res.text()).then(data => {
        if (data === "Connection established Successfully. Please Check Contact Details in Connected List Tab") {
            error.classList.remove("text-danger");
            error.classList.add("text-success");
            error.textContent = data;
            button.classList.remove("btn-primary");
            button.classList.add("btn-success");
        }
        else if (data === "Requested Profile has Insufficient Tokens") {
            error.textContent = data;
        }
        else if (data === "Insufficient Tokens") {
            error.textContent = data;
        }
        else {
            error.textContent = data;
        }
    })
    .catch(err => error.textContent = "Error: Something Went Wrong");
} 

function contact(id) {
    let detailsContainer = document.getElementById("contact"+id);
    fetch(contactUrl+id).then(res=> res.json())
        .then(data => {
            let contactDetails = `<ul>
            <li>Mobile Number: ${data.mobileNumber}</li>
            <li>Alternate Mobile Number(Optional): ${data.alternateMobileNumber}</li>
            <li>Email(Optional): ${data.emailId}</li>
            <li>Village: ${data.village}</li>
            </ul>`;
            detailsContainer.innerHTML = contactDetails;
        })
        .catch(err => {
        detailsContainer.textContent = "Error: Something Went Wrong!";
        console.log(err);
        });

}

function liked() {
    getRequest(likedUrl);
}
function liker() {
    getRequest(likerUrl);
}
function connected() {
    getRequest(connectedUrl);
}

function myProfile() {
    fetch(myProfileUrl)
    .then(res => res.json())
    .then(data => {
        searchError.textContent="";
        profileEditingForm.classList.add("d-none");
        totalDisplay.classList.remove("d-none");
        let content = `<div id="myProfile" class="text-white row">
        <div id="text-center" class="m-3 col-lg-4">
            <img src="data:image;base64,${data.image}" class="profile-image"/>
        </div>
        <div class="ml-3 col-lg-6">
            <h1>Tokens Left: ${data.tokens}</h1>
            <h4>Name: ${data.fistName} ${data.lastName}</h4>
            <p>Profile Number: <b>${data.profileId}</b></p>
            <p>Father: ${data.fatherName}      Mother :${data.motherName}</p>
            <p>Height: ${data.feet}"${data.inch}'    DOB:${data.birth}-${data.birthMonth}-${data.birthYear}</p>
            <p>Maritial Status: ${data.maritialStatus}</p>
            <p>Brother: ${data.numberOfBrothers},  Sisters: ${data.numberOfSisters}</p>
            <p>Address: ${data.doorNo}, ${data.streetName}, ${data.village}, ${data.apDistrict} ${data.nativeState}</p>
            <p>Native: ${data.nativeLocation}</p>
            <p>Occupation: ${data.occupation} ${data.occupationRole}, ${data.countryName}, ${data.otherCountryCityName} ${data.cityName}, ${data.occupationState}</p>
            <p>Salary: ${data.salary}</p>
            <p>Property Value: ${data.propertyValue}</p>
            <div id="myProfileButtons" class="text-center">
                <button id="edit" onclick="editProfile()" class="btn btn-primary">Edit</button>
                <button id="dlt" onclick="delt()" class="btn btn-danger">Delete Account</button>
                <p></p>
            </div>
            <div id="myProfileDeleteButtons" class="d-none">
                <button id="confirmDelete" onclick="confirm(${data.profileId})" class="btn btn-warning">Confirm</button>
                <button id="cancelDelete" onclick="cancel()" class="btn btn-secondary">Cancel</button>
                <p></p>
            </div>
        </div>
    </div>`;
    showCase.innerHTML=content;
    })
    .catch(err => searchError.textContent = "Something Went Wrong!");
}

function delt() {
    alert("This action will DELETE your account PERMENENTLY");
    myProfileButtons.classList.add("d-none");
    myProfileDeleteButtons.classList.remove("d-none");
}

function cancel() {
    myProfileButtons.classList.remove("d-none");
    myProfileDeleteButtons.classList.add("d-none");
}

function confirm(id) {
    fetch("http://localhost:8080/profile/delete/"+id)
        .then(res => res.text())
        .then(data => {
            alert("Your Profile Account Deleted Successfully");
            if (data === "Profile Account Deleted") window.location.replace("http://localhost:8080/logout");
            else editError.textContent = data;
        })
        .catch(err => editError.textContent ="Error: Something Went Wrong");
}

function editProfile() {
    profileEditingForm.classList.remove("d-none");
    totalDisplay.classList.add("d-none");
}

function getRequest(url,options={method:"GET"}) {
    filter.classList.add("d-none");
    filterBtn.classList.remove("btn-danger");
    filterBtn.classList.add("btn-primary");
    profileEditingForm.classList.add("d-none");
    totalDisplay.classList.remove("d-none");
    searchError.textContent="";
    fetch(url, options)
    .then(response => response.json())
    .then(profiles => {
        let count = 0;
        let profile = "";
        profiles.forEach(data => {
            count++;
            let displayButton;
        if (url===allProfilesUrl || url === searchUrl || url === filterProfilesUrl) {
            displayButton = `<div class="text-center mt-3">
            <button id="profile${data.profileId}" onclick="connect('${data.profileId}')" class="btn btn-success">Connect</button>
            <p id="error${data.profileId}" class="text-danger"></p>
            </div>
    </div>
    
</div>`;
        }
        else if (url === likedUrl) {
            displayButton = `<div class="text-center mt-3">
            <button id="cancel${data.profileId}" onclick="cancel('${data.profileId}')" class="btn btn-danger">Cancel</button>
            <p id="error${data.profileId}" class="text-danger"></p>
            </div>
    </div>
    
</div>`;
        }
        else if (url === likerUrl) {
            displayButton = `<div class="text-center mt-3">
            <button id="accept${data.profileId}" onclick="accept('${data.profileId}')" class="btn btn-primary">Accept</button>
            <p id="error${data.profileId}" class="text-danger"></p>
            </div>
    </div>
    
</div>`;
        }
        else if (url === connectedUrl) {
            displayButton = `<div class="text-center mt-3">
            <button id="connected${data.profileId}" onclick="contact('${data.profileId}')" class="btn btn-primary">View Contact Details</button>
            <p id="error${data.profileId}" class="text-danger"></p>
            </div>
            <div id="contact${data.profileId}"></div>
    </div>
    
</div>`;
        }
        profile += ` <div class="profile-container row mt-3 mb-5 p-2">
        <div class="col-lg-7 mr-3 text-center">
            <img src="data:image;base64,${data.image}" class="profile-image">
        </div>

        <div class="details col-lg-4">
            <span>Profile No: <b>${data.profileId}</b></span><br>
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
            `+displayButton;
        });
        searchError.textContent = "count : " + count;
        showCase.innerHTML=profile;
        if (url === allProfilesUrl || url === searchUrl || url === filterProfilesUrl) searchError.textContent="";
        
    })
    .catch(err=> {
        console.log(err);
        searchError.textContent= "Error: Something went Wrong!";
    }); 
}
getRequest(allProfilesUrl);

function search() {
    let searchId = searchInput.value.trim();
    if (/^\d+$/.test(searchId)) {
        searchError.textContent="";
        searchInput.value="";
        let searchProfileUrl = searchUrl+searchId;
        getRequest(searchProfileUrl);
    }
    else {
        searchError.textContent = "Please Enter Valid Profile Number";
    }
}

searchInput.addEventListener("keydown", event => {
    if (event.key === "Enter") search();
})












filterBtn.addEventListener("click", (event) =>{
    filter.classList.toggle("d-none");
    if (filter.classList.contains("d-none")) {
        event.target.classList.remove("btn-danger");
        event.target.classList.add("btn-primary");
    }
    else {
        event.target.classList.remove("btn-primary");
        event.target.classList.add("btn-danger");
    }
})

function send() {
    const fd = new FormData(filterForm);
    options = {
        method:"POST",
        body:fd
    }
    getRequest(filterProfilesUrl, options);
}

filterForm.addEventListener("submit", (event) => {
    event.preventDefault();
    filter.classList.add("d-none");
    filterBtn.classList.remove("btn-danger");
    filterBtn.classList.add("btn-primary");
    send();
})












editProfileForm.addEventListener("submit", event => {
    event.preventDefault();
    const fd = new FormData(editProfileForm);
    let options = {
        method:"POST",
        body:fd
    }
    fetch("http://localhost:8080/profile/edit", options)
        .then(res => res.text())
        .then(data => validationErrorMessage.textContent = data)
        .catch(err => validationErrorMessage.textContent="Something Went Wrong");
})











