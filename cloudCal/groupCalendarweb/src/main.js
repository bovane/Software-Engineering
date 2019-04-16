import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';   // 默认主题
/*import '../static/css/theme-green/index.css'; */      // 浅绿色主题
import "babel-polyfill";
import $ from 'jquery';
import VueResource from 'vue-resource'
import  VueQuillEditor from 'vue-quill-editor'
import 'vue-event-calendar/dist/style.css' //1.1.10之后的版本，css被放在了单独的文件中，方便替换
import vueEventCalendar from 'vue-event-calendar'
Vue.use(vueEventCalendar, {locale: 'en', weekStartOn: 1}) //可以设置语言，支持中文和英文

Vue.use(VueQuillEditor);
Vue.use(VueResource);

Vue.use(ElementUI);
window.Vue = Vue
Vue.prototype.$axios = axios;
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
