
import { action, dispatchToState, promiseAndDispatch, promiseAndThen } from 'nara-redux-util';
import citizenApi from '../api/citizenApi';


const initialState = {
  //
  disqualifiedCitizens: [],
};


const actionTypes = {
  //
  CLEAR_CITIZENS: 'disqualifiedCitizenList.CLEAR_CITIZENS',
  SET_CITIZENS: 'disqualifiedCitizenList.SET_CITIZENS',
  DETACH_CITIZEN: 'disqualifiedCitizenList.DETACH_CITIZEN',
};


const disqualifiedActions = {
  //
  find(metroId) {
    return promiseAndDispatch(
      () => citizenApi.findDisqualifiedCitizens(metroId),
      (citizens) => action(actionTypes.SET_CITIZENS, citizens),
    );
  },

  clear() {
    return dispatchToState(actionTypes.CLEAR_CITIZENS);
  },

};


function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {

    case actionTypes.CLEAR_CITIZENS:
      return {
        ...state,
        disqualifiedCitizens: initialState.disqualifiedCitizens,
      };

    case actionTypes.SET_CITIZENS:
      return {
        ...state,
        disqualifiedCitizens: [...payload],
      };

    default:
      return state;
  }
}

export default reducer;
export { disqualifiedActions };
