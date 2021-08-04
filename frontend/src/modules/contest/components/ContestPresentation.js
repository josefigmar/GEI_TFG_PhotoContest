const ContestPresentation = ({ contestData }) => {
    return (
        <div className="contestPresentation">
            <div className="centeredHorizontalDiv">
                <h3>{contestData.nombreConcurso}</h3>
            </div>
            <br/><br/>
            <div>
                <img className="centeredImg contestHeaderImg" alt="Contest Header" src={`data:image/jpeg;base64, ${contestData.fotoConcurso}`} />
            <div/>
            <br/>
            <div>
                <p className="p2">{contestData.descripcionConcurso}</p>
            </div>
                
            </div>
        </div>
    )

}

export default ContestPresentation;