var path    = require('path');
var webpack = require('webpack');

module.exports = {
  devtool: 'source-map',
	entry:'./demo/main.js',
	output:{
		path:path.resolve(__dirname, 'static'),
    publichPath : '/static/',
		filename:'bundle.js'
	},
	plugins : [
    new webpack.ProvidePlugin({
      Promise : 'es6-promise'
    })
  ],
  resolve: {
    extensions: ['', '.js', '.vue'],
    alias: {
      'src' : path.resolve(__dirname, './src'),
      'dist' : path.resolve(__dirname, './dist')
    }
  },
	module:{
    loaders:[
      {
        test:/\.js$/,
        exclude:/node_modules/,
        loader:'babel?presets[]=es2015'
      },
      {
        test:/\.vue$/,
        loader:'vue'
      },{
        test:/\.scss$/,
        loader:'style!css!sass'
      },
      {
        test:/\.css$/,
        loader:'style!css'
      }
    ]
  },
  vue: {
    autoprefixer: {
      browsers: ['> 1%']
    },
    loaders: {
      scss: 'style!css!sass'
    }
  },
  babel: {
    presets: ['es2015'],
    plugins: ['transform-runtime']
  },
  devServer: {
    port : 8070,
    historyApiFallback: true,
    stats : {
      colors : true,
      chunks : false
    }
  }
};

if (process.env.NODE_ENV === 'production') {
    module.exports.entry = './src/index.js'

    module.exports.output = {
        path: path.resolve(__dirname, './dist'),
        filename:'index.js',
        library:'VueEventCalendar',
        libraryTarget: 'umd'
    }
    module.exports.devtool = '#source-map'
    module.exports.module.rules[0].options.loaders = {
        css: ExtractTextPlugin.extract({
            use: 'css-loader',
            fallback: 'vue-style-loader' // <- this is a dep of vue-loader, so no need to explicitly install if using npm3
        }),
        less: ExtractTextPlugin.extract({
            use: 'css-loader!less-loader',
            fallback: 'vue-style-loader'
        })
    }
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.LoaderOptionsPlugin({
            minimize: true
        }),
        new ExtractTextPlugin("style.css")
    ])
}
