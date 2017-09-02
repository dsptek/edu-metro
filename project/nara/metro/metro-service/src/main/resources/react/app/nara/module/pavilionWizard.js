
import { action, dispatchToState, promiseAndDispatch, promiseAndThen } from 'nara-redux-util';
import metroApi from '../api/metroApi';
import citizenApi from '../api/citizenApi';


const initialState = {
  //
  metros: [],
  metro: null,

  admin: {
    firstName: '',
    familyName: '',
    email: '',
  },
};

const actionTypes = {
  //
  CLEAR_STORE: 'metroList.CLEAR_STORE',

  CLEAR_METROS: 'metroList.CLEAR_METROS',
  SET_METROS: 'metroList.SET_METROS',

  CLEAR_METRO: 'metroList.CLEAR_METRO',
  SET_METRO: 'metroList.SET_METRO',
  SET_METRO_PROP: 'metroList.SET_METRO_PROP',

  CLEAR_ADMIN: 'metroList.CLEAR_ADMIN',
  SET_ADMIN_PROP: 'metroList.SET_ADMIN_PROP',
};

const constant = {
  'SAVE_RESULT': {
    'METRO_NAME_EXISTENT': 'METRO_NAME_EXISTENT',
    'ADMIN_NAME_EXISTENT': 'ADMIN_NAME_EXISTENT',
    'ADMIN_EMAIL_EXISTENT': 'ADMIN_EMAIL_EXISTENT',
  }
};


const metrosActions = {
  //
  clearStore() {
    return dispatchToState(actionTypes.CLEAR_STORE);
  },


  findMetros() {
    return promiseAndDispatch(
      () => metroApi.findMetros(),
      (metros) => action(actionTypes.SET_METROS, metros),
    );
  },

  clearMetros() {
    return dispatchToState(actionTypes.CLEAR_METROS);
  },
};

const metroActions = {
  //
  createMetro: (metroCdo, admin) => () => {
    //
    return metroApi.buildMetro(metroCdo)
      .then((metroId) => citizenApi.registerMetroAdminCitizen({
        metroId,
        name: {
          langLocale: 'ko_KR',
          firstName: admin.firstName,
          familyName: admin.familyName,
        },
        email: admin.email
      }));
  },

  findMetro(metroId) {
    return promiseAndDispatch(
      () => metroApi.findMetro(metroId),
      (metro) => action(actionTypes.SET_METRO, metro)
    );
  },

  modifyMetro(metroId, { name, memo }) {
    return promiseAndThen(
      () => metroApi.modifyMetro(metroId, { name, memo }),
    );
  },


  clearMetro() {
    return dispatchToState(actionTypes.CLEAR_METRO);
  },

  setNewMetro() {
    //
    const metro = {
      name: '',
      memo: '',
      time: null,
    };
    return dispatchToState(actionTypes.SET_METRO, metro);
  },

  setMetroProp(propName, value) {
    return dispatchToState(actionTypes.SET_METRO_PROP, { propName, value });
  },

};

const adminActions = {
  //
  assignPrimaryAdmin(citizenId, name) {
    return promiseAndDispatch(
      () => metroApi.setMetroAdmins({ id: citizenId, name: name })
    );
  },

  clearAdmin() {
    return dispatchToState(actionTypes.CLEAR_ADMIN);
  },

  setAdminProp(propName, value) {
    return dispatchToState(actionTypes.SET_ADMIN_PROP, { propName, value });
  }
};


function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {

    case actionTypes.CLEAR_STORE:
      return {
        ...initialState,
      };

    case actionTypes.CLEAR_METROS:
      return {
        ...state,
        metros: initialState.metros,
      };

    case actionTypes.SET_METROS:
      return {
        ...state,
        metros: [...payload],
      };


    case actionTypes.CLEAR_METRO:
      return {
        ...state,
        metro: initialState.metro,
      };

    case actionTypes.SET_METRO:
      return {
        ...state,
        metro: payload,
      };

    case actionTypes.SET_METRO_PROP:
      return {
        ...state,
        metro: {
          ...state.metro,
          [payload.propName]: payload.value,
        },
      };


    case actionTypes.CLEAR_ADMIN:
      return {
        ...state,
        admin: initialState.admin,
      };

    case actionTypes.SET_ADMIN_PROP:
      return {
        ...state,
        admin: {
          ...state.admin,
          [payload.propName]: payload.value,
        },
      };


    default:
      return state;
  }
}

export default reducer;
export { constant, metrosActions, metroActions, adminActions };
