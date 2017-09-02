
import { combineReducers, createStore, applyMiddleware } from 'redux';
import { routerReducer } from 'react-router-redux';
import thunk from 'redux-thunk';

import metroListStore from './metro/module/metroList';
import citizenListStore from './metro/module/citizenList';
import disqualifiedCitizenListStore from './metro/module/disqualifiedCitizenList';
import adminListStore from './metro/module/adminList';


const rootReducer = combineReducers({
  //
  routing: routerReducer,

  // add module
  metroListStore,
  citizenListStore,
  disqualifiedCitizenListStore,
  adminListStore,
});

export default createStore(
  rootReducer,
  applyMiddleware(thunk),
);
