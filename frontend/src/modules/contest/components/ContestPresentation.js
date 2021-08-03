const ContestPresentation = ({ contestData }) => {
    return (
        <div className="contestPresentation">
            <div>
                <img className="contestHeaderImg" src={`data:image/jpeg;base64, ${contestData.fotoConcurso}`} />
            </div>&ensp;&ensp;&ensp;
            <div>
                <h3>{contestData.nombreConcurso}</h3>
                <h6>{contestData.descripcionConcurso}</h6>
            </div>
        </div>
    )

}

export default ContestPresentation;