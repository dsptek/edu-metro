
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

const metroBaseUrl = `${naraContext.contextPath()}/api/s/metros`;

export default {
  //
  buildMetro:       ({ name, memo })          => ajax.postJSON(`${metroBaseUrl}`, { name, memo }),
  findMetros:       ()                        => ajax.getJSON(`${metroBaseUrl}`),
  findMetro:        (metroId)                 => ajax.getJSON(`${metroBaseUrl}/${metroId}`),
  modifyMetro:      (metroId, { name, memo }) => ajax.putJSON(`${metroBaseUrl}/${metroId}`, objectUtil.toNameValues({ name, memo })),
  existMetroByName: (metroName)               => ajax.getJSON(`${metroBaseUrl}/exists?name=${metroName}`),

  setMetroAdmins:   (metroId, admins)         => ajax.putJSON(`${metroBaseUrl}/${metroId}`, objectUtil.toNameValues({ admins })),
};
