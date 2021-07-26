const tipoLabelConcurso = (estado) => {

    switch(estado){
        case "ABIERTO" : return "success"
        case "VOTACION": return "warning"
        case "FINALIZADO" : return "danger"
        default : return "success"
    }
}

const imgToBase64 = (file) => {

    const reader = new FileReader();

    if (file) {
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            let imgB64 = reader.result;
            let indexOfComma = imgB64.indexOf(",");
            imgB64 = imgB64.substr(indexOfComma + 1);
            return imgB64;
          }
    }
}

let exportObj = {tipoLabelConcurso, imgToBase64}

export default exportObj;