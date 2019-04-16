// https://github.com/shelljs/shelljs
require('./check-versions')()

process.env.NODE_ENV = 'production'

var ora = require('ora')
var path = require('path')
var chalk = require('chalk')
var shell = require('shelljs')
var webpack = require('webpack')
var config = require('../config')
var webpackConfig = require('./webpack.prod.conf')

var spinner = ora('building for production...')
spinner.start()

var assetsPath = path.join(config.build.assetsRoot, config.build.assetsSubDirectory)
shell.rm('-rf', assetsPath)
shell.mkdir('-p', assetsPath)
shell.config.silent = true
shell.cp('-R', 'static/*', assetsPath)
shell.config.silent = false

var fs = require('fs')
var zlib = require('zlib')
var rollup = require('rollup')
var uglify = require('uglify-js')
var babel = require('rollup-plugin-babel')
var replace = require('rollup-plugin-replace')
var version = process.env.VERSION || require('../package.json').version

var banner =
    '/*!\n' +
    ' * vue-fullcalendar.js v' + version + '\n' +
    ' * (c) ' + new Date().getFullYear() + ' Sunny Wang\n' +
    ' * Released under the MIT License.\n' +
    ' */'

// CommonJS build.
// this is used as the "main" field in package.json
// and used by bundlers like Webpack and Browserify.
rollup.rollup({
    entry: 'src/fc.js'
})
    .then(function (bundle) {
        return write('dist/vue-fullcalendar.common.js', bundle.generate({
            format: 'cjs'
        }).code)
    })
    .then(function () {
        return rollup.rollup({
            entry: 'src/fc.js',
        })
            .then(function (bundle) {
                return write('dist/vue-fullcalendar.js', bundle.generate({
                    format: 'umd',
                    banner: banner,
                    moduleName: 'vue-fullcalendar'
                }).code)
            })
    })
    .then(function () {
        // Standalone Production Build
        return rollup.rollup({
            entry: 'src/fc.js'
        })
            .then(function (bundle) {
                var code = bundle.generate({
                    format: 'umd',
                    moduleName: 'vue-fullcalendar'
                }).code
                var minified = banner + '\n' + uglify.minify(code, {
                    fromString: true
                }).code
                return write('dist/vue-fullcalendar.min.js', minified)
            })
            .then(zip)
    })
    .catch(logError)

function write (dest, code) {
    return new Promise(function (resolve, reject) {
        fs.writeFile(dest, code, function (err) {
            if (err) return reject(err)
            console.log(blue(dest) + ' ' + getSize(code))
            resolve()
        })
    })
}

function zip () {
    return new Promise(function (resolve, reject) {
        fs.readFile('dist/vue-fullcalendar.min.js', function (err, buf) {
            if (err) return reject(err)
            zlib.gzip(buf, function (err, buf) {
                if (err) return reject(err)
                write('dist/vue-fullcalendar.min.js.gz', buf).then(resolve)
            })
        })
    })
}

function getSize (code) {
    return (code.length / 1024).toFixed(2) + 'kb'
}

function logError (e) {
    console.log(e)
}

function blue (str) {
    return '\x1b[1m\x1b[34m' + str + '\x1b[39m\x1b[22m'
}


webpack(webpackConfig, function (err, stats) {
  spinner.stop()
  if (err) throw err
  process.stdout.write(stats.toString({
    colors: true,
    modules: false,
    children: false,
    chunks: false,
    chunkModules: false
  }) + '\n\n')

  console.log(chalk.cyan('  Build complete.\n'))
  console.log(chalk.yellow(
    '  Tip: built files are meant to be served over an HTTP server.\n' +
    '  Opening index.html over file:// won\'t work.\n'
  ))
})
