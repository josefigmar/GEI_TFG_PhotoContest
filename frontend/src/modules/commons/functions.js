const tipoLabelConcurso = (estado) => {

    switch(estado){
        case "EN_PREPARACION" : return "info"
        case "ABIERTO" : return "success"
        case "VOTACION": return "warning"
        case "FINALIZADO" : return "danger"
        default : return "success"
    }
}

const fileToBase64 = (file, funcion) => {

    const reader = new FileReader();

    if (file) {
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            let imgB64 = reader.result;
            let indexOfComma = imgB64.indexOf(",");
            imgB64 = imgB64.substr(indexOfComma + 1);
            funcion(imgB64);
          }
    }
}

let exportObj = {tipoLabelConcurso, fileToBase64}

export default exportObj;