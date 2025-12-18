// funcion para validar los campos del login
function validacionLogin() {
    const email = document.getElementById("email_empleado").value.trim();
    const password = document.getElementById("password_empleado").value.trim();
    const btnLogin = document.getElementById("btn_login");


    // Si ambos campos NO están vacíos, habilitamos el  botón; de lo contrario, deshabilitamos
    if (email !== "" && password !== "") {
        btnLogin.disabled = false;

    } else {
        btnLogin.disabled = true;

    }


}

function mostrarAlerta(alertaId) {
    const alerta = document.getElementById(alertaId);
    alerta.style.display = 'block';
    alerta.innerHTML="Debes completar todos los campos."
    setTimeout(() => {
        alerta.style.display = 'none';
    }, 3000);
}

document.addEventListener("DOMContentLoaded", function (){
    // Ejecutar la validación cada vez que el usuario escriba o borre
    document.getElementById("email_empleado").addEventListener("input", validacionLogin);
    document.getElementById("password_empleado").addEventListener("input", validacionLogin);


})