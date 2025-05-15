<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Administración de Empleados</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<div class="header">
  <h1>Employees</h1>
  <button class="btn btn-primary" id="createJobBtn">Crear trabajo</button>
</div>

<div class="tabs">
  <div class="tab active" data-tab="employees">Empleados</div>
  <div class="tab" data-tab="titles">Títulos</div>
  <div class="tab" data-tab="salaries">Salarios</div>
</div>

<div class="tab-content active" id="employees-tab">
  <div class="search-container">
    <input type="text" id="searchJob" placeholder="Buscar trabajo">
  </div>

  <table id="employeesTable">
    <thead>
    <tr>
      <th>ID</th>
      <th>Fecha Nacimiento</th>
      <th>Nombre</th>
      <th>Apellido</th>
      <th>Género</th>
      <th>Fecha Contratación</th>
      <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
      <tr>
        <td>${employee.empNo}</td>
        <td>${employee.birthDate}</td>
        <td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td>${employee.gender}</td>
        <td>${employee.hireDate}</td>
        <td class="action-buttons">
          <button class="btn btn-success">Editar</button>
          <button class="btn btn-danger">Borrar</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div class="tab-content" id="titles-tab">
  <h2>Contenido de Títulos</h2>
  <p>Aquí iría el contenido de la pestaña de Títulos</p>
</div>

<div class="tab-content" id="salaries-tab">
  <h2>Contenido de Salarios</h2>
  <p>Aquí iría el contenido de la pestaña de Salarios</p>
</div>

<script>
  // Manejar el cambio de pestañas
  document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
      // Remover clase active de todas las pestañas y contenidos
      document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
      document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));

      // Agregar clase active a la pestaña y contenido seleccionados
      tab.classList.add('active');
      document.getElementById(`${tab.dataset.tab}-tab`).classList.add('active');
    });
  });

  // Evento para el botón "Crear trabajo"
  document.getElementById('createJobBtn').addEventListener('click', () => {
    alert('Funcionalidad para crear nuevo trabajo');
  });

  // Evento para el buscador
  document.getElementById('searchJob').addEventListener('input', (e) => {
    const searchTerm = e.target.value.toLowerCase();
    const rows = document.querySelectorAll('#employeesTable tbody tr');

    rows.forEach(row => {
      const text = row.textContent.toLowerCase();
      row.style.display = text.includes(searchTerm) ? '' : 'none';
    });
  });
</script>
</body>
</html>