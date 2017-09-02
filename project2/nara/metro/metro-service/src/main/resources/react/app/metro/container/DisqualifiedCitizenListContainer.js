
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Button } from 'react-bootstrap';

import { dialogUtil, NaraWrapper } from 'nara-react';
import { citizenActions } from '../module/citizenList';
import { disqualifiedActions } from '../module/disqualifiedCitizenList';
import CitizenList from '../component/CitizenList';
import Citizen from '../component/Citizen';


class CitizenListContainer extends Component {
  //
  constructor(props) {
    super(props);
    autoBind(this);
  }

  init() {
    //
    const { metroId, citizenId } = this.props.params;

    this.props.disqualifiedActions.clear()
      .then(() => this.props.disqualifiedActions.find(metroId))
      .then(this.props.citizenActions.clear)
      .then(() => {
        if (!citizenId) {
          return;
        }

        let selected;

        this.props.disqualifiedCitizens.some((citizen) => {
          const result = citizenId === citizen.id;

          if (result === true) {
            selected = citizen;
          }
          return result;
        });

        if (selected) {
          this.props.citizenActions.set(selected);
        }
      });
  }


  // @Override
  componentDidMount() {
    this.init();
  }


  onSelectCitizen(citizen) {
    //
    const { metroId } = this.props.params;

    this.props.citizenActions.set(citizen)
      .then(() => this.props.router.push(`/metros/${metroId}/disqualified-citizens/${citizen.id}`));
  }

  onCloseCitizen() {
    //
    const { metroId } = this.props.params;

    this.props.citizenActions.clear()
      .then(() => this.props.router.push(`/metros/${metroId}/disqualified-citizens`));
  }

  onQualify(citizen) {
    dialogUtil.notify('Sorry. Not ready to yet.', 'info');
    // return this.props.citizenActions.disqualify(citizen.id).then(this.init);
  }


  render() {
    //
    const { disqualifiedCitizens, citizen } = this.props;

    return (
      <NaraWrapper className="content-inner">

        <h3>Disqualified Citizens</h3>

        <div className="row">
          <div className="responsive-wrap">

            <div className="responsive-box">
                <CitizenList
                  citizens={disqualifiedCitizens}
                  onSelectCitizen={this.onSelectCitizen}
                />
            </div>

            { citizen &&
              <div className="responsive-box">
                <Citizen
                  citizen={citizen}
                  buttons={[
                    <Button bsStyle="primary" bsSize="small" onClick={this.onQualify} >
                      <span><em className="fa fa-check mr-sm"/>Qualify</span>
                    </Button>
                  ]}

                  onClose={this.onCloseCitizen}
                />
              </div>
            }

          </div>
        </div>
      </NaraWrapper>
    );
  }
}


const mapStateToProps = ({ disqualifiedCitizenListStore, citizenListStore }) => {
  return {
    disqualifiedCitizens: disqualifiedCitizenListStore.disqualifiedCitizens,
    citizen: citizenListStore.citizen,
  };
};

const mapDispatchToProps = (dispatch) => {
  //
  return {
    disqualifiedActions: bindActionCreators(disqualifiedActions, dispatch),
    citizenActions: bindActionCreators(citizenActions, dispatch),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(CitizenListContainer);
