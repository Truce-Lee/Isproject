import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import request from "@/utils/request";
Vue.config.productionTip = false

Vue.use(ElementUI, {size:"small"});
Vue.prototype.request=request

// new Vue({
//   el: '#app',
//   render: h => h(App)
// });
new Vue({
  el: '#app',
  router,
  render: h => h(App)
}).$mount('#app')
// new Vue({
//   el: '#app',
//   render: h => h(App),
//   router,
//   components: { App },
//   template: '<App/>'
// });
