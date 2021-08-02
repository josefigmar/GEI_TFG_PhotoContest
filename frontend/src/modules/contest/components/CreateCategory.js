import { useState } from "react";
import { Form, Button, Jumbotron } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import Errors from "../../commons/components/Errors";
import backend from "../../../backend";

const CreateCategory = ({onNew}) => {

    const [nombreCategoria, setNombreCategoria] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [backendErrors, setBackendErrors] = useState("");

    const clearData = () => {
        setNombreCategoria("");
        setDescripcion("");
        onNew(Array.from(Math.random()));
    }

    const handleSubmit = e => {
        
        e.preventDefault();
        backend.catalogService.createCategory(
            {
                nombreCategoria,
                descripcion
            },
            () => clearData(),
            errors => setBackendErrors(errors)            
        )

    }

    return (
        <Jumbotron>
            <Form>
                <h4><FormattedMessage id='contest.CreateContest.CreateCategory' /></h4><br />
                <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
                <Form.Group className="mb-3">
                    <Form.Label><FormattedMessage id='contest.CreateContest.CreateCategory.Name' /></Form.Label>
                    <Form.Control type="text" value={nombreCategoria} onChange={e => setNombreCategoria(e.target.value)} />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label><FormattedMessage id='contest.CreateContest.CreateCategory.Desc' /></Form.Label>
                    <Form.Control as="textarea" required value={descripcion} onChange={e => setDescripcion(e.target.value)} />
                </Form.Group>

                <Button variant="success"  onClick={e => handleSubmit(e)}>
                    <FormattedMessage id='contest.CreateContest.CreateCategoryConfirm' />
                </Button>
            </Form>
        </Jumbotron>

    );
}

export default CreateCategory;