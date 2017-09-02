
import React, { Component } from 'react';


class LeftSideBarLayout extends Component {
  //
  render() {

    if (!this.props.leftSideBar) {
      return null;
    }

    return (
      React.cloneElement(this.props.leftSideBar)
    );
  }
}

export default LeftSideBarLayout;
