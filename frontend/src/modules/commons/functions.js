const tipoLabelConcurso = (estado) => {

    switch(estado){
        case "ABIERTO" : return "success"
        case "VOTACION": return "warning"
        case "FINALIZADO" : return "danger"
        default : return "success"
    }
}

export default tipoLabelConcurso;