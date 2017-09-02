
var webpack = require('webpack');
var webpackMerge = require('webpack-merge');
var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var StringReplacePlugin = require('string-replace-webpack-plugin');

var commonConfig = require('./webpack.common.js');

// Define external resources url
var externalResources = {
  commonAssetLocation: 'http://nara.namoo.io/common-asset',
};

module.exports = webpackMerge(commonConfig, {
  devtool: '#cheap-module-eval-source-map',

  // prod webpack에 있는 publicPath 넣어주는것을 권장
  output: {
    path: path.join(process.cwd(), '/dist'),
    filename: '[name].[hash].js',
  },

  module: {
    loaders: [
      {
        test: /index\.html$/,
        loaders: ['raw-loader', StringReplacePlugin.replace({
          replacements: [
            {
              pattern: /\*\{\S+\}/g,  // Search like ${commonAssetLocation}
              replacement(match) {
                const resourcePath = match.substring(2, match.length - 1);
                return externalResources[resourcePath];
              },
            },
            {
              pattern: /\sth:\S+=/g, // Search like th:src=, th:href=, remove thymeleaf tag.
              replacement(match) {
                return ' ' + match.substring(4, match.length);
              },
            },
            {
              pattern: /\+'\S+'/g, // Search like +'/vendor.js'
              replacement(match) {
                return match.substring(2, match.length - 1);
              },
            },
          ],
        })],
      }
    ],
  },
  plugins: [
    new ExtractTextPlugin('[name].[hash].css'),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: JSON.stringify('development'),
      },
    }),
    new CopyWebpackPlugin([{
      from: 'resources/img',
      to: 'res/img',
      context: path.join(__dirname, 'app'),
    }, {
      from: 'resources/server',
      to: 'res/server',
      context: path.join(__dirname, 'app'),
    }, {
      from: 'resources/fonts',
      to: 'res/fonts',
      context: path.join(__dirname, 'app'),
    }]),
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
      'window.jQuery': 'jquery',
    }),

    new StringReplacePlugin(),
  ],

  devServer: {
    historyApiFallback: true,   // 비정상 URL 루트로 리다이렉트 추정
    stats: 'minimal',
    inline: true,
    hot: true,
    headers: { 'Access-Control-Allow-Origin': '*' },
    proxy: {
      '/api': {
        // target: 'http://localhost:9040',
        target: 'http://localhost:19020',
        secure: false,
      },
    },
  },
});
