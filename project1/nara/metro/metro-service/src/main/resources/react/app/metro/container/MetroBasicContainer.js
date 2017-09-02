
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { NaraWrapper } from 'nara-react';
import { metroActions } from '../module/metroList';
import MetroBasic from '../component/MetroBasic';


class MetroBasicContainer extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  init() {
    const metroId = this.props.params.metroId;
    this.props.metroActions.findMetro(metroId);
  }

  // @Override
  componentDidMount() {
    this.init();
  }


  revert() {
    this.init();
  }

  save(metroId, metroUdo) {
    return this.props.metroActions.modifyMetro(metroId, metroUdo);
  }


  render() {
    //
    const { metro } = this.props;

    return (
      <MetroBasic
        metro={metro}

        onChangeMetroProp={this.props.metroActions.setMetroProp}
        revert={this.revert}
        save={this.save}
      />
    );
  }
}


const mapStateToProps = ({ metroListStore }) => {
  return {
    metro: metroListStore.metro,
  };
};

const mapDispatchToProps = (dispatch) => {
  //
  return {
    metroActions: bindActionCreators(metroActions, dispatch),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(MetroBasicContainer);
