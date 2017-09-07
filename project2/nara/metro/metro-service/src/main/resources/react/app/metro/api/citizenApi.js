
import { ajax, naraContext } from 'nara-core';
import { dialogUtil } from 'nara-react';
import objectUtil from '../../common/js/objectUtil';


ajax.setErrorCallback((errorCode) => {
  //
  if (errorCode === 401) {
    dialogUtil.alert('Time has expired and you are logged out.', () => {
      window.location.pathname = '/';
    });
    console.log('[Metro] Metro reload cause error code : 401');
  }
});

const citizenBaseUrl = `${naraContext.path()}/api/s/citizens`;
const disqualifiedBaseUrl = `${naraContext.path()}/api/s/disqualified-citizens`;

export default {
  //
  registerCitizen: ({ metroId, name, email, /*Optionals*/ username, phone, password }) =>
    ajax.postJSON(`${citizenBaseUrl}`, { metroId, name, email, username, phone, password }),

  findCitizens: (metroId, offset, limit) =>
    ajax.getJSON(`${citizenBaseUrl}?metroId=${metroId}&offset=${offset}&limit=${limit}`),

  findDisqualifiedCitizens: (metroId) =>
    ajax.getJSON(`${disqualifiedBaseUrl}?metroId=${metroId}`),

  modifyCitizen: (citizenId, citizenUdo) =>
    ajax.putJSON(`${citizenBaseUrl}/${citizenId}`, objectUtil.toNameValues(citizenUdo, ['name', 'username', 'email', 'phone', 'guest'])),

  disqualifyCitizen: (citizenId) =>
    ajax.putJSON(`${citizenBaseUrl}/${citizenId}/disqualification`),

  existsCitizenName: (metroId, userName) =>
    ajax.getJSON(`${citizenBaseUrl}/exists?metroId=${metroId}&username=${userName}`),

  existsCitizenEmail: (metroId, email) =>
    ajax.getJSON(`${citizenBaseUrl}/exists?metroId=${metroId}&email=${email}`),


  registerMetroAdminCitizen: ({ metroId, name, email}) =>
    ajax.postJSON(`${citizenBaseUrl}/admin`, { metroId, name, email }),
};
