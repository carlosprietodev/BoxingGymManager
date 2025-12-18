
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
    <jsp:include page="/INC/cabecera.jsp">
        <jsp:param name="titulo" value="Estadisticas" />
        <jsp:param name="estilo" value="${estilo}" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="${contexto}/CSS/estilo_empleado.css" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script type="module" src="${contexto}/JavaScript/Estadisticas/cargarEstadisticas.js"></script>


</head>
<body>

<jsp:include page="/INC/headerEmpleado.jsp"/>

<div class="d-flex">

    <jsp:include page="/INC/menuEmpleado.jsp"/>

    <!-- Main Content -->
    <main id="content" class="flex-grow-1">
        <div class="d-flex justify-content-center gap-5">
            <h2 class="text-center text-danger">Estad&iacute;sticas</h2>

        </div>

        <div class="div_graficos">
            <canvas id="graficoPieEntrenamiento" width="500" height="500"></canvas>
            <canvas id="graficoLocalidad" width="500" height="500"></canvas>
        </div>


    </main>

</div>


</body>

</html>
