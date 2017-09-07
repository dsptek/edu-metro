
import React, { Component} from 'react';
import PropTypes from 'prop-types';
import autoBind from 'react-autobind';

import { naraContext } from 'nara-core';
import HeaderDramas from '../../common/component/HeaderDramas';
import DramaLaunchpad from '../../common/component/DramaLaunchpad';


class MetroHeaderContainer extends Component {
  //
  static propTypes = {
    router: PropTypes.object,
    location: PropTypes.object,
  };

  static getDramas() {
    return [
      //
      { name: 'Nara',     className: 'app-img thumb24', img: `${naraContext.path()}/res/img/app-icon/no_icon.png`,     url: `/nara` },
      { name: 'Metro',    className: 'app-img thumb24', img: `${naraContext.path()}/res/img/app-icon/no_icon.png`,     url: `/metros/M1` },
    ];
  }

  constructor(props) {
    super(props);
    autoBind(this);
  }


  render() {
    //
    const props = this.props;

    return (
      <header className="nara-topnavbar-wrapper">
        { /* START Top Navbar */ }
        <nav role="navigation" className="navbar topnavbar pavbar" >

          <HeaderDramas
            loggedIn={props.loggedIn}
            dramas={MetroHeaderContainer.getDramas()}
            // pathname={props.location.pathname}
            pathname=""
            onToggleLaunchpad={() => {}}
          />

          { props.launchpadOpened &&
            <DramaLaunchpad
              dramas={MetroHeaderContainer.getDramas()}
              launchpadOpened={false}
              onCloseLaunchpad={() => {}}
            />
          }

        </nav>
        { /* END Top Navbar */ }
      </header>
    );
  }
}

export default MetroHeaderContainer;
