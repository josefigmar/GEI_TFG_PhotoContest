import { FormattedMessage } from "react-intl";

const StatusSelector = (selectProps) => {

    // No funciona si Abierto = 0, se suma una unidad y luego se resta
    const statuses = [
        {id: 1, value: "Abierto"},
        {id: 2, value: "Votacion"},
        {id: 3, value: "Finalizado"},
    ]
    
    return (

        <select {...selectProps}>

            <FormattedMessage id='catalog.StatusSelector.allStatuses'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {statuses.map(status => 
                <option key={status.id} value={status.id}>{status.value}</option>
            )}

        </select>

    );

}

export default StatusSelector;