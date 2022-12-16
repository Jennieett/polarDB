const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
   devServer: {
      port:8080,
      proxy: {// API代理
        '/api': {
          // 凡是到/api的请求都会发往下面的地址
          target: 'http://localhost:8081',
          changeOrigin: true, // 允许跨域
          pathRewrite: {
            '^/api': ''
          }
        }
  
      }
    }
})
