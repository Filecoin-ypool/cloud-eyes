import Vue from "vue";
import Router from "vue-router";
import Major from './pages/Major';

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: '/',
            redirect: "/major"
        }, {
            name: 'major',
            path: '/major',
            component: Major,
        }
    ]
});
