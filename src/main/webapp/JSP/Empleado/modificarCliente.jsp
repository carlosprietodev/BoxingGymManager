<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">

<head>
    <jsp:include page="/INC/cabecera.jsp">
        <jsp:param name="titulo" value="Modificar Cliente" />
        <jsp:param name="estilo" value="${estilo}" />
    </jsp:include>
    <!--estilos y validaciones propias de la vista de modificar-->
    <link rel="stylesheet" type="text/css" href="${contexto}/CSS/estilo_empleado.css" />
    <script type="module" src="JavaScript/Validaciones/validarModificar.js"></script>

</head>
<body>

<jsp:include page="/INC/headerEmpleado.jsp"/>

<div class="d-flex">
    <jsp:include page="/INC/menuEmpleado.jsp"/>

    <!-- Main Content -->
    <main id="content" class="flex-grow-1">
        <div class="container">
            <div>
                <h3 class="mb-4 text-center text-danger" style="color: var(--color-text-primary)">Modificar Cliente</h3>

            </div>

            <div class="row justify-content-center">
                <div class="col-12 col-md-10 col-lg-8">
                    <form method="post" action="${contexto}/EmpleadoController" id="form_cliente" class="form_crear p-4 rounded shadow-lg bg-white">
                        <div class="row g-3">
                            <!-- Nombre -->
                            <div class="col-12 col-md-6">
                                <label for="nombre_cliente" class="form-label label_crear">Nombre:</label>
                                <div class="input-group input-group-focus">
                                    <span class="input-group-text"><i class="bi bi-person"></i></span>
                                    <input type="text" class="form-control" id="nombre_cliente" name="nombre" value="${sessionScope.cliente.nombre}">
                                </div>
                                <div id="mensaje_nombre" class="form-text"></div>
                            </div>

                            <!-- Apellidos -->
                            <div class="col-12 col-md-6">
                                <label for="apellidos_cliente" class="form-label laber_crear">Apellidos:</label>
                                <div class="input-group input-group-focus">
                                    <span class="input-group-text"><i class="bi bi-person-lines-fill"></i></span>
                                    <input type="text" class="form-control" id="apellidos_cliente" name="apellidos" value="${sessionScope.cliente.apellidos}">
                                </div>
                                <div id="mensaje_apellidos" class="form-text"></div>
                            </div>

                            <!-- Teléfono -->
                            <div class="col-12 col-md-6">
                                <label for="telefono_cliente" class="form-label label_crear">Teléfono:</label>
                                <div class="input-group input-group-focus">
                                    <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                                    <input type="text" class="form-control" id="telefono_cliente" name="telefono" value="${sessionScope.cliente.telefono}">
                                </div>
                                <div id="mensaje_telefono" class="form-text"></div>
                            </div>

                            <!-- Email -->
                            <div class="col-12 col-md-6">
                                <label for="email_cliente" class="form-label label_crear">Email:</label>
                                <div class="input-group input-group-focus">
                                    <span class="input-group-text"><i class="bi bi-envelope-at"></i></span>
                                    <input type="email" class="form-control" id="email_cliente" name="email" value="${sessionScope.cliente.email}">
                                </div>
                                <div id="mensaje_email" class="form-text"></div>
                            </div>

                            <!-- Localidad -->
                            <div class="col-12 col-md-6">
                                <label for="localidad_cliente" class="form-label label_crear">Localidad:</label>
                                <div class="input-group input-group-focus">
                                    <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
                                    <input type="text" class="form-control" id="localidad_cliente" name="localidad"  value="${sessionScope.cliente.localidad}">
                                </div>
                                <div id="mensaje_localidad" class="form-text"></div>
                            </div>

                            <!-- Entrenamiento -->
                            <div class="col-12">
                                <label for="sel_entrenamiento_cliente" class="form-label label_crear">Entrenamiento:</label>
                                <div class="input-group input-group-focus">
                                    <span class="input-group-text"><i class="bi bi-barbell"></i></span>
                                    <select class="form-select" id="sel_entrenamiento_cliente" name="entrenamiento">
                                        <option value="" disabled selected>Selecciona un entrenamiento:</option>

                                        <c:forEach var="entreno" items="${requestScope.listaEntrenamientos}">
                                            <c:choose>
                                                <c:when test="${sessionScope.cliente.entrenamiento.idEntrenamiento == entreno.idEntrenamiento}">
                                                    <option value="${entreno.idEntrenamiento}" selected>${entreno.tipo}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${entreno.idEntrenamiento}">${entreno.tipo}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <!-- Botón -->
                            <div class="col-12 text-center mt-4">
                                <button type="submit" class="btn btn_rojo w-25" disabled id="btn_modificar" name="accion" value="Modificar">
                                    <i class="bi bi-plus-circle me-2"></i> Modificar Cliente
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

</div>
<!-- Alerta oculta  -->
<div id="alerta" class="alert alert-warning w-50 text-center position-fixed top-0 start-50 translate-middle-x"
     style="display: none; margin-top: 50px; width: 50px" role="alert">


</div>

</body>
</html>
