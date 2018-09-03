export const entry = ['babel-polyfill', './main.js'];
export const output = {
    path: __dirname,
    filename: 'index.js'
};
export const resolve = {
    // Be able to import from file regardless of extension
    extensions: ['.js', '.jsx']
};
export const devServer = {
    inline: true,
    port: 8080 // Arbitrarily chosen for demo
};
export const devtool = 'source-map';
export const module = {
    loaders: [{
        // All files that end with '.js' and '.jsx'
        test: /\.jsx?$/,
        // Do not use files in node_modules folder
        exclude: /node_modules/,
        // Use babel as the loader
        loader: 'babel-loader',
        // Pass arguments/queries to the loader
        query: {
            presets: ['env', 'react']
        }
    }]
};