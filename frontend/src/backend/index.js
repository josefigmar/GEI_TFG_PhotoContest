import {init} from './appFetch';

import * as catalogService from './catalogService';
import * as userService from './userService';
import * as notificationService from './notificationService';

export {default as NetworkError} from "./NetworkError";

export default {init, catalogService, userService, notificationService};
