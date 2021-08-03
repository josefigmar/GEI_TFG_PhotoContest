const tipoLabelConcurso = (estado) => {

    switch(estado){
        case "EN_PREPARACION" : return "info"
        case "ABIERTO" : return "success"
        case "VOTACION": return "warning"
        case "FINALIZADO" : return "danger"
        default : return "success"
    }
}

const tipoLabelAcceso = (estado) => {

    switch(estado){
        case true : return "success"
        case false : return "warning"
        default : return "success"
    }
}

const tipoLabelVotante = (estado) => {

    switch(estado){
        case "CUALQUIERA" : return "success"
        case "JURADO" : return "warning"
        case "PARTICIPANTE" : return "info"
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

let exportObj = {tipoLabelConcurso, tipoLabelAcceso, tipoLabelVotante, fileToBase64}

export default exportObj;