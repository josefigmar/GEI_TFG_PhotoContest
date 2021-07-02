import { FormattedMessage } from "react-intl";

const StatusSelector = (selectProps) => {

    const statuses = [
        {id: 0, value: "Abierto"},
        {id: 1, value: "Votacion"},
        {id: 2, value: "Finalizado"},
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