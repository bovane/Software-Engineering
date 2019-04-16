import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            component: resolve => require(['../admin/page/Login.vue'], resolve)
        },
        {
            path: '/register',
            component: resolve => require(['../admin/page/Register.vue'], resolve)
        },
        {
            path: '/forget',
            component: resolve => require(['../admin/page/Forget.vue'], resolve)
        },
        {
            path: '/calendar',
            component: resolve => require(['../admin/common/Home.vue'], resolve),
            children:[
                {
                    path: '/calendar',
                    component: resolve => require(['../admin/page/Calendar.vue'], resolve)
                },
            ]
        },
        {
            path: '/sgroup',
            component: resolve => require(['../admin/common/Home.vue'], resolve),
            children:[
                {
                    path: '/sgroup',
                    component: resolve => require(['../admin/page/Sgroup.vue'], resolve)
                },
                {
                    path: '/cgroup',
                    component: resolve => require(['../admin/page/Cgroup.vue'], resolve)
                },
            ]
        },
        {
            path: '/message',
            component: resolve => require(['../admin/common/Home.vue'], resolve),
            children:[
                {
                    path: '/message',
                    component: resolve => require(['../admin/page/Message.vue'], resolve)
                },
            ]
        },
    ]
})
