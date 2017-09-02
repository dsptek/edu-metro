
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import autoBind from 'react-autobind';
import { Col, Button, ButtonToolbar, FormControl, FormGroup } from 'react-bootstrap';

import { dialogUtil } from 'nara-react';


class MetroBasic extends Component {
  //
  static propTypes = {
    metro: PropTypes.object,

    onChangeMetroProps: PropTypes.func.isRequired,
    revert: PropTypes.func.isRequired,
    save: PropTypes.func.isRequired,
  };

  constructor(props) {
    super(props);
    autoBind(this);
  }


  onSave() {
    //
    const { metro } = this.props;

    if (!metro.name) {
      dialogUtil.notify('Please enter the pavilion name', 'warning');
      return;
    }

    this.props.save(metro.id, metro).then(() => dialogUtil.notify('Success to save pavilion', 'success'));
  }


  render() {
    //
    if (!this.props.metro) {
      return null;
    }

    const {
      metro,
      onChangeMetroProp, revert
    } = this.props;

    return (
      <div className="content-wrapper content-inner">

        <h3>Basic settings</h3>

        <div className="panel mb0 b radius-clear">

          <div className="panel-heading bg-light-gray">
            <div className="clearfix">

              <h4 className="m-sm inline">Metro</h4>

              <div className="pull-right">
                <ButtonToolbar>
                  <Button bsSize="small" onClick={revert}>
                    <span><em className="fa fa-undo mr-sm"/>Revert</span>
                  </Button>
                  <Button bsSize="small" bsStyle="primary" onClick={this.onSave}>
                    <span><em className="fa fa-check mr-sm"/>Save</span>
                  </Button>
                </ButtonToolbar>
              </div>
            </div>
          </div>

          <div className="panel-body bt p0">
            <Col lg={ 12 } xs={ 12 } className="bb p">
              <FormGroup>
                <label htmlFor="input-id-1" className="col-sm-3 control-label p-sm">
                  Metro name
                </label>
                <Col sm={ 9 }>
                  <FormControl type="text" className="form-control m-b" placeholder="Metro name" title="Metro name" dir="auto" autoFocus={true}
                               value={metro.name} onChange={(e) => onChangeMetroProp('name', e.target.value)}
                  />
                </Col>
              </FormGroup>
            </Col>

            <Col lg={ 12 } xs={ 12 } className="bb p">
              <FormGroup>
                <label htmlFor="input-id-1" className="col-sm-3 control-label p-sm">
                  Memo
                </label>
                <Col sm={ 9 }>
                  <FormControl type="text" className="form-control m-b" placeholder="Memo" title="Memo" dir="auto" value={metro.memo}
                               onChange={(e) => onChangeMetroProp('memo', e.target.value)}
                  />
                </Col>
              </FormGroup>
            </Col>

            <Col lg={ 12 } xs={ 12 } className="bb p">
              <FormGroup>
                <label htmlFor="input-id-1" className="col-sm-3 control-label p-sm">
                  Time
                </label>
                <Col sm={ 9 }>
                  <FormControl type="text" className="form-control m-b" placeholder="Time" title="Time" dir="auto" readOnly={true}
                               value={new Date(metro.time).toLocaleDateString()}
                  />
                </Col>
              </FormGroup>
            </Col>
          </div>
        </div>
      </div>
    );
  }
}


export default MetroBasic;
