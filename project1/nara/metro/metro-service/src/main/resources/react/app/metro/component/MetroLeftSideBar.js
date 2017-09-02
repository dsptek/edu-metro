
import React, { Component } from 'react';

import LeftSideBar from '../../common/component/LeftSideBar';


class MetroLeftSideBar extends Component {
  //
  static TITLE = 'Metro';

  getMenus() {
    //
    const metroId = this.props.params.metroId;

    return [
      { name: 'Basic',        url: `/metros/${metroId}/basic`},
      { name: 'Citizens',     url: `/metros/${metroId}/citizens`},
      { name: 'Disqualified citizens',    url: `/metros/${metroId}/disqualified-citizens`},
    ];
  }


  render() {
    //
    return (
      <LeftSideBar
        location={this.props.location}
        title={MetroLeftSideBar.TITLE}
        items={this.getMenus()}
      />
    );
  }
}


export default MetroLeftSideBar;
