
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Grid, Row, Col, Panel, Button, FormControl } from 'react-bootstrap';

import { NaraWrapper } from 'nara-react';
import SetupNavigator from '../component/SetupNavigator';
import SetupStep1 from '../component/SetupStep1';
import SetupStep2 from '../component/SetupStep2';
import SetupButtons from '../component/SetupButtons';



class PavilionInitializerContainer extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  init() {
  }

  // @Override
  componentDidMount() {
    this.init();
  }




  render() {
    //
    const { params } = this.props;

    return (
      <NaraWrapper className="content-inner">

        <h3>Setup Pavilion</h3>

        <Panel>
          <form>
            <div className="wizard clearfix">

              <SetupNavigator
                step={params.step}
              />

              { params.step === '1' &&
                <SetupStep1 />
              }
              { params.step === '2' &&
              <SetupStep2 />
              }


              <SetupButtons />

            </div>
          </form>
        </Panel>
      </NaraWrapper>
    );
  }
}


const mapStateToProps = ({ metroListStore }) => {
  return {
  };
};

const mapDispatchToProps = (dispatch) => {
  //
  return {
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(PavilionInitializerContainer);
