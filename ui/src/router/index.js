import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from "@/components/login.vue";
import StudentCourse from "@/components/student-course.vue";
import AddCourse from "@/components/add-course.vue";
import DeleteCourse from "@/components/delete-course.vue";
import TeacherCourse from "@/components/teacher-course.vue";
import MyInfo from "@/components/my-info.vue";
import TeacherInfo from "@/components/teacher-info.vue";
import ChangePasswd from "@/components/change-passwd.vue";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'teacher-course',
    component: TeacherCourse
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/student-course',
    name: 'student-course',
    component: StudentCourse
  },
  {
    path: '/add-course',
    name: 'add-course',
    component: AddCourse
  },
  {
    path: '/delete-course',
    name: 'delete-course',
    component: DeleteCourse
  },
  {
    path: '/teacher-course',
    name: 'teacher-course',
    component: TeacherCourse
  },
  {
    path: '/my-info',
    name: 'my-info',
    component: MyInfo
  },
  {
    path: '/teacher-info',
    name: 'teacher-info',
    component: TeacherInfo
  },
  {
    path: '/change-passwd',
    name: 'change-passwd',
    component: ChangePasswd
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
