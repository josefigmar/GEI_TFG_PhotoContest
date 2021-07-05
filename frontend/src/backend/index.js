import {init} from './appFetch';

import * as catalogService from './catalogService';
import * as userService from './userService';

export {default as NetworkError} from "./NetworkError";

export default {init, catalogService, userService};
