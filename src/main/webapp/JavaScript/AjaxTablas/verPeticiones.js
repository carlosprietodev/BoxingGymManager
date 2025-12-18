// SCRIPT PARA CARGAR LAS PETICIONES EN LA BOOTSTRAP TABLE EN LA VISTA DE VER PETICIONES

$(document).ready(function () {
    inicializarTablaPeticiones();
});

function inicializarTablaPeticiones() {
    $("#tabla-clientes")
        .bootstrapTable("destroy")
        .bootstrapTable({
            url:"PeticionesAjax",
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
                    "No se pudieron cargar las peticiones",
                    "error"
                );
            },
            onLoadSuccess: function (data) {
                console.log("Datos cargados correctamente:", data);
            },
        });
}

// Formatter para las acciones (botones de aceptar, rechazar) se usa en empleado.jsp
function accionesFormatter(value, row, index) {
    return [

        // Botón Aceptar Peticion
        '<button class="btn btn-sm btn-success aceptar-peticion" data-id="' + row.idPeticion + '" title="Aceptar Petición">',
        '  <i class="bi bi-check-square-fill"></i>',
        '</button>',

        // Boton Rechazar Peticion
        '<button class="btn btn-sm btn-danger rechazar-peticion" data-id="' + row.idPeticion + '" title="Rechazar Petición">',
        '  <i class="bi bi-x-circle"></i>',
        '</button>'


    ].join("");
}


// Eventos para los botones de aceptar y rechazar peticion
window.accionesEvents = {
    // si pulsamos en aceptar peticion abrimos un sweet alert
    'click .aceptar-peticion': function (e, value, row, index) {
        Swal.fire({
            title: '¿Aceptar petición?',
            text: '¿Estás seguro de que quieres aceptar esta petición?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Sí, aceptar',
            cancelButtonText: 'Cancelar',

        }).then((result) => {
 // si se confirma hacemos un post a PeticionController pasando el param accion y el idPeticion
            if (result.isConfirmed) {
                fetch('PeticionController', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        accion: 'AceptarPeticion',
                        idPeticion: row.idPeticion
                    })
                })
                    .then((res) => res.text())
                    .then(() => {
                        Swal.fire('Aceptada', 'La petición ha sido aceptada', 'success');
                        $('#tabla-clientes').bootstrapTable('refresh'); // refrescamos la tabla para no tener que recargar  la pagina
                    })
                    .catch((error) => {
                        console.error(error);
                        Swal.fire('Error', 'No se pudo aceptar la petición', 'error');
                    });
            }
        });
    },
    // si pulsamos en rechazar peticion abrimos un sweet alert
    'click .rechazar-peticion': function (e, value, row, index) {
        Swal.fire({
            title: '¿Rechazar petición?',
            text: '¿Estás seguro de que quieres rechazar esta petición?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Sí, rechazar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {

// si se confirma hacemos un post a PeticionController pasando el param accion y el idPeticion
            if (result.isConfirmed) {
                fetch('PeticionController', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        accion: 'RechazarPeticion',
                        idPeticionRechazar: row.idPeticion
                    })
                })
                    .then((res) => res.text())
                    .then(() => {
                        Swal.fire('Rechazada', 'La petición ha sido rechazada', 'success');
                        $('#tabla-clientes').bootstrapTable('refresh'); // refrescamos la tabla para no tener que recargar  la pagina
                    })
                    .catch((error) => {
                        console.error(error);
                        Swal.fire('Error', 'No se pudo rechazar la petición', 'error');
                    });
            }
        });
    }
};






