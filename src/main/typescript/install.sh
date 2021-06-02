npm i -S jquery @types/jquery
npm i -D webpack webpack-cli
npm i -D terser-webpack-plugin optimize-css-assets-webpack-plugin mini-css-extract-plugin
npm i -D node-sass css-loader sass-loader style-loader postcss-loader
npm i -D babel-loader @babel/core @babel/polyfill @babel/preset-env
npm i -D autoprefixer

# 分割したcssの読み込みをリロードしない(brouser-sinc代用)
$ npm i -D webpack-dev-server

# gulp使うとき
$ npm i -D gulp gulp-eslint gulp-notify gulp-plumber
