
var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');



module.exports = {
  //
  entry: {
    'vendor': './app/Vendor.js',
    'app': './app/App.js',
  },

  resolve: {
    modules: [path.resolve(__dirname, 'app'), 'node_modules'],
    descriptionFiles: ['package.json'],
    extensions: ['.js', '.jsx'],
  },

  node: {
    fs: 'empty'
  },

  module: {
    loaders: [{
        test: /jquery\.flot\.resize\.js$/,
        loader: 'imports-loader?this=>window'
      }, {
        test: /\.js/,
        loader: 'imports-loader?define=>false'
      },  {
        test: /\.js?$/,
        exclude: /(node_modules|bower_components)/,
        loaders: ['react-hot-loader']
      }, {
        test: /\.js?$/,
        exclude: /(node_modules|bower_components)/,
        loader: 'babel-loader',
        query: {
          presets: ['es2015', 'stage-2', 'react'],
          compact: false
        }
      }, {
        test: /\.css$/,
        exclude: path.join(process.cwd(), '/app'),
        loader: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader?sourceMap'
        })
      }, {
        test: /\.css$/,
        include: path.join(process.cwd(), '/app'),
        loader: 'raw-loader'
      },{
        test: /\.woff|\.woff2|\.svg|.eot|\.ttf/,
        loader: 'file-loader?publicPath=./&name=fonts/[name].[ext]',
      }, {
        test: /\.(png|jpg|gif)$/,
        loader: 'url-loader?limit=10000&publicPath=pav-res/&name=img/app/[name].[ext]',
      }, {
        test: /\.scss$/,
        loader: 'style-loader!css-loader!sass-loader?outputStyle=expanded',
      }],
  },

  // RTL(right to left)
  resolveLoader: {
    alias: {
      'rtlcss-loader': path.join(__dirname, 'rtlcss-loader.js')
    }
  },

  plugins: [
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      filename: 'vendor.[hash].js'
    }),
    // dist index.html 만들어주는 놈
    new HtmlWebpackPlugin({
      template: 'app/index.html',
      baseUrl: '/',
      xhtml: true,    // 태그 정상 닫히도록
    }),
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      'window.jQuery': 'jquery',
    }),
    new webpack.ContextReplacementPlugin(/\.\/locale$/, 'empty-module', false, /js$/),
    new webpack.DefinePlugin({
      WP_BASE_HREF: '/'
    }),
    new webpack.ProvidePlugin({
      React: 'react',
    }),
  ],

  externals: {
    'jquery': 'var NaraVendor.JQuery',
    'react': 'var NaraVendor.React',
    'prop-types': 'var NaraVendor.PropTypes',
    'react-dom': 'var NaraVendor.ReactDOM',
    'react-autobind': 'var NaraVendor.ReactAutobind',
    'react-router': 'var NaraVendor.ReactRouter',
    'redux': 'var NaraVendor.Redux',
    'redux-thunk': 'var NaraVendor.ReduxThunk',
    'react-redux': 'var NaraVendor.ReactRedux',
    'react-router-redux': 'var NaraVendor.ReactRouterRedux',
    'react-bootstrap': 'var NaraVendor.ReactBootstrap',
    'react-router-bootstrap': 'var NaraVendor.ReactRouterBootstrap',
    'whatwg-fetch': 'var NaraVendor.WhatwgFetch',

    'nara-core': 'var nara.core',
    'nara-react': 'var nara.react',
    'nara-redux-util': 'var nara.reduxUtil',
  },
};
