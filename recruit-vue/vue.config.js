module.exports = {
  publicPath: '/',
  devServer: {
    port: 8082,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8090/', //后台接口的服务地址
        changeOrigin: true, //changes the origin of the host header to the target URL 设置是否将host更换为代理url
        ws: true, //websocket是否代理
        secure: false, //true/false, if you want to verify the SSL Certs，https协议的情况下为true
        pathRewrite: { //object/function, rewrite target's url path. Object-keys will be used as RegExp to match paths. 重写url路径
            '^/api': '/api' //需要代理的路径
        }
      }
    }
  },
  lintOnSave:false
}

