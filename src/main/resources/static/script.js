const API = 'http://localhost:8080/api';

function showTab(id) {
    document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.tabs button').forEach(b => b.classList.remove('active'));
    document.getElementById(id).classList.add('active');
    event.target.classList.add('active');
    if (id === 'clients') loadClients();
    if (id === 'cars') { loadClientsForSelect(); loadCars(); }
    if (id === 'mechanics') loadMechanics();
    if (id === 'orders') { loadCarsForOrderSelect(); loadOrders(); }
}

async function loadClients() {
    const res = await fetch(`${API}/clients`);
    const data = await res.json();
    document.querySelector('#clientsTable tbody').innerHTML = data.map(c =>
        `<tr><td>${c.id}</td><td>${c.fullName}</td><td>${c.phone}</td><td>${c.email}</td>
        <td><button class="delete-btn" onclick="deleteClient(${c.id})">Удалить</button></td></tr>`
    ).join('');
}

async function addClient(e) {
    e.preventDefault();
    await fetch(`${API}/clients`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            fullName: document.getElementById('clientName').value,
            phone: document.getElementById('clientPhone').value,
            email: document.getElementById('clientEmail').value
        })
    });
    e.target.reset();
    loadClients();
}

async function deleteClient(id) {
    await fetch(`${API}/clients/${id}`, { method: 'DELETE' });
    loadClients();
}

async function loadClientsForSelect() {
    const res = await fetch(`${API}/clients`);
    const data = await res.json();
    document.getElementById('carClientId').innerHTML = data.map(c =>
        `<option value="${c.id}">${c.fullName} (${c.phone})</option>`
    ).join('');
}

async function loadCars() {
    const res = await fetch(`${API}/cars`);
    const data = await res.json();
    document.querySelector('#carsTable tbody').innerHTML = data.map(c =>
        `<tr><td>${c.id}</td><td>${c.plateNumber}</td><td>${c.brand}</td><td>${c.model}</td>
        <td>${c.productionYear}</td><td>${c.vin}</td><td>${c.clientId}</td>
        <td><button class="delete-btn" onclick="deleteCar(${c.id})">Удалить</button></td></tr>`
    ).join('');
}

async function addCar(e) {
    e.preventDefault();
    const clientId = document.getElementById('carClientId').value;
    await fetch(`${API}/cars/client/${clientId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            plateNumber: document.getElementById('carPlate').value,
            brand: document.getElementById('carBrand').value,
            model: document.getElementById('carModel').value,
            productionYear: parseInt(document.getElementById('carYear').value),
            vin: document.getElementById('carVin').value
        })
    });
    e.target.reset();
    loadCars();
}

async function deleteCar(id) {
    await fetch(`${API}/cars/${id}`, { method: 'DELETE' });
    loadCars();
}

async function loadMechanics() {
    const res = await fetch(`${API}/mechanics`);
    const data = await res.json();
    document.querySelector('#mechanicsTable tbody').innerHTML = data.map(m =>
        `<tr><td>${m.id}</td><td>${m.fullName}</td><td>${m.specialization}</td><td>${m.phone}</td>
        <td><button class="delete-btn" onclick="deleteMechanic(${m.id})">Удалить</button></td></tr>`
    ).join('');
}

async function addMechanic(e) {
    e.preventDefault();
    await fetch(`${API}/mechanics`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            fullName: document.getElementById('mechanicName').value,
            specialization: document.getElementById('mechanicSpec').value,
            phone: document.getElementById('mechanicPhone').value
        })
    });
    e.target.reset();
    loadMechanics();
}

async function deleteMechanic(id) {
    await fetch(`${API}/mechanics/${id}`, { method: 'DELETE' });
    loadMechanics();
}

async function loadCarsForOrderSelect() {
    const res = await fetch(`${API}/cars`);
    const data = await res.json();
    document.getElementById('orderCarId').innerHTML = data.map(c =>
        `<option value="${c.id}">${c.plateNumber} - ${c.brand} ${c.model}</option>`
    ).join('');
}

async function loadOrders() {
    const res = await fetch(`${API}/orders`);
    const data = await res.json();
    const mechRes = await fetch(`${API}/mechanics`);
    const mechanics = await mechRes.json();

    document.querySelector('#ordersTable tbody').innerHTML = data.map(o => {
        const mech = mechanics.find(m => m.id === o.mechanicId);
        return `<tr>
            <td>${o.id}</td><td>${o.receptionDate}</td>
            <td>${o.carId}</td><td>—</td>
            <td>${o.faultDescription}</td>
            <td>
                <select class="status-select" onchange="updateStatus(${o.id}, this.value)">
                    <option ${o.status === 'ACCEPTED' ? 'selected' : ''}>ACCEPTED</option>
                    <option ${o.status === 'IN_PROGRESS' ? 'selected' : ''}>IN_PROGRESS</option>
                    <option ${o.status === 'WAITING_PARTS' ? 'selected' : ''}>WAITING_PARTS</option>
                    <option ${o.status === 'READY' ? 'selected' : ''}>READY</option>
                    <option ${o.status === 'ISSUED' ? 'selected' : ''}>ISSUED</option>
                </select>
            </td>
            <td>${mech ? mech.fullName : '—'} ${o.mechanicId ? `<button class="delete-btn" onclick="unassignMechanic(${o.id})">✕</button>` : ''}</td>
            <td><button class="delete-btn" onclick="deleteOrder(${o.id})">Удалить</button></td>
        </tr>`;
    }).join('');
}

async function createOrder(e) {
    e.preventDefault();
    const carId = document.getElementById('orderCarId').value;
    const fault = document.getElementById('orderFault').value;
    await fetch(`${API}/orders/car/${carId}?faultDescription=${encodeURIComponent(fault)}`, {
        method: 'POST'
    });
    e.target.reset();
    loadOrders();
}

async function updateStatus(orderId, status) {
    await fetch(`${API}/orders/${orderId}/status?status=${status}`, { method: 'PUT' });
    loadOrders();
}

async function unassignMechanic(orderId) {
    await fetch(`${API}/orders/${orderId}/status?status=ACCEPTED`, { method: 'PUT' });
    loadOrders();
}

async function deleteOrder(id) {
    await fetch(`${API}/orders/${id}`, { method: 'DELETE' });
    loadOrders();
}

loadClients();