let token = null;

window.onload = function () {
  updateUIState();
};

function updateUIState() {
  const protectedBtns = document.querySelectorAll(".protected");
  protectedBtns.forEach((btn) =>
    btn.style.display = token ? "inline-block" : "none"
  );
}

function toggle(id) {
  document.querySelectorAll(".content-section").forEach((div) => {
    if (div.id !== id) div.style.display = "none";
  });
  const x = document.getElementById(id);
  x.style.display = x.style.display === "none" ? "block" : "none";
}

// helper to format JSON to key:value readable
function formatObject(obj) {
  if (Array.isArray(obj)) {
    return obj.map((item, i) => `#${i + 1}\n${formatObject(item)}`).join("\n\n");
  }
  return Object.entries(obj)
    .map(([k, v]) => `${k}: ${v}`)
    .join("\n");
}

// auth
async function register() {
  const username = document.getElementById("regUsername").value;
  const password = document.getElementById("regPassword").value;

  const res = await fetch("http://localhost:8080/auth/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });

  document.getElementById("response").innerText = await res.text();
}

async function login() {
  try {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    const res = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    let contentType = res.headers.get("content-type");
    let jwt;

    if (contentType?.includes("json")) {
      const data = await res.json();
      jwt = data.token || data.jwt || data;
    } else {
      jwt = (await res.text()).trim();
      if (jwt.startsWith("Bearer ")) jwt = jwt.substring(7);
    }

    token = jwt.trim();
    document.getElementById("response").innerText = "Login Successful";
    updateUIState();
  } catch (e) {
    document.getElementById("response").innerText = e.message;
  }
}

function logout() {
  token = null;
  document.querySelectorAll("input").forEach((i) => (i.value = ""));
  document.querySelectorAll(".response-box").forEach((b) => (b.innerText = ""));
  document.querySelectorAll(".content-section").forEach((el) => (el.style.display = "none"));
  updateUIState();
  alert("Logged out");
}

// create student
async function createStudent() {
  const name = document.getElementById("createStudentName").value;
  const grade = document.getElementById("createStudentGrade").value;
  const parent_number = document.getElementById("createStudentParentNumber").value;

  const res = await fetch("http://localhost:8080/students/add", {
    method: "POST",
    headers: { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" },
    body: JSON.stringify({ name, grade, parent_number }),
  });

  document.getElementById("createStudentResponse").innerText = await res.text();
}

async function getAllStudents() {
  const res = await fetch("http://localhost:8080/students/all", {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` },
  });

  const data = await res.json();
  document.getElementById("allStudentsResponse").innerText = formatObject(data);
}

// routes
async function createRoute() {
  const route_name = document.getElementById("createRouteName").value;
  const start = document.getElementById("createRouteStart").value;
  const end = document.getElementById("createRouteEnd").value;
  const status = document.getElementById("createRouteStatus").value;

  const res = await fetch("http://localhost:8080/routes/add", {
    method: "POST",
    headers: { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" },
    body: JSON.stringify({ route_name, start, end, status }),
  });

  document.getElementById("createRouteResponse").innerText = formatObject(await res.json());
}

async function updateRouteStatus() {
  const id = document.getElementById("updateRouteId").value;
  const status = document.getElementById("updateStatus").value;

  const res = await fetch(`http://localhost:8080/routes/${id}/${status}`, {
    method: "PATCH",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("updateRouteResponse").innerText = formatObject(await res.json());
}

async function getRouteStatus() {
  const id = document.getElementById("getStatusRouteId").value;

  const res = await fetch(`http://localhost:8080/routes/${id}/status`, {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("routeStatusResponse").innerText = await res.text();
}

// vehicles
async function addVehicle() {
  const vehicleNumber = document.getElementById("vehicleNumber").value;
  const capacity = document.getElementById("vehicleCapacity").value;
  const driverName = document.getElementById("vehicleDriverName").value;
  const routeId = document.getElementById("vehicleRouteId").value;

  const res = await fetch(`http://localhost:8080/vehicles/route/${routeId}`, {
    method: "POST",
    headers: { "Authorization": `Bearer ${token}`, "Content-Type": "application/json" },
    body: JSON.stringify({ vehicleNumber, capacity, driverName }),
  });

  document.getElementById("addVehicleResponse").innerText = formatObject(await res.json());
}

async function getVehiclesByRoute() {
  const routeId = document.getElementById("vehiclesRouteId").value;

  const res = await fetch(`http://localhost:8080/vehicles/route/${routeId}`, {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("vehiclesByRouteResponse").innerText = formatObject(await res.json());
}

// NEW — Get all vehicles
async function getAllVehicles() {
  const res = await fetch("http://localhost:8080/vehicles/all", {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("allVehiclesResponse").innerText = formatObject(await res.json());
}

// NEW — Get vehicle by ID
async function getVehicleById() {
  const id = document.getElementById("vehicleByIdInput").value;

  const res = await fetch(`http://localhost:8080/vehicles/vehicleId/${id}`, {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("vehicleByIdResponse").innerText = formatObject(await res.json());
}

// assignments
async function assignStudent() {
  const routeId = document.getElementById("assignRouteId").value;
  const studentId = document.getElementById("assignStudentId").value;

  const res = await fetch(`http://localhost:8080/assignments/route/${routeId}/student/${studentId}`, {
    method: "POST",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("assignStudentResponse").innerText = formatObject(await res.json());
}

async function getStudentsByRoute() {
  const routeId = document.getElementById("studentsRouteId").value;

  const res = await fetch(`http://localhost:8080/assignments/route/${routeId}`, {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` },
  });

  document.getElementById("studentsByRouteResponse").innerText = formatObject(await res.json());
}
