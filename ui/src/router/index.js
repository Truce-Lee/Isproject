import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "@/components/login.vue";
import AddCourse from "@/components/user/add-course.vue";
import DeleteCourse from "@/components/user/delete-course.vue";
import ViewCourse from "@/components/user/view-course.vue";
import UserInfo from "@/components/user/user-info.vue";
import TeacherManager from "@/components/user-manager.vue";
import ChangeUser from "@/components/user/change-user.vue";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'user-course',
    component: TeacherManager,
    redirect:'/user-course',
    children:[
      {path:'user-course',name:'user-course',component:ViewCourse},
      {path:'add-course',name:'add-course',component:AddCourse},
      {path:'delete-course',name:'delete-course',component:DeleteCourse},
      {path:'user-info',name:'user-info',component:UserInfo},
      {path:'change-user',name:'change-user',component:ChangeUser},
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/add-course',
    name: 'add-course',
    component: AddCourse
  },


  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
