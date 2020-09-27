import Vue from 'vue'
import router from "./router";
import App from './App.vue'
import '@/styles/index.scss'
import 'lib-flexible'
import VideoPlayer from 'vue-video-player'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import api from "./common/api";
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VideoPlayer);
Vue.use(VueAxios, axios);
Vue.use(ViewUI)
Vue.config.productionTip = false
Vue.prototype.$api = api

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
