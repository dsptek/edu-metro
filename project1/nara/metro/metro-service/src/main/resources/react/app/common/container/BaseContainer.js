
import React, { Component } from 'react';
import autoBind from 'react-autobind';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import ReactCSSTransitionGroup from 'react-addons-css-transition-group';

import { naraContext } from 'nara-core';
import BaseHeader from './BaseHeaderContainer';


class BaseContainer extends Component {
  //
  constructor(props) {
    //
    super(props);
    autoBind(this);
  }

  // @Override
  componentDidMount() {
    //
    const { type, metroId } = naraContext.getContext();

    // if (type !== 'Local' && this.props.location.pathname === '/') {
      this.props.router.push(`/metros/${metroId}`);
      console.log('dd')
    // }
  }

  render() {
    //
    // Animations supported
    const animationNames = {
      RAG_FADE_IN: 'rag-fadeIn',
      RAG_FADE_IN_UP: 'rag-fadeInUp',
      RAG_FADE_IN_DOWN: 'rag-fadeInDown',
      RAG_FADE_IN_RIGHT: 'rag-fadeInRight',
      RAG_FADE_IN_LEFT: 'rag-fadeInLeft',
      RAG_FADE_IN_UP_BIG: 'rag-fadeInUpBig',
      RAG_FADE_IN_DOWN_BIG: 'rag-fadeInDownBig',
      RAG_FADE_IN_RIGHT_BIG: 'rag-fadeInRightBig',
      RAG_FADE_IN_LEFT_BIG: 'rag-fadeInLeftBig',
      RAG_ZOOM_BACK_DOWN: 'rag-zoomBackDown',
    };

    return (
      <div className="wrapper">

        {/* Header */}
        { naraContext.getContext().type === 'Local' && this.props.header &&
          <BaseHeader
            header={this.props.header}
          />
        }

        { this.props.leftSideBarLayout && React.cloneElement(this.props.leftSideBarLayout)}

        <ReactCSSTransitionGroup
          component="section"
          transitionName={animationNames.RAG_FADE_IN}
          transitionEnterTimeout={500}
          transitionLeaveTimeout={500}
          style={{ marginLeft: '0px' }}
        >
          {React.cloneElement(this.props.contentLayout)}
        </ReactCSSTransitionGroup>

      </div>
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

export default connect(mapStateToProps, mapDispatchToProps)(BaseContainer);
