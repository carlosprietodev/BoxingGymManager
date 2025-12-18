// SCRIPT PARA LA BOOTSTRAP TABLE DE LA VISTA DE VER CLIENTES

$(document).ready(function () {
    inicializarTablaClientes();
});

function inicializarTablaClientes() {
    $("#tabla-clientes")
        .bootstrapTable("destroy")
        .bootstrapTable({
            url:"ClientesAjax",
            method: "post",
            pagination: true,
            loadingFontSize: 0,
            search: true,
            showColumns: true,
            showRefresh: false,
            showToggle: false,
            mobileResponsive: true,
            toolbar: "#toolbar",
            locale: "es-ES",
            sidePagination: "client",
            pageSize: 10,
            pageList: [10, 25, 50, 100, "All"],
            responseHandler: function (res) {
                if (res.error) {
                    Swal.fire("Error", res.error, "error");
                    return { rows: [], total: 0 };
                }
                return res;
            },
            onLoadError: function (status, res) {
                Swal.fire(
                    "Error",
                    "No se pudieron cargar los datos de clientes",
                    "error"
                );
            },
            onLoadSuccess: function (data) {
                console.log("Datos cargados correctamente:", data);
            },
        });
}

// Funcion para las acciones (botones de editar, ver, eliminar) se usa en verClientes.jsp
function accionesFormatter(value, row, index) {
    return [

        // Botón Modificar
        '<form action="EmpleadoController" method="post" class="d-inline me-1">',
        '  <input type="hidden" name="accion" value="Editar">',
        '  <input type="hidden" name="idCliente" value="' + row.idCliente + '">',
        '  <button type="submit" class="btn btn-sm btn-warning" name="accion" value="Editar"  title="Modificar cliente">',
        '    <i class="bi bi-pencil-square"></i>',
        '  </button>',
        '</form>',

        // Botón Eliminar con data-id
        '<button class="btn btn-sm btn-danger eliminar-btn" data-id="' + row.idCliente + '" title="Eliminar cliente">',
        '  <i class="bi bi-trash"></i>',
        '</button>'
    ].join("");
}

// Codigo para el boton de eliminar
let idClienteAEliminar = null;

$(document).on("click", ".eliminar-btn", function () {
    idClienteAEliminar = $(this).data("id");
    $("#modalEliminar").modal("show");
});

// cuando pulso el boton eliminar del modal hago un post a EmpleadoController
$("#confirmarEliminarBtn").on("click", function () {
    if (idClienteAEliminar) {
        fetch("EmpleadoController", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            // pasamos como parametro un campo accion y el idCliente que lo recibiremos en EmpleadoController
            body: new URLSearchParams({
                accion: "eliminarCliente",
                idCliente: idClienteAEliminar,
            }),
        })
            .then((res) => res.text())
            .then(() => {
                $("#modalEliminar").modal("hide");
                Swal.fire("Eliminado", "El cliente ha sido eliminado con éxito.", "success");
                inicializarTablaClientes(); // ← esto recarga la tabla desde cero
            })
            .catch(() => {
                Swal.fire("Error", "No se pudo eliminar el cliente.", "error");
            });
    }
});




// Eventos para los botones de acciones
window.accionesEvents = {
    "click .ver": function (e, value, row, index) {
        fetch("EmpleadoController", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams({
                accion: "verCliente",
                idUsuario: row.idUsuario,
            }),
        })
            .then((response) => response.text())
            .then((data) => {
                // Redirigir a la vista de cliente si es necesario
                window.location.href =
                    contextoApp + "/JSP/Empleado/verClientes.jsp";
            })
            .catch((error) => {
                console.error("Error:", error);
                Swal.fire("Error", "No se pudo procesar la solicitud", "error");
            });
    },


};
