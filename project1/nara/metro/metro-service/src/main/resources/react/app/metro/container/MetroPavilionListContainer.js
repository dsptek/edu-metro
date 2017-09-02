
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { NaraWrapper } from 'nara-react';


class MetroPavilionListContainer extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }


  render() {
    return (
      <NaraWrapper className="content-inner">
        <h3>Pavilions</h3>
      </NaraWrapper>
    );
  }
}


const mapStateToProps = ({  }) => {
  return {
  };
};

const mapDispatchToProps = (dispatch) => {
  //
  return {
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(MetroPavilionListContainer);

