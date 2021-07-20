import { FormattedMessage } from "react-intl";

const LenguagueSelector = (selectProps) => {

    const lenguagues = [
        {id: 0, value: "Español"},
        {id: 1, value: "Galego"},
        {id: 2, value: "English"},
    ]
    
    return (

        <select {...selectProps}>

            <FormattedMessage id='user.SignUp.Lenguagues'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {lenguagues.map(lenguaje => 
                <option key={lenguaje.id} value={lenguaje.id}>{lenguaje.value}</option>
            )}

        </select>

    );

}

export default LenguagueSelector;