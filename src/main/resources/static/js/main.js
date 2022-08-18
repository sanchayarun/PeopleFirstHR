"use strict";


// Divs
let resultsDiv = document.querySelector("#resultsDiv");


// Inputs
let firstNameInput = document.querySelector("#firstNameInput");
let lastNameInput = document.querySelector("#lastNameInput");
let emailInput = document.querySelector("#emailInput");
let mobileInput = document.querySelector("#mobileInput")
let idInput = document.querySelector("#idInput")

//Buttons
let createBtn = document.querySelector("#createButton");
let updateBtn = document.querySelector("#updateButton");
let deleteBtn = document.querySelector("#deleteButton");
let clearBtn = document.querySelector("#clearButton");

// Functions

let showResults = (result) => {

    let printParent = document.createElement("div");
    printParent.setAttribute("class", "printParent");

    let printDiv = document.createElement("div");
    printDiv.setAttribute("class", "printDiv");
    printDiv.textContent = `${result.id} | ${result.firstName} | ${result.lastName} | ${result.email} | ${result.mobile}`;


    let remBtn = document.createElement("button");
    remBtn.textContent = "Delete";
    remBtn.type = "button";
    remBtn.setAttribute("Class", "btn btn-danger btn-sm");
    remBtn.setAttribute("onClick", `remove(${result.id})`)

    printParent.appendChild(printDiv);
    printParent.appendChild(remBtn);
    resultsDiv.appendChild(printParent);
}




let getAll = () => {
    axios.get("http://localhost:8080/employee/getAll")
    .then((response) => {                    
        resultsDiv.innerHTML = "";         
       let results = response.data;

       for (let result of results) {                    
            showResults(result);                  
       }

    }).catch((error) => {console.log(error); });     
}


let create = () => {

    if(validateCreate() === false) {
        return;
    }


    let newEmployee = {
        "firstName": firstNameInput.value,
        "lastName": lastNameInput.value,
        "email": emailInput.value,
        "mobile": mobileInput.value
    }


    axios.post("http://localhost:8080/employee/create", newEmployee)  
    .then( (response) => {
        getAll();
    }).catch(error => {console.log(error)});
}

let update = () => {

    if(validateUpdate() === false) {
        return;
    }


    let updateEmployee = {
        "firstName": firstNameInput.value,
        "lastName": lastNameInput.value,
        "email": emailInput.value,
        "mobile": mobileInput.value
    }


    axios.put(`http://localhost:8080/employee/update/${idInput.value}`, updateEmployee)   
    .then( (response) => {
        getAll();
    }).catch(error => {console.log(error)});
}


let remove = (id) => {
    axios.delete(`http://localhost:8080/employee/delete/${id}`)  
    .then( (response) => {
        getAll();
    }).catch(error => {console.log(error)});
}

let removeTwo = () => {
    if(validateRemove() === false) {
        return;
    }

    axios.delete(`http://localhost:8080/employee/delete/${idInput.value}`)  
    .then( (response) => {
        getAll();
    }).catch(error => {console.log(error)});
}

let clear = () => {
    document.getElementById("firstNameInput").value="";
    document.getElementById("lastNameInput").value="";
    document.getElementById("emailInput").value="";
    document.getElementById("mobileInput").value="";
    document.getElementById("idInput").value="";
}






// Validate functions
// ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈


let validateCreate = () => {
    if (firstNameInput.value === "") {
        alert("Check First Name, Last Name, Email and Mobile fields have been entered");
        return false;
    } else if (lastNameInput.value === "") {
        alert("Check First Name, Last Name, Email and Mobile fields have been entered");
        return false;
    } else if (emailInput.value === "") {
        alert("Check First Name, Last Name, Email and Mobile fields have been entered");
        return false;
    } else {
        return true;
    }
}


let validateUpdate = () => {
    if (firstNameInput.value === "") {
        alert("Check First Name, Last Name, Email, Mobile and ID fields have been entered");
        return false;
    } else if (lastNameInput.value === "") {
        alert("Check First Name, Last Name, Email, Mobile and ID fields have been entered");
        return false;
    } else if (emailInput.value === "") {
        alert("Check First Name, Last Name, Email, Mobile and ID fields have been entered");
        return false;
    } else if (idInput.value === "") {
        alert("Check First Name, Last Name, Email, Mobile and ID fields have been entered");
        return false;
    } else {
        return true;
    }
}



let validateRemove = () => {
    if (idInput.value === "") {
        alert("WARNING: ID Field Empty");
        return false;
    } else {
        return true;
    }
}




// Listener
// ≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈≈
createBtn.addEventListener("click", create);
updateBtn.addEventListener("click", update);
deleteBtn.addEventListener("click", removeTwo);
clearBtn.addEventListener("click", clear);