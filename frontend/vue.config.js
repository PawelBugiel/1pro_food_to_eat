const { defineConfig } = require('@vue/cli-service');
const webpack = require('webpack');

module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    plugins: [
      new webpack.DefinePlugin({
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: JSON.stringify(false),
        __VUE_OPTIONS_API__: JSON.stringify(true),
        __VUE_PROD_DEVTOOLS__: JSON.stringify(false)
      })
    ]
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://myspring-boot-app:8081', // backend port
        changeOrigin: true,
        // pathRewrite: { '^/api': '' } // Opcjonalne, usuwa /api z żądania, jeśli backend tego wymaga
      }
    }
  }
});

