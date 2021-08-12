import { useState } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { Form, Button } from "react-bootstrap";
import backend from "../../../backend";
import { useHistory } from "react-router";
import Errors from "../../commons/components/Errors";

const SuperviseControl = (props) => {

    let photographyData = props.photographyData;
    let userData = props.userData;
    let contestName = props.contestName;

    const intl = useIntl();
    const history = useHistory();
    const [decision, setDecision] = useState();
    const [explanation, setExplanation] = useState();
    const [backendErrors, setBackendErrors] = useState();
    let form;

    const handleSubmit = event => {
        event.preventDefault();

        if (form.checkValidity()) {
            backend.catalogService.supervisePhotography(
                {
                    idFotografia: photographyData.idFotografia,
                    idConcurso: photographyData.idConcurso,
                    nombreConcurso : contestName,
                    nombreFotografia: photographyData.tituloFotografia,
                    decision: decision,
                    motivo: explanation,
                    nombreUsuarioAutor: userData.nombreUsuario
                },
                history.push("/"),
                errors => setBackendErrors(errors)
            );
        } else {
            form.classList.add('was-validated');
        }

    }

    return (
        <div className="photographySupervisionControl centeredParagraph">
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <form ref={node => form = node} noValidate={false}>
                <h5><FormattedMessage id='contest.supervision.table.manage' /></h5>
                <br />
                <h6><FormattedMessage id='contest.supervision.superviseControl.Decision' /></h6>
                <select name="metodoVoto" selec required onChange={e => setDecision(e.target.value)}>
                    <option value=""></option>
                    <option selected={decision === "APROBADA"} value="APROBADA">{intl.formatMessage({ id: 'contest.supervision.superviseControl.Approved' })}</option>
                    <option selected={decision === "DENEGADA"} value="DENEGADA">{intl.formatMessage({ id: 'contest.supervision.superviseControl.Denied' })}</option>
                </select>
                <br />
                <br />
                <Form.Group className="mb-3">
                    <Form.Label><h6><FormattedMessage id='contest.supervision.superviseControl.Explanation' /></h6></Form.Label>
                    <Form.Control as="textarea" required value={explanation} onChange={e => setExplanation(e.target.value)} maxLength="200" />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>


                <Button variant="success" type="submit" onClick={e => handleSubmit(e)}>

                    <FormattedMessage id='contest.EditContest.SaveChangues' />

                </Button>
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
            </form>

        </div>
    )
}

export default SuperviseControl;