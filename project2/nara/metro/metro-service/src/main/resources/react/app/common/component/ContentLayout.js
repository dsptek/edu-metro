
import React, { Component } from 'react';


class ContentLayout extends Component {
  //
  render() {
    //
    if (!this.props.children) {
      return null;
    }

    return (
      React.cloneElement(this.props.children)
    );
  }
}

export default ContentLayout;
