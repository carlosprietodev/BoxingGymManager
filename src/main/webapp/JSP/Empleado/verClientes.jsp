<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
    <jsp:include page="/INC/cabecera.jsp">
        <jsp:param name="titulo" value="Listado Clientes" />
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

    <h2 class="text-center text-danger">Listado de clientes</h2>
    <div class="card shadow-sm">
        <div class="card-body">
            <div class="table-responsive" style="min-height: 400px;">
                <table id="tabla-clientes"
                       class="table table-striped"
                       data-toggle="table"
                       data-pagination="true"
                       data-search="true"
                       data-show-columns="true"
                       data-show-refresh="true"
                       data-show-toggle="true"
                       data-toolbar="#toolbar"
                       data-locale="es-ES">
                    <thead>
                    <tr>
                        <th data-field="idCliente" data-sortable="true" data-visible="false">ID</th>
                        <th data-field="nombre" data-sortable="true">Nombre</th>
                        <th data-field="apellidos" data-sortable="true">Apellidos</th>
                        <th data-field="dni" data-sortable="true">DNI</th>
                        <th data-field="email" data-sortable="true">Email</th>
                        <th data-field="telefono" data-sortable="true">Telefono</th>
                        <th data-field="localidad" data-sortable="true">Localidad</th>
                        <th data-field="entrenamiento" data-sortable="true">Entrenamiento</th>

                        <th data-field="acciones" data-formatter="accionesFormatter" data-events="accionesEvents">
                            Acciones
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    </main>

</div>

<!-- Modal de Confirmación -->
<div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalEliminarLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="modalEliminarLabel">Confirmar Eliminación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas eliminar este cliente?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-danger" id="confirmarEliminarBtn">Eliminar</button>
            </div>
        </div>
    </div>
</div>


<style>
    .bootstrap-table .fixed-table-pagination {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>


<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Bootstrap Table -->
<script src="https://unpkg.com/bootstrap-table@1.22.1/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.22.1/dist/locale/bootstrap-table-es-ES.min.js"></script>

<!-- SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script src="${contexto}/JavaScript/AjaxTablas/verClientes.js"></script>
</body>
</html>

