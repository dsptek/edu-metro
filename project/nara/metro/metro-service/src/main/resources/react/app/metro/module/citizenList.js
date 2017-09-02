
import { action, dispatchToState, promiseAndDispatch, promiseAndThen } from 'nara-redux-util';
import citizenApi from '../api/citizenApi';


const initialState = {
  //
  citizens: [],
  citizen: null,
};

class Citizen {
  //
  constructor(model = {}) {
    //
    return {
      ...model,
      name: new CitizenName(model.name),
      username: model.username || '',
      email: model.email || '',
      phone: model.phone || '',
      guest: model.guest || false,
      time: model.time || null,
      disqualified : model.disqualified || false,

      new: Object.keys(model).length < 1,
    };
  }
}

class CitizenName {
  //
  constructor(model = {}) {
    return {
      ...model,
      firstName: model.firstName || '',
      middleName: model.middleName || '',
      familyName: model.familyName || '',
      displayName: model.displayName || '',
    };
  }
}

const actionTypes = {
  //
  CLEAR_CITIZENS: 'citizenList.CLEAR_CITIZENS',
  SET_CITIZENS: 'citizenList.SET_CITIZENS',
  DETACH_CITIZEN: 'citizenList.DETACH_CITIZEN',

  CLEAR_CITIZEN: 'citizenList.CLEAR_CITIZEN',
  SET_CITIZEN: 'citizenList.SET_CITIZEN',
  SET_CITIZEN_PROP: 'citizenList.SET_CITIZEN_PROP',
  SET_CITIZEN_NAME_PROP: 'citizenList.SET_NAME_PROP',
};

const constant = {
  CITIZEN_LIMIT_PER_PAGE: 100,
};


const citizensActions = {
  //
  find(metroId) {
    return promiseAndDispatch(
      () => citizenApi.findCitizens(metroId, 0, constant.CITIZEN_LIMIT_PER_PAGE),
      (citizens) => action(actionTypes.SET_CITIZENS, citizens.map((citizen) => new Citizen(citizen) )),
    );
  },

  clear() {
    return dispatchToState(actionTypes.CLEAR_CITIZENS);
  },

};

const citizenActions = {
  //
  save(metroId, citizen) {
    //
    if (citizen.new === true) {
      return promiseAndThen(
        () => citizenApi.registerCitizen({ metroId, ...citizen }),
        (citizenId) => {
          const promises = [];

          if (citizen.guest) {
            promises.push(citizenApi.modifyCitizen(citizenId, { guest: citizen.guest }));
          }
          if (citizen.password) {
            // TODO: 비밀번호 저장 api 호출
          }
          return Promise.all(promises);
        }
      );
    }
    else {
      return promiseAndThen(() => citizenApi.modifyCitizen(citizen.id, citizen));
    }
  },

  disqualify(citizenId) {
    return promiseAndThen(() => citizenApi.disqualifyCitizen(citizenId));
  },

  clear() {
    return dispatchToState(actionTypes.CLEAR_CITIZEN);
  },

  setNew() {
    return dispatchToState(actionTypes.SET_CITIZEN, new Citizen());
  },

  set(citizen) {
    return dispatchToState(actionTypes.SET_CITIZEN, citizen);
  },

  setCitizenProp(propName, value) {
    return dispatchToState(actionTypes.SET_CITIZEN_PROP, { propName, value });
  },

  setCitizenNameProp(propName, value) {
    return dispatchToState(actionTypes.SET_CITIZEN_NAME_PROP, { propName, value });
  },
};


function reducer(state = initialState, { type, payload }) {
  //
  switch (type) {

    case actionTypes.CLEAR_CITIZENS:
      return {
        ...state,
        citizens: initialState.citizens,
      };

    case actionTypes.SET_CITIZENS:
      return {
        ...state,
        citizens: [...payload],
      };


    case actionTypes.CLEAR_CITIZEN:
      return {
        ...state,
        citizen: initialState.citizen,
      };

    case actionTypes.SET_CITIZEN:
      return {
        ...state,
        citizen: payload,
      };

    case actionTypes.SET_CITIZEN_PROP:
      return {
        ...state,
        citizen: {
          ...state.citizen,
          [payload.propName]: payload.value,
        },
      };

    case actionTypes.SET_CITIZEN_NAME_PROP:
      return {
        ...state,
        citizen: {
          ...state.citizen,
          name: {
            ...state.citizen.name,
            [payload.propName]: payload.value,
          }
        }
      };

    default:
      return state;
  }
}

export default reducer;
export { citizensActions, citizenActions };
