import React from 'react';
import PropTypes from 'prop-types';

const Errors = ({ errors, onClose }) => {

    if (!errors) {
        return null;
    }

    let globalError;
    let fieldErrors;

    if (errors.errorGlobal) {
        globalError = errors.errorGlobal;
    } else if (errors.erroresCampos) {
        fieldErrors = [];
        errors.erroresCampos.forEach(e => {
            fieldErrors.push(`${e.nombreCampo}: ${e.mensaje}`)
        });

    }

    return (

        <div className="alert alert-danger alert-dismissible fade show" role="alert">

            {globalError ? globalError : ''}

            {fieldErrors ?
                <ul>
                    {fieldErrors.map((fieldError, index) =>
                        <li key={index}>{fieldError}</li>
                    )}
                </ul>
                :
                ''
            }

            <button type="button" className="close" data-dismiss="alert" aria-label="Close"
                onClick={() => onClose()}>
                <span aria-hidden="true">&times;</span>
            </button>

        </div>

    );

}

Errors.propTypes = {
    errors: PropTypes.oneOfType([PropTypes.object, PropTypes.array]),
    onClose: PropTypes.func.isRequired
};

export default Errors;