import { FormattedMessage, useIntl } from "react-intl";

const StatusSelector = (selectProps) => {

    const intl = useIntl();
    // No funciona si Abierto = 0, se suma una unidad y luego se resta
    const statuses = [
        {id: 0, value: intl.formatMessage({id: 'app.ContestTable.Status.EN_PREPARACION'})},
        {id: 1, value: intl.formatMessage({id: 'app.ContestTable.Status.ABIERTO'})},
        {id: 2, value: intl.formatMessage({id:'app.ContestTable.Status.VOTACION'})},
        {id: 3, value: intl.formatMessage({id: 'app.ContestTable.Status.FINALIZADO'})},
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