

// 開発or本番モードの選択(development、production、noneのいずれか設定必須)
// development: 開発時のファイル出力のモード(最適化より時間短縮,エラー表示などを優先)
// production: 本番時のファイル出力のモード(最適化されて出力される)
const MODE = "development";
// ソースマップの利用有無(productionのときはソースマップを利用しない)
const enabledSourceMap = MODE === "development";

// ファイル出力時の絶対パス指定に使用
const path = require('path');

// プラグイン
// js最適化
const TerserPlugin = require('terser-webpack-plugin');
// css最適化
// const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin');
// css抽出
// const MiniCssExtractPlugin = require("mini-css-extract-plugin");
// jQueryで使用
const webpack = require('webpack');


module.exports = {
    // エントリーポイント(メインのjsファイル)
    entry: './dist/index.js',
    // ファイルの出力設定
    output: {
        // 出力先(絶対パスでの指定必須)
        path: path.resolve(__dirname, 'webpack/js'),
        // 出力ファイル名
        filename: "bundle.js"

    },
    mode: MODE,
    // ソースマップ有効
    devtool: 'source-map',
    // ローダーの設定
    module: {
        rules: [
            {
                // ローダーの対象 // 拡張子 .js の場合
                test: /\.js$/,
                // ローダーの処理対象から外すディレクトリ
                exclude: /node_modules/,
                use: [
                    {
                        // Babel を利用する
                        loader: "babel-loader",
                        // Babel のオプションを指定する
                        options: {
                            presets: [
                                // プリセットを指定することで、ES2019 を ES5 に変換
                                "@babel/preset-env"
                                              
                            ]
                                        
                        }
                                  
                    }
                            
                ]
                      
            },
            {
                // ローダーの対象 // 拡張子 .scss の場合
                test: /\.scss/,
                // Sassファイルの読み込みとコンパイル
                use: [
                    // linkタグに出力する機能
                    "style-loader",
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            // css出力先を指定
                            publicPath: path.resolve(__dirname, 'dist/css'),
                            // developmentのときのみ有効
                            hmr: process.env.NODE_ENV === 'development',
                                        
                        },
                                  
                    },
                    // CSSをバンドルするための機能
                    {
                        loader: "css-loader",
                        options: {
                            // CSS内のurl()メソッドの取り込みを禁止する
                            // 画像ファイルをbase64でエンコードするとかでは無い限り、必要なし
                            url: false,
                            // ソースマップの利用有無
                            sourceMap: enabledSourceMap,
                            // Sass+PostCSSの場合は2を指定
                            // 0 => no loaders (default);
                            // 1 => postcss-loader;
                            // 2 => postcss-loader, sass-loader
                            importLoaders: 2
                                        
                        }
                                  
                    },
                    // PostCSS(Autoprefixer)のための設定
                    // ベンダープレフィックスを追加するためのPostCSS用プラグイン
                    {
                        loader: "postcss-loader",
                        options: {
                            // PostCSS側でもソースマップを有効にする
                            sourceMap: enabledSourceMap,
                            plugins: [
                                // Autoprefixerを有効化
                                require("autoprefixer")({
                                    grid: true
                                                    
                                })
                                              
                            ]
                                        
                        }
                                  
                    },
                    // Sassをバンドルするための機能
                    {
                        loader: "sass-loader",
                        options: {
                            // ソースマップの利用有無
                            sourceMap: enabledSourceMap
                                        
                        }
                                  
                    }
                            
                ]
                      
            }
                
        ]
          
    },
    // mode:puroductionでビルドした場合のファイル圧縮
    optimization: {
        minimizer: production
            ? []
            : [
                // jsファイルの最適化
                new TerserPlugin({
                    // すべてのコメント削除
                    extractComments: 'all',
                    // console.logの出力除去
                    terserOptions: {
                        compress: { drop_console: true }
                                  
                    },
                            
                }),
                // 抽出したcssファイルの最適化
                new OptimizeCssAssetsPlugin({})
                      
            ]
          
    },
    // js, css, html更新時自動的にブラウザをリロード
    devServer: {
        // サーバーの起点ディレクトリ
        // contentBase: "dist",
        // バンドルされるファイルの監視 // パスがサーバー起点と異なる場合に設定
        publicPath: '/dist/js/',
        //コンテンツの変更監視をする
        watchContentBase: true,
        // 実行時(サーバー起動時)ブラウザ自動起動
        open: true,
        // 自動で指定したページを開く
        openPage: "index.html",
        //　同一network内からのアクセス可能に
        host: "0.0.0.0"
          
    },
    plugins: [
        // 抽出したCSSファイル最適化
        new OptimizeCssAssetsPlugin({
            assetNameRegExp: /\.optimize\.css$/g,
            cssProcessor: require('cssnano'),
            cssProcessorPluginOptions: {
                preset: ['default', { discardComments: { removeAll: true } }],
                      
            },
            canPrint: true
                
        }),
        // ビルドされたjsファイルからstyleの部分を抽出してcssファイルで出力
        // jsファイルからcssの内容消える
        // v3でのextract-text-pluginのようなもの
        new MiniCssExtractPlugin({
            // 出力先ファイル名 // prefix は output.path
            filename: "../css/[name].css",
            chunkFilename: "[id].css"
                
        }),
        new webpack.ProvidePlugin({
            $: 'jquery'
                
        })
          
    ]

};
