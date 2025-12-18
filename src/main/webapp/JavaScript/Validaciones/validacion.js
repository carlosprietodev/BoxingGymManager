function mostrarMensaje(campo, mensaje) {
    const mensajeDiv = document.getElementById("mensaje_" + campo.id.split('_')[1]);
    mensajeDiv.textContent = mensaje;
    mensajeDiv.style.color = "gray"; // Color neutro al principio
}

function actualizarMensaje(campo, valido) {
    const mensajeDiv = document.getElementById("mensaje_" + campo.id.split('_')[1]);
    if (valido) {
        mensajeDiv.style.color = "#09df09";
    } else {
        mensajeDiv.style.color = "#f43052";
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
    actualizarMensaje(nombre,valido)
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
    actualizarMensaje(apellidos,valido)
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
    actualizarMensaje(telefono,valido)
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
    actualizarMensaje(input,valido)
}

function validarEmail(input_email) {
    let email = document.getElementById('input_email');

    let regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/;

    let valido = regex.test(input_email.value);
    let mensaje_email = document.getElementById('mensaje_email');

    if(valido){
        input_email.classList.add("valido");
        input_email.classList.remove("no_valido");

    }else{
        input_email.classList.add("no_valido");
        input_email.classList.remove("valido");
    }

    actualizarMensaje(input_email,valido)
}

function validarSelect() {
    let select = document.getElementById("sel_entrenamiento");
    return select.value !== "";
}

function validarCheckbox() {
    let checkbox = document.getElementById("checkbox_terminos");
    return checkbox.checked;
}




// funcion para comprobar todos los elementos del formulario
function checkElementos(){
    let form_contacto = document.getElementById("form_contacto");
    const inputs = Array.from(form_contacto.querySelectorAll("input"));

    let todosValidos = true;

    // Validar inputs con clase .valido
    inputs.forEach((input)=>{
        if (input.type !== "checkbox" && !input.classList.contains("valido")) {
            todosValidos = false;
        }
    });

    // Validar select y checkbox
    if (!validarSelect() || !validarCheckbox()) {
        todosValidos = false;
    }

    let btn_enviar = document.getElementById("btn_enviar");
    btn_enviar.disabled = !todosValidos;
}



// Asignamos eventos
document.getElementById("input_nombre").addEventListener("focus", (event) => {
    mostrarMensaje(event.target, "El nombre debe contener entre 3-20 caracteres, únicamente letras y puede incluir espacios y acentos.");
});
document.getElementById("input_nombre").addEventListener("input", (event) => {
    validarNombre(event.target);
    checkElementos();
});

document.getElementById("input_email").addEventListener("focus", (event) => {
    mostrarMensaje(event.target, "El Email debe contener un @ y una única extensión.");
});
document.getElementById("input_email").addEventListener("input", (event) => {
    validarEmail(event.target);
    checkElementos();
});
document.getElementById("input_telefono").addEventListener("focus", (event) => {
    mostrarMensaje(event.target, "El teléfono debe comenzar por 6,7,8 o 9 y contener 9 dígitos. ");
});
document.getElementById("input_telefono").addEventListener("input", (event) => {
    validarTelefono(event.target);
    checkElementos();
});

document.getElementById("sel_entrenamiento").addEventListener("change", () => {
    checkElementos();
});

document.getElementById("checkbox_terminos").addEventListener("change", () => {
    checkElementos();
});

