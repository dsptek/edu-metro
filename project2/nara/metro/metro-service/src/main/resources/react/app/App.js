
import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute, IndexRedirect, browserHistory } from 'react-router';
import { Provider } from 'react-redux';
import { syncHistoryWithStore } from 'react-router-redux';

import { naraContext } from 'nara-core';

import store from './store';

import Base from './common/container/BaseContainer';
import LeftSideBarLayout from './common/component/LeftSideBarLayout';
import ContentLayout from './common/component/ContentLayout';
import NotFound from './common/component/NotFound';

import MetroHeader from './common/container/MetroHeaderContainer';
import Foyer from './common/container/FoyerContainer';


import NaraLeftSideBar from './nara/component/NaraLeftSideBar';
import NaraMetroList from './nara/container/MetroListContainer';
import PavilionSetup from './nara/container/PavilionSetupContainer';


import MetroLeftSideBar from './metro/component/MetroLeftSideBar';
import MetroBasic from './metro/container/MetroBasicContainer';
import CitizenList from './metro/container/CitizenListContainer';
import DisqualifiedCitizenList from './metro/container/DisqualifiedCitizenListContainer';


if (!naraContext.isInitialized()) {
  naraContext.__setDevContext({
    contextPath: '',
    metroId: 'M1',
  });
  console.debug(naraContext.getContext());
}
else {
  console.log('Start nara drama with pavilion mode');
  console.debug(`Nara drama base-path : ${naraContext.getContext().contextPath}`);
}
// ex) stand-alone => "/", pavilion => "/pav-proxy/****/"
//     window.document.getElementsByTagName('base')[0].href = DRAMA_CONTEXT


const history = syncHistoryWithStore(browserHistory, store);

const metroId = naraContext.getContext().metroId;


ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path={`${naraContext.path()}/`} component={Base} >

        <Route components={{ header: MetroHeader, leftSideBarLayout: LeftSideBarLayout, contentLayout: ContentLayout }} >

          <IndexRedirect to={`metros/${metroId}`} />

          <Route path="nara" components={{ leftSideBar: NaraLeftSideBar, children: ContentLayout}}>
            <IndexRedirect to="metros" />
            <Route path="metros" component={NaraMetroList} />
            <Route path="setup" component={PavilionSetup}>
              <IndexRedirect to="1" />
              <Route path=":step" />
            </Route>
          </Route>


          <Route path="metros/:metroId" components={{ leftSideBar: MetroLeftSideBar, children: ContentLayout }}>
            <IndexRedirect to="basic" />
            <Route path="basic" component={MetroBasic} />
            <Route path="citizens" component={CitizenList}>
              <Route path=":citizenId" />
            </Route>
            <Route path="disqualified-citizens" component={DisqualifiedCitizenList}>
              <Route path=":citizenId" />
            </Route>
          </Route>

        </Route>

      </Route>

      <Route path="*" component={NotFound}/>

    </Router>
  </Provider>,
  document.getElementById('app'),
);
