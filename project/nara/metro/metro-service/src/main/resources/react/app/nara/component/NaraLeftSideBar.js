
import React, { Component } from 'react';

import LeftSideBar from '../../common/component/LeftSideBar';


class NaraLeftSideBar extends Component {
  //
  static TITLE = 'Nara';

  static MENUS = [
    { name: 'Metros',       url: '/nara/metros'},
    { name: 'Pavilion',     url: '/nara/setup'},
  ];


  render() {
    //
    return (
      <LeftSideBar
        location={this.props.location}
        title={NaraLeftSideBar.TITLE}
        items={NaraLeftSideBar.MENUS}
      />
    );
  }
}


export default NaraLeftSideBar;
