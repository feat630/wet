import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import main from '@/components/main'
import Map from '@/components/Map'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }
    ,{
      path: '/main',
      name: 'main',
      component: main
    }
    ,{
      path: '/map',
      name: 'map',
      component: Map
    }
    ,{
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
