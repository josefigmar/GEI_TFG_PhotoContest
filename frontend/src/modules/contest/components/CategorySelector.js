import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';
import { useDispatch } from 'react-redux';

import * as selectors from '../selectors';
import * as actions from '../actions';

const CategorySelector = (selectProps) => {

    const categories = useSelector(selectors.getCategories);
    const dispatch = useDispatch();

    if(!categories){
        dispatch(actions.findCategories());
    }

    return (

        <select {...selectProps}>

            <FormattedMessage id='catalog.CategorySelector.allCategories'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {categories && categories.map(category => 
                <option key={category.idCategoria} value={category.idCategoria}>{category.nombreCategoria}</option>
            )}

        </select>

    );

}

CategorySelector.propTypes = {
    selectProps: PropTypes.object
};

export default CategorySelector;
