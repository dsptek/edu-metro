
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { metrosActions, metroActions, adminActions } from '../../metro/module/metroList';
import MetroList from '../component/MetroList';


class MetroListContainer extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  init(){
    const { metrosActions } = this.props;

    metrosActions.clearStore()
      .then(metrosActions.findMetros);
  }

  // @Override
  componentDidMount() {
    this.init();
  }


  onSaveEditedMetro(metroCdo, admin) {
    //
    return this.props.metroActions.createMetro(metroCdo, admin).then(this.init);
  }

  onCancelEditing() {
    this.props.metroActions.clearMetro();
    this.props.adminActions.clearAdmin();
  }


  render() {
    //
    const {
      metros, metro, admin,
    } = this.props;

    return (
      <MetroList
        metros={metros}
        editingMetro={metro}
        editingAdmin={admin}

        onAdd={this.props.metroActions.setNewMetro}
        onChangeEditingMetroProp={this.props.metroActions.setMetroProp}
        onChangeEditingAdminProp={this.props.adminActions.setAdminProp}
        cancelEditing={this.onCancelEditing}
        saveEditedMetro={this.props.metroActions.createMetro}
      />
    );
  }
}


const mapStateToProps = ({ metroListStore }) => {
  return {
    metros: metroListStore.metros,
    metro: metroListStore.metro,
    admin: metroListStore.admin,
  };
};

const mapDispatchToProps = (dispatch) => {
  //
  return {
    metrosActions: bindActionCreators(metrosActions, dispatch),
    metroActions: bindActionCreators(metroActions, dispatch),
    adminActions: bindActionCreators(adminActions, dispatch),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(MetroListContainer);
