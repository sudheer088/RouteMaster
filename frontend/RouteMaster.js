let token = null;

window.onload = function () {
  // Note: localStorage is not available in Claude artifacts
  // Token will only persist during the current session
  updateUIState();
};

function updateUIState() {
  const protectedBtns = document.querySelectorAll(".protected");

  if (token) {
    protectedBtns.forEach((btn) => (btn.style.display = "inline-block"));
  } else {
    protectedBtns.forEach((btn) => (btn.style.display = "none"));
  }
}

function toggle(id) {
  const x = document.getElementById(id);
  document.querySelectorAll(".content-section").forEach((div) => {
    if (div.id !== id) div.style.display = "none";
  });
  x.style.display = x.style.display === "none" ? "block" : "none";
}

async function register() {
  try {
    const username = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;
    const response = await fetch("http://localhost:8080/auth/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });
    const data = await response.text();
    document.getElementById("response").innerText = data;
  } catch (error) {
    document.getElementById("response").innerText = error.message;
  }
}

async function login() {
  try {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    if (!response.ok) throw new Error("Login failed");

    const data = await response.text();
    token = data;

    document.getElementById("response").innerText = "Login Successful";
    document.getElementById("loginSection").style.display = "none";

    document.getElementById("loginUsername").value = "";
    document.getElementById("loginPassword").value = "";

    updateUIState();
  } catch (error) {
    document.getElementById("response").innerText = error.message;
  }
}

function logout() {
  token = null;

  document.querySelectorAll("input").forEach((input) => (input.value = ""));

  const responseIds = [
    "response",
    "createStudentResponse",
    "allStudentsResponse",
    "createRouteResponse",
    "updateRouteResponse",
    "routeStatusResponse",
    "addVehicleResponse",
    "vehiclesByRouteResponse",
    "assignStudentResponse",
    "studentsByRouteResponse",
  ];
  responseIds.forEach((id) => {
    const el = document.getElementById(id);
    if (el) el.innerText = "";
  });

  document
    .querySelectorAll(".content-section")
    .forEach((el) => (el.style.display = "none"));

  updateUIState();

  alert("Logged out");
}

async function createStudent() {
  try {
    if (!token) throw new Error("Not logged in");

    const name = document.getElementById("createStudentName").value;
    const grade = document.getElementById("createStudentGrade").value;
    const parent_number = document.getElementById("createStudentParentNumber").value;

    const response = await fetch("http://localhost:8080/students/add", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, grade, parent_number }),
    });

    const data = await response.text();
    document.getElementById("createStudentResponse").innerText = data;
  } catch (error) {
    document.getElementById("createStudentResponse").innerText = error.message;
  }
}

async function getAllStudents() {
  try {
    if (!token) throw new Error("Not logged in");

    const response = await fetch("http://localhost:8080/students/all", {
      method: "GET",
      headers: { Authorization: "Bearer " + token },
    });
    const data = await response.json();
    document.getElementById("allStudentsResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("allStudentsResponse").innerText = error.message;
  }
}

async function createRoute() {
  try {
    if (!token) throw new Error("Not logged in");

    const route_name = document.getElementById("createRouteName").value;
    const start = document.getElementById("createRouteStart").value;
    const end = document.getElementById("createRouteEnd").value;
    const status = document.getElementById("createRouteStatus").value;

    const response = await fetch("http://localhost:8080/routes/add", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ route_name, start, end, status }),
    });

    const data = await response.json();
    document.getElementById("createRouteResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("createRouteResponse").innerText = error.message;
  }
}

async function updateRouteStatus() {
  try {
    if (!token) throw new Error("Not logged in");

    const routeId = document.getElementById("updateRouteId").value;
    const status = document.getElementById("updateStatus").value;

    const response = await fetch(`http://localhost:8080/routes/${routeId}/${status}`, {
      method: "PATCH",
      headers: { Authorization: "Bearer " + token },
    });

    const data = await response.json();
    document.getElementById("updateRouteResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("updateRouteResponse").innerText = error.message;
  }
}

async function getRouteStatus() {
  try {
    if (!token) throw new Error("Not logged in");

    const routeId = document.getElementById("getStatusRouteId").value;

    const response = await fetch(`http://localhost:8080/routes/${routeId}/status`, {
      method: "GET",
      headers: { Authorization: "Bearer " + token },
    });

    const data = await response.text();
    document.getElementById("routeStatusResponse").innerText = data;
  } catch (error) {
    document.getElementById("routeStatusResponse").innerText = error.message;
  }
}

async function addVehicle() {
  try {
    if (!token) throw new Error("Not logged in");

    const vehicleNumber = document.getElementById("vehicleNumber").value;
    const capacity = parseInt(document.getElementById("vehicleCapacity").value);
    const driverName = document.getElementById("vehicleDriverName").value;
    const routeId = document.getElementById("vehicleRouteId").value;

    const response = await fetch(`http://localhost:8080/vehicles/route/${routeId}`, {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ vehicleNumber, capacity, driverName }),
    });

    const data = await response.json();
    document.getElementById("addVehicleResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("addVehicleResponse").innerText = error.message;
  }
}

async function getVehiclesByRoute() {
  try {
    if (!token) throw new Error("Not logged in");

    const routeId = document.getElementById("vehiclesRouteId").value;

    const response = await fetch(`http://localhost:8080/vehicles/route/${routeId}`, {
      method: "GET",
      headers: { Authorization: "Bearer " + token },
    });

    const data = await response.json();
    document.getElementById("vehiclesByRouteResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("vehiclesByRouteResponse").innerText = error.message;
  }
}

async function assignStudent() {
  try {
    if (!token) throw new Error("Not logged in");

    const routeId = document.getElementById("assignRouteId").value;
    const studentId = document.getElementById("assignStudentId").value;

    const response = await fetch(`http://localhost:8080/assignments/route/${routeId}/student/${studentId}`, {
      method: "POST",
      headers: { Authorization: "Bearer " + token },
    });

    const data = await response.json();
    document.getElementById("assignStudentResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("assignStudentResponse").innerText = error.message;
  }
}

async function getStudentsByRoute() {
  try {
    if (!token) throw new Error("Not logged in");

    const routeId = document.getElementById("studentsRouteId").value;

    const response = await fetch(`http://localhost:8080/assignments/route/${routeId}`, {
      method: "GET",
      headers: { Authorization: "Bearer " + token },
    });

    const data = await response.json();
    document.getElementById("studentsByRouteResponse").innerText = JSON.stringify(data, null, 2);
  } catch (error) {
    document.getElementById("studentsByRouteResponse").innerText = error.message;
  }
}