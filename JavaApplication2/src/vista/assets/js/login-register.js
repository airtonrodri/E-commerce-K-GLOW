// Obtener los elementos de contraseña y confirmación de contraseña
var passwordInput = document.getElementById('password');
var confirmPasswordInput = document.getElementById('confirm-password');

function passwordChart() {
    // Obtener el valor del campo de contraseña
    let passwordValue = passwordInput.value;

    //verificar letra, numero y tamaño.
    let tieneLetra = /[a-zA-Z]/.test(passwordValue);
    let tieneNumero = /[0-9]/.test(passwordValue);
    let tieneTamaño = passwordValue.length >= 8 && passwordValue.length <= 20;

    // Verificar si el valor cumple con los requisitos
    if (tieneLetra && tieneNumero && tieneTamaño) {
        passwordInput.classList.remove('is-invalid');
        passwordInput.classList.add('is-valid');
    } else {
        // Si no cumple con los requisitos
        passwordInput.classList.remove('is-valid');
        passwordInput.classList.add('is-invalid');
    }
}

// Función para verificar si las contraseñas coinciden
function validatePasswords() {
    if (passwordInput.value !== confirmPasswordInput.value) {
        confirmPasswordInput.classList.add('is-invalid');
    } else {
        confirmPasswordInput.classList.remove('is-invalid');
        confirmPasswordInput.classList.add('is-valid');
    }
}


passwordInput.addEventListener('input',passwordChart);
passwordInput.addEventListener('input', validatePasswords);
confirmPasswordInput.addEventListener('input', validatePasswords);




