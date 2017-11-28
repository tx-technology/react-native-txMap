import { requireNativeComponent, View, } from 'react-native';
import PropTypes from 'prop-types';
let mapItem = {
    name: 'MapView',
    propTypes: {
        ...View.propTypes
    }
};
export default requireNativeComponent('RCTMapView', mapItem);