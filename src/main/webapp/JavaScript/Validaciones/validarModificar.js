// SCRIPT DE VALIDACION PARA LA VISTA DE MODIFICAR CLIENTE

let emailValido = false;
const valoresOriginales = {};


document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("form_cliente");
    const inputs = Array.from(form.querySelectorAll("input"));
    const select = document.getElementById("sel_entrenamiento_cliente");
    const btnModificar = document.getElementById("btn_modificar");

    // Guardar valores originales usando id
    inputs.forEach(input => {
        valoresOriginales[input.id] = input.value.trim();
    });
    valoresOriginales[select.id] = select.value;

    function haCambiadoCampo(input) {
        return input.value.trim() !== valoresOriginales[input.id];
    }

    function haCambiadoSelect() {
        return select.value !== valoresOriginales[select.id];
    }

    function checkElementos() {
        // Filtrar solo los campos que han sido modificados
        const inputsModificados = inputs.filter(haCambiadoCampo);
        const selectModificado = haCambiadoSelect();

        // Comprobar si hay al menos un cambio
        const algunCambio = inputsModificados.length > 0 || selectModificado;

        // Validar solo los campos modificados
        const todosValidos = inputsModificados.every(input => input.classList.contains("valido"));

        const emailInput = document.getElementById("email_cliente");
        const emailModificado = haCambiadoCampo(emailInput);
        const emailEsCorrecto = emailModificado ? emailValido : true;

        btnModificar.disabled = !(algunCambio && todosValidos && emailEsCorrecto);
    }


function mostrarMensaje(campo, mensaje) {
    const mensajeDiv = document.getElementById("mensaje_" + campo.id.split('_')[0]);
    mensajeDiv.textContent = mensaje;
    mensajeDiv.style.color = "gray"; // Color neutro al principio
}

function actualizarMensaje(campo, valido) {
    const mensajeDiv = document.getElementById("mensaje_" + campo.id.split('_')[0]);
    if (valido) {
        mensajeDiv.style.color = "green";
    } else {
        mensajeDiv.style.color = "red";
    }

}

function validarNombre(nombre) {
    // Letras minúsculas y mayúsculas, incluyendo vocales acentuadas y la letra "ñ"
    // Espacios en blanco, Un mínimo de 3 caracteres y un máximo de 20
    let regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{3,20}$/;
    let valido = regex.test(nombre.value)
    if (valido) {
        nombre.classList.add("valido");
        nombre.classList.remove("no_valido");
    } else {
        nombre.classList.add("no_valido");
        nombre.classList.remove("valido");
    }

}

function validarApellidos(apellidos) {
    // Letras minúsculas y mayúsculas del alfabeto español, incluyendo vocales acentuadas y la letra "ñ"
    // Espacios en blanco para apellidos compuestos, guiones para apellidos como García-López, Un mínimo de 3 caracteres y un máximo de 30
    let regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s-]{3,30}$/;
    let valido = regex.test(apellidos.value)
    if (valido) {
        apellidos.classList.add("valido");
        apellidos.classList.remove("no_valido");
    } else {
        apellidos.classList.add("no_valido");
        apellidos.classList.remove("valido");
    }

}

function validarTelefono(telefono) {
    let regex = /^[6789]\d{8}$/;
    let valido = regex.test(telefono.value)
    if (valido) {
        telefono.classList.add("valido");
        telefono.classList.remove("no_valido");
    } else {
        telefono.classList.add("no_valido");
        telefono.classList.remove("valido");
    }

}

function validarLocalidad(input) {
    //   Permite letras mayúsculas y minúsculas, incluyendo acentos y la letra "ñ".
    // Permite espacios para nombres de localidades compuestos.
    // Requiere un mínimo de dos caracteres y no permite números ni caracteres especiales.

    let regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,30}$/;
    let valido = regex.test(input.value)
    if (valido) {
        input.classList.add("valido");
        input.classList.remove("no_valido");
    } else {
        input.classList.add("no_valido");
        input.classList.remove("valido");
    }

}

async function validarEmail(input_email) {
    let regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/;
    let valido = regex.test(input_email.value);

    // si cumple la regex llamo a comprobarEmail
    if(valido){
        input_email.classList.add("valido");
        input_email.classList.remove("no_valido");
        await comprobarEmail();

    }else{
        input_email.classList.add("no_valido");
        input_email.classList.remove("valido");
        emailValido = false;
    }

    checkElementos();
}



const url = 'AjaxController';

// Función asincrónica para validar el email, la cual hace una peticion al servlet Ajax de Java
async function comprobarEmail() {
    const input_email = document.getElementById('email_cliente');
    const data = new URLSearchParams();
    data.append("accion", "verEmail");
    const valorEmail = input_email.value.trim(); // Guardamos el valor sin espacios

    const alerta = document.getElementById("alerta");
    // Solo hacemos el fetch por ajax si el email no está vacío
    if (valorEmail !== "") {
        data.append("email", valorEmail);

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Accept-Charset': 'utf-8'
                },
                body: data.toString()
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const respuestaJson = await response.json();
            console.log("Respuesta del servidor:", respuestaJson);



            // Validar si el email está disponible
            if (respuestaJson.disponible === "no") {
                alerta.style.display = 'block';
                alerta.innerHTML = "";
                alerta.innerHTML = "Ya existe un cliente con ese Email.";
                input_email.classList.add("no_valido");
                emailValido = false; // Email no válido
                setTimeout(() => {
                    alerta.style.display = 'none';
                }, 5000);
            } else {
                input_email.classList.remove("no_valido");
                input_email.classList.add("valido");
                alerta.style.display = 'none';
                emailValido = true; // El email es válido
            }

        } catch (error) {
            console.error("Error en la validación del email:", error);
        }
    } else {
        // Si el email está vacío, lo ponemos a false
        emailValido = false;
        input_email.classList.add("no_valido");
        alerta.style.display = 'block';
        alerta.innerHTML = "";
        alerta.innerHTML = "El Email no puede estar vacío.";
        setTimeout(() => {
            alerta.style.display = 'none';
        }, 5000);
    }


}


// EVENTOS PARA CADA UNO DE LOS INPUT

    document.getElementById("nombre_cliente").addEventListener("focus", (e) => {
        mostrarMensaje(e.target, "El nombre debe contener entre 3-20 caracteres, solo letras y puede incluir espacios.");
    });
    document.getElementById("nombre_cliente").addEventListener("input", (e) => {
        validarNombre(e.target);
        checkElementos();
    });

    document.getElementById("apellidos_cliente").addEventListener("focus", (e) => {
        mostrarMensaje(e.target, "Los apellidos deben tener entre 3-30 caracteres y solo letras.");
    });
    document.getElementById("apellidos_cliente").addEventListener("input", (e) => {
        validarApellidos(e.target);
        checkElementos();
    });

    document.getElementById("email_cliente").addEventListener("focus", (e) => {
        mostrarMensaje(e.target, "Debe tener un formato válido con @ y dominio.");
    });
    document.getElementById("email_cliente").addEventListener("input", async (e) => {
        await validarEmail(e.target);
        checkElementos();
    });

    document.getElementById("telefono_cliente").addEventListener("focus", (e) => {
        mostrarMensaje(e.target, "Debe comenzar por 6-9 y tener 9 dígitos.");
    });
    document.getElementById("telefono_cliente").addEventListener("input", (e) => {
        validarTelefono(e.target);
        checkElementos();
    });

    document.getElementById("localidad_cliente").addEventListener("focus", (e) => {
        mostrarMensaje(e.target, "Solo letras, mínimo 2 caracteres y máximo 30.");
    });
    document.getElementById("localidad_cliente").addEventListener("input", (e) => {
        validarLocalidad(e.target);
        checkElementos();
    });

    select.addEventListener("change", () => {
        checkElementos();
    });


});