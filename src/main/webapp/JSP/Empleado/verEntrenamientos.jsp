<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="es">

<head>
    <jsp:include page="/INC/cabecera.jsp">
        <jsp:param name="titulo" value="Listado Entrenamientos" />
        <jsp:param name="estilo" value="${estilo}" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="${contexto}/CSS/estilo_empleado.css" />

</head>
<body>

<jsp:include page="/INC/headerEmpleado.jsp"/>

<div class="d-flex">

    <jsp:include page="/INC/menuEmpleado.jsp"/>

    <!-- Main Content -->
    <main id="content" class="flex-grow-1">
        <div class="container">
            <h2 class="mb-5 text-center text-danger">Entrenamientos y Clientes</h2>

            <div class="accordion shadow-sm rounded-4" id="acordeonEntrenamientos">
                <c:forEach var="entrenamiento" items="${requestScope.listaEntrenamientos}" varStatus="status">
                    <div class="accordion-item mb-3 border-0 shadow-sm rounded-3">
                        <h2 class="accordion-header" id="heading${status.index}">
                            <button class="accordion-button collapsed bg-white rounded-3 fw-semibold text-dark"
                                    type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse${status.index}" aria-expanded="false"
                                    aria-controls="collapse${status.index}">
                                <span class="me-2 badge bg-danger text-white text-uppercase tipo">${entrenamiento.tipo}</span>
                                <span class="ms-2 descripcion ">${entrenamiento.descripcion}</span>
                            </button>
                        </h2>
                        <div id="collapse${status.index}" class="accordion-collapse collapse"
                             aria-labelledby="heading${status.index}" data-bs-parent="#acordeonEntrenamientos">
                            <div class="accordion-body bg-white rounded-bottom">
                                <c:choose>
                                    <c:when test="${not empty entrenamiento.clientes}">
                                        <ul class="list-group list-group-flush">
                                            <c:forEach var="cliente" items="${entrenamiento.clientes}">
                                                <li class="list-group-item d-flex flex-column flex-md-row justify-content-between align-items-start bg-light border-0 border-bottom">
                                                    <div>
                                                        <h6 class="fw-bold mb-1 text-dark">${cliente.nombre} ${cliente.apellidos}</h6>
                                                        <small class="text-muted">
                                                            <i class="bi bi-envelope"></i> ${cliente.email} |
                                                            <i class="bi bi-telephone"></i> ${cliente.telefono} |
                                                            <i class="bi bi-geo-alt"></i> ${cliente.localidad} |
                                                            <i class="bi bi-calendar"></i>
                                                            <fmt:formatDate pattern="dd-MM-yyyy" value="${cliente.fechaRegistro}" />
                                                        </small>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-muted fst-italic">No hay clientes asignados a este entrenamiento.</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>

</div>

</body>

</html>
