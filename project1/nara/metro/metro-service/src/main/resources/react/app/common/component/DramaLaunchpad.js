
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router';


class PavilionLaunchpad extends Component {
  //
  static propTypes = {
    onCloseLaunchpad: PropTypes.func.isRequired,
  };

  render() {
    //
    const props = this.props;

    if (!props.dramas) {
      return null;
    }

    return (
      <div className="nav-appmenu-wrap" >
        <div className="nav-appmenu-title">
          전체 드라마
          <span className="btn-close pull-right" title="닫기" onClick={props.onCloseLaunchpad}>
            <em className="fa fa-times"/>
          </span>
        </div>
        <ul className="nav-appmenu clearfix">

          { props.dramas.length > 0 &&
          props.dramas.map((drama) =>
            <li className="app-default">
              <Link className="app-icon"
                    to={drama.url}
                    onClick={props.onCloseLaunchpad}>
                <img className="app-img thumb32" src={drama.img} alt="drama icon" />
              </Link>
              <span className="app-title">{drama.name}</span>
            </li>
          )}
        </ul>
      </div>
    );
  }
}

export default PavilionLaunchpad;
