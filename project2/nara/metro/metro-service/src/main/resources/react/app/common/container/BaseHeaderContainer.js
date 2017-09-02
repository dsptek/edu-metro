
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';


class BaseHeaderContainer extends Component {
  //
  constructor(props) {
    //
    super(props);
    autoBind(this);
  }


  render() {
    //
    const props = this.props;

    return (
      React.cloneElement(this.props.header, {
        router: props.router,
        location: props.location,
      })
    );
  }
}


const mapStateToProps = () => {
  return {
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(BaseHeaderContainer);
