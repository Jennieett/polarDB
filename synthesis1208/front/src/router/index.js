import { createRouter, createWebHashHistory } from 'vue-router'

import Layout from "../layout/Layout.vue";

const routes = [
	{
		path:'/',
		name:'Login',
		component: ()=> import('../views/Login.vue')
	},
	{
		path:'/register',
		name:"Register",
		component: ()=> import('../views/Register.vue')
	},
	{
		path:'/Uhome',
		name:"Uhome",
		component: ()=> import('../views/Userhome.vue')
	},

	// 用于查看界面（但是需要一个账号和密码
	{
		path:'/book',
		name:'Book',
		component: ()=> import('../views/Book.vue')
	},
	{
		path:'/bookwithUser',
		name:'BookWithUser',
		component: ()=> import('../views/BookWithUser.vue')
	},
  
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router


