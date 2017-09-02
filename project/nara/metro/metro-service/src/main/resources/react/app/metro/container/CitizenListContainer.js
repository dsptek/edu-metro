
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { Button } from 'react-bootstrap';

import { dialogUtil, NaraWrapper } from 'nara-react';
import { citizensActions, citizenActions } from '../module/citizenList';
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

    this.props.citizensActions.clear()
      .then(() => this.props.citizensActions.find(metroId))
      .then(this.props.citizenActions.clear)
      .then(() => {
        if (citizenId === 'new') {
          this.props.citizenActions.setNew();
        }
        else if (citizenId) {
          let selected;

          this.props.citizens.some((citizen) => {
            const result = citizenId === citizen.id;

            if (result === true) {
              selected = citizen;
            }
            return result;
          });

          if (selected) {
            this.props.citizenActions.set(selected);
          }
        }
      });
  }


  // @Override
  componentDidMount() {
    this.init();
  }


  onAddCitizen() {
    //
    const { metroId } = this.props.params;

    this.props.citizenActions.setNew()
      .then(() => this.props.router.push(`/metros/${metroId}/citizens/new`));
  }

  onSelectCitizen(citizen) {
    //
    const { metroId } = this.props.params;

    this.props.citizenActions.set(citizen)
      .then(() => this.props.router.push(`/metros/${metroId}/citizens/${citizen.id}`));
  }

  onCloseCitizen() {
    //
    const { metroId } = this.props.params;

    this.props.citizenActions.clear()
      .then(() => this.props.router.push(`/metros/${metroId}/citizens`));
  }

  onChangeCitizenProp(propName, propValue) {
    //
    if (Array.isArray(propName)) {
      this.props.citizenActions.setCitizenNameProp(propName[propName.length - 1], propValue);
    }
    else {
      this.props.citizenActions.setCitizenProp(propName, propValue);
    }
  }

  onSave() {
    //
    const { params, citizen } = this.props;

    if (!citizen.name.firstName) {
      dialogUtil.notify('Please enter the first name.', 'warning');
      return;
    }
    if (!citizen.name.familyName) {
      dialogUtil.notify('Please enter the family name.', 'warning');
      return;
    }
    if (!citizen.email) {
      dialogUtil.notify('Please enter the email.', 'warning');
      return;
    }

    return this.props.citizenActions.save(params.metroId, citizen)
      .then(() => dialogUtil.notify('Success save', 'success'))
      .then(this.init);
  }

  onDisqualify() {
    //
    dialogUtil.confirm('Really disqualify this citizen?', () => {
      return this.props.citizenActions.disqualify(this.props.citizen.id)
        .then(() => dialogUtil.notify('Success disqualify', 'success'))
        .then(this.init);
    });
  }


  render() {
    //
    const { citizens, citizen } = this.props;
    const addable = !citizen || citizen.new === false;

    return (
      <NaraWrapper className="content-inner">

        <h3>Citizens</h3>

        <div className="row">
          <div className="responsive-wrap">

            <div className="responsive-box">
                <CitizenList
                  citizens={citizens}
                  buttons={[
                    <Button bsSize="small" bsStyle="primary" disabled={!addable} onClick={this.onAddCitizen}>
                      <span>
                        <em className="fa fa-plus mr-sm"/>Add
                      </span>
                    </Button>
                  ]}

                  onSelectCitizen={this.onSelectCitizen}
                />
            </div>

            { citizen &&
              <div className="responsive-box">
                <Citizen
                  citizen={citizen}
                  buttons={[
                    <Button bsStyle="danger" bsSize="small" className={citizen.new && 'hidden'} onClick={this.onDisqualify} >
                      <span><em className="fa fa-check mr-sm"/>Disqualify</span>
                    </Button>,
                    <Button bsStyle="primary" bsSize="small" onClick={this.onSave} >
                      <span><em className="fa fa-check mr-sm"/>Save</span>
                    </Button>
                  ]}

                  onClose={this.onCloseCitizen}
                  onChangeCitizenProp={this.onChangeCitizenProp}
                />
              </div>
            }

          </div>
        </div>
      </NaraWrapper>
    );
  }

}


const mapStateToProps = ({ citizenListStore }) => {
  return {
    citizens: citizenListStore.citizens,
    citizen: citizenListStore.citizen,
  };
};

const mapDispatchToProps = (dispatch) => {
  //
  return {
    citizensActions: bindActionCreators(citizensActions, dispatch),
    citizenActions: bindActionCreators(citizenActions, dispatch),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(CitizenListContainer);
