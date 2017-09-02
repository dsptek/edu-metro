
import { action, dispatchToState, promiseAndDispatch } from 'nara-redux-util';
import citizenApi from '../api/citizenApi';


const initialState = {
  //
  admins: [],
};

const actionTypes = {
  //
  CLEAR_ADMINS: 'adminList.CLEAR_ADMINS',
  SET_ADMINS: 'adminList.SET_ADMINS',
};


const adminsActions = {
  //
  findAdmins(metroId) {
    return promiseAndDispatch(
      () => citizenApi.findAdmins(metroId),
      () => action(actionTypes.SET_ADMINS),
    );
  },
  
  clearAdmins() {
    return dispatchToState(actionTypes.CLEAR_ADMINS);
  },
};


function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {

    case actionTypes.CLEAR_ADMINS:
      return {
        ...state,
        admins: initialState.admins,
      };

    case actionTypes.SET_METROS:
      return {
        ...state,
        metros: [...payload],
      };


    default:
      return state;
  }
}

export default reducer;
export { adminsActions };
