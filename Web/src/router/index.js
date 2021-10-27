import Vue from 'vue'
import Router from 'vue-router'
import remoteconfig from '@/components/remoteconfig'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'remoteconfig',
      component: remoteconfig
    }
  ]
})
